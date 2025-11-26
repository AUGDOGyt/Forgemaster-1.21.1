package aug.forgemaster.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class ChandelierBlock extends Block {
    protected static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(3, 1, 3, 13, 8, 13), Block.createCuboidShape(6.5, 8, 6.5, 9.5, 16, 9.5));

    public ChandelierBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
