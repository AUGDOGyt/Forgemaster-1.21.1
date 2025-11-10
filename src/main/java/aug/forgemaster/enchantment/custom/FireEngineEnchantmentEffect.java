package aug.forgemaster.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record FireEngineEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<FireEngineEnchantmentEffect> CODEC = MapCodec.unit(FireEngineEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (level == 1) {
            //fire ring
            Vec3d origin = getEyePos();
            Vec3d radius = distanceFallen()/20;

            for (int i = 2; i < radius; i++) {
                int fireRadius = 3; // Radius of the fire ring
                int fireCount = ; // Number of fire particles/entities to spawn
                double angleStep = 360.0 // fireCount;

                for (int i = 0; i < fireCount; i++) {
                    double angle = Math.toRadians(i * angleStep);
                    double x = user.getX() + fireRadius * Math.cos(angle);
                    double z = user.getZ() + fireRadius * Math.sin(angle);
        
                    // Use the ServerWorld.spawnParticles method for server-side particles
                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(
                        ParticleTypes.FLAME, // The particle type
                        x, user.getY(), z, // Position (y is player's y)
                        1, // Number of particles to spawn
                        0, 0, 0, // Spread/offsets (zero for a precise ring)
                        0.1 // Speed of particles
                        );
                    }

                    // Spawn a Fireball entity at each point
                    FireballEntity fireball = new FireballEntity(EntityType.FIREBALL, world);
                    fireball.setPos(x, user.getY(), z);
                    fireball.setVelocity(1, 0.1, 0); // Give it a slight upwards velocity
                    world.spawnEntity(fireball);

                    // Alternatively, you could place fire blocks
                    BlockPos firePos = new BlockPos((int)x, (int)user.getY(), (int)z);
                    world.setBlockState(firePos, Blocks.FIRE.getDefaultState());
            }
            //extra damage

        } else if (level == 2) {
            Vec3d origin = getEyePos();
            Vec3d radius = distanceFallen()/15;

            for (int i = 2; i < radius; i++) {
                int fireRadius = 3; // Radius of the fire ring
                int fireCount = ; // Number of fire particles/entities to spawn
                double angleStep = 360.0 // fireCount;

                for (int i = 0; i < fireCount; i++) {
                    double angle = Math.toRadians(i * angleStep);
                    double x = user.getX() + fireRadius * Math.cos(angle);
                    double z = user.getZ() + fireRadius * Math.sin(angle);
        
                    // Use the ServerWorld.spawnParticles method for server-side particles
                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(
                        ParticleTypes.FLAME, // The particle type
                        x, user.getY(), z, // Position (y is player's y)
                        1, // Number of particles to spawn
                        0, 0, 0, // Spread/offsets (zero for a precise ring)
                        0.1 // Speed of particles
                        );
                    }

                    // Spawn a Fireball entity at each point
                    FireballEntity fireball = new FireballEntity(EntityType.FIREBALL, world);
                    fireball.setPos(x, user.getY(), z);
                    fireball.setVelocity(1, 0.1, 0); // Give it a slight upwards velocity
                    world.spawnEntity(fireball);

                    // Alternatively, you could place fire blocks
                    BlockPos firePos = new BlockPos((int)x, (int)user.getY(), (int)z);
                    world.setBlockState(firePos, Blocks.FIRE.getDefaultState());

        } else if (level == 3) {
            Vec3d origin = getEyePos();
            Vec3d radius = distanceFallen()/10;

            for (int i = 2; i < radius; i++) {
                int fireRadius = 3; // Radius of the fire ring
                int fireCount = ; // Number of fire particles/entities to spawn
                double angleStep = 360.0 // fireCount;

                for (int i = 0; i < fireCount; i++) {
                    double angle = Math.toRadians(i * angleStep);
                    double x = user.getX() + fireRadius * Math.cos(angle);
                    double z = user.getZ() + fireRadius * Math.sin(angle);
        
                    // Use the ServerWorld.spawnParticles method for server-side particles
                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(
                        ParticleTypes.FLAME, // The particle type
                        x, user.getY(), z, // Position (y is player's y)
                        1, // Number of particles to spawn
                        0, 0, 0, // Spread/offsets (zero for a precise ring)
                        0.1 // Speed of particles
                        );
                    }

                    // Spawn a Fireball entity at each point
                    FireballEntity fireball = new FireballEntity(EntityType.FIREBALL, world);
                    fireball.setPos(x, user.getY(), z);
                    fireball.setVelocity(1, 0.1, 0); // Give it a slight upwards velocity
                    world.spawnEntity(fireball);

                    // Alternatively, you could place fire blocks
                    BlockPos firePos = new BlockPos((int)x, (int)user.getY(), (int)z);
                    world.setBlockState(firePos, Blocks.FIRE.getDefaultState());
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
