package aug.forgemaster.mixin;

import aug.forgemaster.effect.ModEffects;
import aug.forgemaster.effect.ScorchedEffect;
import aug.forgemaster.item.ModItems;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static aug.forgemaster.effect.ModEffects.SCORCHED;

@Mixin(PlayerEntity.class)
    public abstract class CheckCritHitMixin extends LivingEntity {
    @Shadow
    public abstract boolean isCreative();

    @Shadow public abstract ItemCooldownManager getItemCooldownManager();

    protected CheckCritHitMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
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
            var instance = new StatusEffectInstance(SCORCHED, 100, 1, false, true, true);
            if (target instanceof LivingEntity living) {
                living.addStatusEffect(instance);
            }
            getItemCooldownManager().set(ModItems.ATTACCA, 60);
            Vec3d origin = getEyePos();
            Vec3d dir = target.getEyePos().subtract(origin).normalize();
            dir = new Vec3d(dir.x, 0, dir.z);

            for (int i = 2; i < 10; i++) {
                BlockPos pos = BlockPos.ofFloored(origin.add(dir.multiply(i))).up(3);

                BlockState state = getWorld().getBlockState(pos);
                if (state.isSolidBlock(getWorld(), pos)) {
                    break;
                }

                while (!(state = getWorld().getBlockState(pos.down())).isSolidBlock(getWorld(), pos.down()) && state.isReplaceable()) {
                    pos = pos.down();
                }

                getWorld().setBlockState(pos, Blocks.FIRE.getDefaultState());
            }
        }
    }
}