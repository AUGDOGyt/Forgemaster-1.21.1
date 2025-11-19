package aug.forgemaster.entity;

import aug.forgemaster.Forgemaster;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.UnaryOperator;

public class ModEntities {

    public static final EntityType<GreekFireballEntity> GREEK_FIREBALL = register("greek_fireball", GreekFireballEntity::new, SpawnGroup.MISC, builder -> builder.dimensions(1, 1).maxTrackingRange(4).trackingTickInterval(10));

    private static <T extends Entity> EntityType<T> register(String name, EntityType.EntityFactory<T> factory, SpawnGroup spawnGroup, UnaryOperator<EntityType.Builder<T>> consumer) {
        return Registry.register(Registries.ENTITY_TYPE, Forgemaster.id(name), consumer.apply(EntityType.Builder.create(factory, spawnGroup)).build(name));
    }

    public static void register() {
        Forgemaster.LOGGER.info("Registering Mod Entities for " + Forgemaster.MOD_ID);
    }
}
