package aug.forgemaster.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class GreekFireBlock extends AbstractFireBlock {
    public static final MapCodec<GreekFireBlock> CODEC = createCodec(GreekFireBlock::new);

    public GreekFireBlock(Settings settings) {
        super(settings, 1);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.UP, Properties.NORTH, Properties.EAST, Properties.SOUTH, Properties.WEST);
    }

    @Override
    protected MapCodec<? extends AbstractFireBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        return canPlaceAt(state, world, pos) ? getDefaultState() : Blocks.AIR.getDefaultState();
    }

    @Override
    protected boolean isFlammable(BlockState state) {
        return true;
    }
}