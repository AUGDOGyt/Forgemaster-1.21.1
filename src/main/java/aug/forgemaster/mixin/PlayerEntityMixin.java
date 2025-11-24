package aug.forgemaster.mixin;

import aug.forgemaster.block.ModBlocks;
import aug.forgemaster.damage_type.ModDamageTypes;
import aug.forgemaster.item.AttaccaItem;
import aug.forgemaster.item.ModItemComponentTypes;
import aug.forgemaster.item.ModItems;
import aug.forgemaster.particle.ModParticles;
import aug.forgemaster.util.Helpers;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract ItemCooldownManager getItemCooldownManager();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;getBonusAttackDamage(Lnet/minecraft/entity/Entity;FLnet/minecraft/entity/damage/DamageSource;)F"
            )
    )
    private void attack(Entity target, CallbackInfo ci, @Local(ordinal = 0) LocalFloatRef damage, @Local ItemStack stack) {
        if (stack.getItem() instanceof AttaccaItem && stack.getOrDefault(ModItemComponentTypes.ATTACCA_CHARGE, 0) >= AttaccaItem.MAX_CAPACITY) {
            damage.set(damage.get() + 6);
        }
    }

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            )
    )
    private void attack(Entity target, CallbackInfo ci, @Local(ordinal = 2) boolean isCritical) {
        if (isCritical && getWeaponStack().isOf(ModItems.ATTACCA) && !getWorld().isClient && !getItemCooldownManager().isCoolingDown(ModItems.ATTACCA)) {
            getItemCooldownManager().set(ModItems.ATTACCA, 60);

            Vec3d origin = getEyePos();
            Vec3d dir = target.getEyePos().subtract(origin).normalize();
            dir = new Vec3d(dir.x, 0, dir.z);

            blocks:
            for (int i = 2; i < 10; i++) {
                BlockPos pos = BlockPos.ofFloored(origin.add(dir.multiply(i)));
                BlockState state = getWorld().getBlockState(pos);
                System.out.println(pos + " " + state);

                if (Helpers.canPlaceFireAt(state, getWorld(), pos)) {
                    while (!Helpers.canPlaceFireAt(getWorld().getBlockState(pos), getWorld(), pos)) {
                        pos = pos.up();

                        if (!pos.isWithinDistance(origin, 10)) {
                            break blocks;
                        }
                    }
                } else {
                    while (!Helpers.canPlaceFireAt(getWorld().getBlockState(pos), getWorld(), pos)) {
                        pos = pos.down();

                        if (!pos.isWithinDistance(origin, 10)) {
                            break blocks;
                        }
                    }
                }

                getWorld().setBlockState(pos, ModBlocks.GREEK_FIRE.getDefaultState());
            }
        }
    }

    @ModifyArg(
            method = "spawnSweepAttackParticles",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I"
            )
    )
    private ParticleEffect spawnSweepAttackParticles(ParticleEffect particle) {
        return getWeaponStack().isOf(ModItems.ATTACCA) ? ModParticles.FIRE_SWEEP : particle;
    }

    @WrapOperation(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/damage/DamageSources;playerAttack(Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/entity/damage/DamageSource;"
            )
    )
    private DamageSource getDamageSource(DamageSources instance, PlayerEntity attacker, Operation<DamageSource> original, @Local ItemStack stack) {
        return stack.isOf(ModItems.ATTACCA) ? instance.create(ModDamageTypes.ATTACCA, attacker) : original.call(instance, attacker);
    }
}
