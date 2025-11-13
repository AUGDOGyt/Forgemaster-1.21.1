package aug.forgemaster.world.gen;

import aug.forgemaster.world.gen.configured_feature.CraterFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import static aug.forgemaster.Forgemaster.id;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> CRATER = key("crater");

    static RegistryKey<ConfiguredFeature<?, ?>> key(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id(id));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> c) {
        var blocks = c.getRegistryLookup(RegistryKeys.BLOCK);

        var filling = DataPool.<BlockState>builder()
                .add(Blocks.NETHERRACK.getDefaultState(), 8)
                .add(Blocks.BLACKSTONE.getDefaultState(), 4)
                .add(Blocks.MAGMA_BLOCK.getDefaultState(), 3);

        ConfiguredFeatures.register(c, CRATER,
                ModFeatures.CRATER,
                new CraterFeatureConfig(
                        new WeightedBlockStateProvider(filling),
                        BlockTags.MOSS_REPLACEABLE,
                        ConstantIntProvider.create(5),
                        ConstantIntProvider.create(18),
                        ConstantIntProvider.create(20),
                        ConstantFloatProvider.create(4)
                )
        );
    }

}
