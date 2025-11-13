package aug.forgemaster.world.gen;

import aug.forgemaster.world.gen.configured_feature.CraterFeature;
import aug.forgemaster.world.gen.configured_feature.CraterFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import static aug.forgemaster.Forgemaster.id;

public class ModFeatures {
    public static final CraterFeature CRATER = register("crater", new CraterFeature(CraterFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, id(name), feature);
    }

    public static void register() {
    }
}
