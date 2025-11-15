package aug.forgemaster.world.gen.configured_feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class CraterFeatureConfig implements FeatureConfig {
    public final BlockStateProvider fillingProvider;
    public TagKey<Block> replaceable;
    public final IntProvider radiusToRingCenter;
    public final IntProvider ringWidth;
    public final IntProvider ringHeight;
    public final FloatProvider noiseMultiplier;

    public CraterFeatureConfig(
            BlockStateProvider fillingProvider,
            TagKey<Block> replaceable,
            IntProvider radiusToRingCenter,
            IntProvider ringWidth,
            IntProvider ringHeight,
            FloatProvider noiseMultiplier
    ) {
        this.fillingProvider = fillingProvider;
        this.replaceable = replaceable;
        this.radiusToRingCenter = radiusToRingCenter;
        this.ringWidth = ringWidth;
        this.ringHeight = ringHeight;
        this.noiseMultiplier = noiseMultiplier;
    }

    public static final Codec<CraterFeatureConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BlockStateProvider.TYPE_CODEC
                            .fieldOf("filling_provider")
                            .forGetter(config -> config.fillingProvider),
                    TagKey.codec(RegistryKeys.BLOCK)
                            .fieldOf("replaceable")
                            .forGetter(config -> config.replaceable),
                    IntProvider.createValidatingCodec(-20, 20)
                            .fieldOf("radius_to_ring_center")
                            .orElse(UniformIntProvider.create(4, 13))
                            .forGetter(config -> config.radiusToRingCenter),
                    IntProvider.createValidatingCodec(-200, 200)
                            .fieldOf("ring_width")
                            .orElse(UniformIntProvider.create(2, 6))
                            .forGetter(config -> config.ringWidth),
                    IntProvider.createValidatingCodec(1, 200)
                            .fieldOf("ring_height")
                            .orElse(UniformIntProvider.create(2, 6))
                            .forGetter(config -> config.ringHeight),
                    FloatProvider.createValidatedCodec(0f, 10f)
                            .fieldOf("noise_multiplier")
                            .forGetter(config -> config.noiseMultiplier)
            ).apply(instance, CraterFeatureConfig::new)
    );
}
