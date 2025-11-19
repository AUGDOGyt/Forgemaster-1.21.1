package aug.forgemaster.world.gen.configured_feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public class CraterFeatureConfig implements FeatureConfig {
    public TagKey<Block> cantReplaceable;
    public final IntProvider width;
    public final IntProvider height;
    public final IntProvider blockClearRage;
    public final FloatProvider ringSize;
    public final FloatProvider ringHeight;
    public final FloatProvider thickness;
    public final FloatProvider offsetNoiseMultiplier;
    public final FloatProvider textureNoiseMultiplier;

    public CraterFeatureConfig(
            TagKey<Block> cantReplaceable,
            IntProvider width, IntProvider height, IntProvider blockClearRage,
            FloatProvider ringSize, FloatProvider ringHeight, FloatProvider thickness,
            FloatProvider offsetNoiseMultiplier, FloatProvider textureNoiseMultiplier
    ) {
        this.cantReplaceable = cantReplaceable;
        this.width = width;
        this.height = height;
        this.blockClearRage = blockClearRage;
        this.ringSize = ringSize;
        this.ringHeight = ringHeight;
        this.thickness = thickness;
        this.offsetNoiseMultiplier = offsetNoiseMultiplier;
        this.textureNoiseMultiplier = textureNoiseMultiplier;
    }

    public static final Codec<CraterFeatureConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    TagKey.codec(RegistryKeys.BLOCK)
                            .fieldOf("cant_replaceable")
                            .forGetter(config -> config.cantReplaceable),

                    IntProvider.createValidatingCodec(8, 128)
                            .fieldOf("width")
                            .orElse(ConstantIntProvider.create(16))
                            .forGetter(config -> config.width),
                    IntProvider.createValidatingCodec(2, 128)
                            .fieldOf("height")
                            .orElse(ConstantIntProvider.create(8))
                            .forGetter(config -> config.height),
                    IntProvider.createValidatingCodec(0, 128)
                            .fieldOf("block_clear_rage")
                            .orElse(ConstantIntProvider.create(32))
                            .forGetter(config -> config.blockClearRage),

                    FloatProvider.createValidatedCodec(-128, 128)
                            .fieldOf("ring_size")
                            .orElse(ConstantFloatProvider.create(4))
                            .forGetter(config -> config.ringSize),
                    FloatProvider.createValidatedCodec(-128, 128)
                            .fieldOf("ring_height")
                            .orElse(ConstantFloatProvider.create(2))
                            .forGetter(config -> config.ringHeight),
                    FloatProvider.createValidatedCodec(0, 10)
                            .fieldOf("thickness")
                            .orElse(ConstantFloatProvider.create(0.5f))
                            .forGetter(config -> config.thickness),

                    FloatProvider.createValidatedCodec(-10, 10)
                            .fieldOf("offset_noise_multiplier")
                            .forGetter(config -> config.offsetNoiseMultiplier),
                    FloatProvider.createValidatedCodec(-10, 10)
                            .fieldOf("texture_noise_multiplier")
                            .forGetter(config -> config.textureNoiseMultiplier)

            ).apply(instance, CraterFeatureConfig::new)
    );
}
