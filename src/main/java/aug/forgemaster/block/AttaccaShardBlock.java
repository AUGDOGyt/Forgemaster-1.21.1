package aug.forgemaster.block;

import aug.forgemaster.util.ModTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import static aug.forgemaster.util.Helpers.isAugDog;

public class AttaccaShardBlock extends HorizontalFacingBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final MapCodec<AttaccaShardBlock> CODEC = createCodec(AttaccaShardBlock::new);

    public AttaccaShardBlock(Settings settings) {
        super(settings);
        setDefaultState(stateManager.getDefaultState().with(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var state = super.getPlacementState(ctx);
        if (state == null) return null;
        var fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return state
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (isAugDog(player) &&
                stack.isOf(Items.NETHERITE_SCRAP) && stack.getCount() >= 4
                && !player.getItemCooldownManager().isCoolingDown(stack.getItem())
                && world.getBlockState(pos.down()).isIn(ModTags.Blocks.SHARD_BASE)) {
            var random = world.random;
            player.getItemCooldownManager().set(stack.getItem(), 20);
            stack.decrementUnlessCreative(4, player);

            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            for (int i = 0; i < 40; i++) {
                world.addParticle(ParticleTypes.SMOKE,
                        pos.getX() + random.nextDouble(),
                        pos.getY() + random.nextDouble() - 0.1,
                        pos.getZ() + random.nextDouble(),
                        0.0, 0.0, 0.0
                );
            }
            for (int i = 0; i < 8; i++) {
                world.addParticle(ParticleTypes.FLAME,
                        pos.getX() + (random.nextDouble() / 2) + 0.25,
                        pos.getY() + (random.nextDouble() / 2),
                        pos.getZ() + (random.nextDouble() / 2) + 0.25,
                        0.0, 0.0, 0.0
                );
            }
            if (world instanceof ServerWorld serverWorld) {
                var item = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5, ModBlocks.ATTACCA_SHARD.asItem().getDefaultStack());
                item.setVelocity(0, 0.25, 0);
                serverWorld.spawnEntity(item);
            }

        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        builder.add(FACING);
    }

    @Override
    public void randomDisplayTick(BlockState stateOg, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(stateOg, world, pos, random);
        var state = world.getBlockState(pos.down());
        if (state.isOf(Blocks.MAGMA_BLOCK) || state.isOf(Blocks.NETHERRACK) || state.isOf(Blocks.BLACKSTONE)) {
            world.addParticle(ParticleTypes.SMOKE,
                    pos.getX() + random.nextDouble(),
                    pos.getY() + 0.3,
                    pos.getZ() + random.nextDouble(),
                    0.0, 0.0, 0.0);
        }
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(4, 0, 4, 12, 6, 12);
    }
}
