package aug.forgemaster.world.gen;

import aug.forgemaster.world.gen.configured_feature.CraterFeature;
import aug.forgemaster.world.gen.configured_feature.CraterFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.RandomOffsetPlacementModifier;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import static aug.forgemaster.Forgemaster.id;
import static aug.forgemaster.util.Helpers.key;

public class ModFeatures {
    public static final CraterFeature CRATER = register("crater", new CraterFeature(CraterFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, id(name), feature);
    }

    public static final RegistryKey<ConfiguredFeature<?, ?>> C_CRATER = key(RegistryKeys.CONFIGURED_FEATURE, "crater");
    public static final RegistryKey<PlacedFeature> P_CRATER = key(RegistryKeys.PLACED_FEATURE, "crater");

    public static void bootstrapConfigured(Registerable<ConfiguredFeature<?, ?>> c) {
        var filling = DataPool.<BlockState>builder()
                .add(Blocks.NETHERRACK.getDefaultState(), 8)
                .add(Blocks.BLACKSTONE.getDefaultState(), 4)
                .add(Blocks.MAGMA_BLOCK.getDefaultState(), 3);

        ConfiguredFeatures.register(c, C_CRATER, CRATER, new CraterFeatureConfig(
                new WeightedBlockStateProvider(filling),
                BlockTags.MOSS_REPLACEABLE,
                ConstantIntProvider.create(5),
                ConstantIntProvider.create(18),
                ConstantIntProvider.create(20),
                ConstantFloatProvider.create(4)
        ));
    }

    public static void bootstrapPlaced(Registerable<PlacedFeature> c) {
        var feat = c.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        PlacedFeatures.register(c,
                P_CRATER, feat.getOrThrow(C_CRATER), RandomOffsetPlacementModifier.horizontally(UniformIntProvider.create(1, 5))
        );
    }

    public static void register() {
    }
}
