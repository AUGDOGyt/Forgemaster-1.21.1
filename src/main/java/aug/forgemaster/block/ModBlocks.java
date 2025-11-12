package aug.forgemaster.block;

import aug.forgemaster.Forgemaster;
import aug.forgemaster.item.ModItemGroups;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;


public class ModBlocks {

    private static final Block SHARD_OF_ATTACCA_BLOCK = registerBlock("shard_of_attacca_block",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.ANCIENT_DEBRIS)
                    .noCollision()));

    private static Block registerBlock(String name, Block block) {
        registerBlock(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Forgemaster.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Forgemaster.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Forgemaster.LOGGER.info("Registering Mod Blocks for " + Forgemaster.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.FORGEMASTER).register(entries -> {
            entries.add(ModBlocks.SHARD_OF_ATTACCA_BLOCK);
        });
    }
}
