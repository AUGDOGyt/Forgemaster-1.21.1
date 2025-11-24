package aug.forgemaster.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.SideShapeType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import static aug.forgemaster.Forgemaster.id;

public interface Helpers {
    static <T> RegistryKey<T> key(RegistryKey<? extends Registry<T>> registry, String id) {
        return RegistryKey.of(registry, id(id));
    }

    static boolean isAugDog(PlayerEntity player) {
        return player.getUuid().toString().equals("fc60197c-3a31-498f-83e4-df0ab4ea6026") || isDev();
    }

    static boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    static boolean canPlaceFireAt(BlockState state, World world, BlockPos pos) {
        if (world.isOutOfHeightLimit(pos)) {
            return false;
        }

        return (!state.isSolidBlock(world, pos) && state.isIn(ModTags.Blocks.FIRE_REPLACEABLE))
                && state.getCollisionShape(world, pos).isEmpty() && state.getFluidState().isEmpty()
                && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), Direction.UP, SideShapeType.FULL);
    }
}
