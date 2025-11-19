package aug.forgemaster.entity;

import aug.forgemaster.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SideShapeType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GreekFireballEntity extends ExplosiveProjectileEntity {
    public GreekFireballEntity(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public GreekFireballEntity(double x, double y, double z, World world) {
        super(ModEntities.GREEK_FIREBALL, x, y, z, world);
    }

    public GreekFireballEntity(double x, double y, double z, Vec3d velocity, World world) {
        super(ModEntities.GREEK_FIREBALL, x, y, z, velocity, world);
    }

    public GreekFireballEntity(LivingEntity owner, Vec3d velocity, World world) {
        super(ModEntities.GREEK_FIREBALL, owner, velocity, world);
    }

    @Override
    protected void onCollision(HitResult hit) {
        super.onCollision(hit);

        if (!getWorld().isClient) {
            getWorld().createExplosion(this, getX(), getY(), getZ(), 2, false, World.ExplosionSourceType.NONE);

            for (int x = -4; x <= 4; x++) {
                blocks:
                for (int z = -4; z <= 4; z++) {
                    BlockPos pos = getBlockPos().add(x, 0, z);
                    BlockState state = getWorld().getBlockState(pos);

                    if (state.isSideSolid(getWorld(), pos, Direction.UP, SideShapeType.FULL) && state.isSolidBlock(getWorld(), pos)) {
                        while ((state = getWorld().getBlockState(pos)).isSolidBlock(getWorld(), pos) && state.isSideSolid(getWorld(), pos, Direction.UP, SideShapeType.FULL)) {
                            pos = pos.up();

                            if (!pos.isWithinDistance(getPos(), 4)) {
                                continue blocks;
                            }
                        }
                    } else {
                        while (!(state = getWorld().getBlockState(pos.down())).isSolidBlock(getWorld(), pos.down()) && !state.isSideSolid(getWorld(), pos, Direction.UP, SideShapeType.FULL)) {
                            pos = pos.down();

                            if (!pos.isWithinDistance(getPos(), 4)) {
                                continue blocks;
                            }
                        }
                    }

                    if (pos.isWithinDistance(getPos(), 4)) {
                        getWorld().setBlockState(pos, ModBlocks.GREEK_FIRE.getDefaultState());
                    }
                }
            }

            discard();
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult hit) {
        super.onEntityHit(hit);

        if (getWorld() instanceof ServerWorld server) {
            DamageSource source = getDamageSources().create(DamageTypes.FIREBALL, this, getOwner());
            hit.getEntity().damage(source, 6);
            EnchantmentHelper.onTargetDamaged(server, hit.getEntity(), source);
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }
}
