package aug.forgemaster.block.entity;

import aug.forgemaster.Forgemaster;
import aug.forgemaster.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Forgemaster.id("pedestal"),
                    BlockEntityType.Builder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build(null));

    public static void registerBlockEntities() {
        Forgemaster.LOGGER.info("Registering Block Entities for " + Forgemaster.MOD_ID);
    }
}

