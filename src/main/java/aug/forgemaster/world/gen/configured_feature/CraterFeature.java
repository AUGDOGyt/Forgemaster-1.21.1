package aug.forgemaster.world.gen.configured_feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import static java.lang.Math.sqrt;

public class CraterFeature extends Feature<CraterFeatureConfig> {
    public CraterFeature(Codec<CraterFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<CraterFeatureConfig> context) {
        var origin = context.getOrigin();
        var random = context.getRandom();
        var world = context.getWorld();
        var config = context.getConfig();

        var chunkRandom = new ChunkRandom(world.getRandom()/*new LegacyRandomSource(world.getSeed())*/);
        var dps = DoublePerlinNoiseSampler.create(chunkRandom, -2, 1.0);

        var radiusToRingCenter = config.radiusToRingCenter.get(random);
        var width = config.ringWidth.get(random);
        var height = config.ringHeight.get(random);
        var noiseMultiplier = ((height + width) / 4.0) * config.noiseMultiplier.get(random);

        var shapeRange = shapeDistanceFromCenter(radiusToRingCenter, width, height);

        var area = BlockPos.iterate(
                origin.add(shapeRange, shapeRange, shapeRange),
                origin.add(-shapeRange, -shapeRange, -shapeRange)
        );
        for (BlockPos pos : area) {
            if (world.getDimension().logicalHeight() > pos.getY() /*&& world.getBlockState(pos).isIn(config.replaceable)*/) {
                if (shape(relative(pos, origin), radiusToRingCenter, width, height, noiseMultiplier, dps)) {
                    setBlockState(world, pos, config.fillingProvider.get(random, pos));
                }
            }
        }

        setBlockState(world, origin, Blocks.GLOWSTONE.getDefaultState());
        return true;
    }

    private Vec3d relative(BlockPos pos, BlockPos origin) {
        var x = (origin.getX() - pos.getX());
        var y = (origin.getY() - pos.getY());
        var z = (origin.getZ() - pos.getZ());
        return new Vec3d(x, y, z);
    }

    private boolean shape(Vec3d rotate, int radiusToRingCenter, int width, int height, double noiseMultiplier, DoublePerlinNoiseSampler dps) {
        var ratio = (double) height / width;

        var holeWidth = radiusToRingCenter - sqrt(rotate.getX() * rotate.getX() + rotate.getZ() * rotate.getZ());
        var rHhW = (holeWidth * holeWidth) + ((rotate.getY() * rotate.getY()) / (ratio * ratio));

        if (noiseMultiplier != 0.0) rHhW += dps.sample(rotate.getX(), rotate.getY(), rotate.getZ()) * noiseMultiplier;

        return (double) ((width / 2) * (width / 2)) > rHhW;
    }

    private int shapeDistanceFromCenter(int radiusToRingCenter, int width, int height) {
        return (int) (radiusToRingCenter + (Math.max(width, height) / 2.0));
    }
}
