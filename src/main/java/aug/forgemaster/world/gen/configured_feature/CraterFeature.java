package aug.forgemaster.world.gen.configured_feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import static com.mojang.text2speech.Narrator.LOGGER;
import static java.lang.Math.*;

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

        var chunkRandom = new ChunkRandom(new CheckedRandom(world.getSeed()));
        var dps = DoublePerlinNoiseSampler.create(chunkRandom, -2, 1.0);

        var width = 40;//config.ringWidth.get(random);
        var height = 8;//config.ringHeight.get(random);
        var innerSphereRadius = 0;//config.innerSphereRadius.get(random);
        var noiseMultiplier = 1;//((height + width) / 4.0) * config.noiseMultiplier.get(random);

        var area = BlockPos.iterate(
                origin.add(width, height, width),
                origin.add(-width, -height, -width)
        );
        for (BlockPos pos : area) {
            var existingState = world.getBlockState(pos);
            if (world.getDimension().logicalHeight() > pos.getY() /*&& !(existingState.isAir() || existingState.isReplaceable())*/) {
                var state = shape(relative(pos.offset(Direction.Axis.Y, (int) (height * (4.5 / 5.0))), origin), innerSphereRadius, width, height, noiseMultiplier, dps, config.fillingProvider, random, pos);
                if (state == null) {
                    if (existingState.isIn(BlockTags.LOGS)) {
                        state = Blocks.BASALT.getStateWithProperties(existingState);
                    } else if (existingState.isIn(BlockTags.LEAVES)) {
                        state = Blocks.AIR.getDefaultState();
                    }
                }
                if (state != null) {
                    setBlockState(world, pos, state);
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

    private BlockState shape(Vec3d pos, int innerSphereRadius, int widthR, int heightR, double noiseMultiplier, DoublePerlinNoiseSampler dps, BlockStateProvider fillingProvider, Random random, BlockPos blockPos) {
//        if (!(pos.getZ() == 0 || pos.getX() == 0)) return null;
        var rad = widthR * widthR;
        var scale = 4;
        var x = ((pos.getX() * pos.getX()) / rad) * scale;
        var y = (pos.getY() / heightR);
        var z = ((pos.getZ() * pos.getZ()) / rad) * scale;
        var horizontalDist = sqrt(x + z);

        if (horizontalDist >= 1.7) {
            if (horizontalDist >= 2) {
                return null;
            }
            if (random.nextBetween(0, (int) (2 * horizontalDist)) > 0) {
                return null;
            }
        }

        var output = 0.75;

        var absX = abs(horizontalDist);
        if (absX <= 1) {
            output = (horizontalDist * horizontalDist);
        } else if (absX <= 1.5) {
            output = ((-8 * pow((absX - 1.25), 2)) / 2) + 1.25;
        } else if (absX <= 2) {
            output = pow(absX - 2, 2) + 0.75;
        }

        if (pos.getY() > 1) {
            output = abs(output);
        }
        var noise = dps.sample(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        var ctrl = abs(y);
        if (noiseMultiplier != 0) {
            ctrl += (((horizontalDist * horizontalDist) / 2) * noise * noiseMultiplier) * 0.1;
        }

        var check = output > ctrl;
        if (pos.getY() == 1 && pos.getX() >= 0 && pos.getX() < 4) {
            LOGGER.info("=-=");
            LOGGER.info("Dist: [{}]", horizontalDist);
            LOGGER.info("Output: [{}]", output);
            LOGGER.info("Pos: [{}]", pos);
            LOGGER.info("Math: [{}, {}, {}]", x, y, z);
            LOGGER.info("Ctrl: [{}]", ctrl);
            LOGGER.info("");
        }


        if (check) {
            var sample = noise * noiseMultiplier * 10;
            if (sample < -1.9) {
                return Blocks.MAGMA_BLOCK.getDefaultState();
            }
            if (sample > 0.5) {
                return Blocks.BLACKSTONE.getDefaultState();
            }
            return Blocks.NETHERRACK.getDefaultState();
        }
        if (output >= -100) {
//            return Blocks.GLASS.getDefaultState();
        }

        /*double x = pos.getX() * pos.getX() / (widthR * widthR);
        double z = pos.getZ() * pos.getZ() / (widthR * widthR);
        var horizontalDist = 1;// sqrt(x + (pos.getY() * pos.getY() / (heightR * heightR)) + z);
        var horizontalDistanceFromCenter = sqrt(x + z);

        var noise = dps.sample(blockPos.getX(), blockPos.getY(), blockPos.getZ());

//        if (noiseMultiplier != 0.0)
//            horizontalDist += ((noise * noiseMultiplier) * 0.1) * (horizontalDist * horizontalDist);

        if (horizontalDist <= 1) {
//            if (horizontalDist <= ((double) innerSphereRadius / widthR)) {
//                return Blocks.AIR.getDefaultState();
//            }
//            if (noise > -0.01) {
//                return Blocks.BLACKSTONE.getDefaultState();
//            }
//            if (noise < -0.5) {
//                return Blocks.MAGMA_BLOCK.getDefaultState();
//            }
            return Blocks.NETHERRACK.getDefaultState();
        }
        */
        return null;
    }

    private int shapeDistanceFromCenter(int radiusToRingCenter, int width, int height) {
        return (int) (radiusToRingCenter + (Math.max(width, height) / 2.0));
    }
}
