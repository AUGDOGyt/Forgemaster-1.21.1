package aug.forgemaster.datagen;

import aug.forgemaster.block.ModBlocks;
import aug.forgemaster.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.FIRE).add(ModBlocks.GREEK_FIRE);
        getOrCreateTagBuilder(ModTags.Blocks.SHARD_BASE)
                .addOptionalTag(BlockTags.ANVIL)
                .add(Blocks.MAGMA_BLOCK);

        getOrCreateTagBuilder(ModTags.Blocks.FIRE_REPLACEABLE)
                .addOptionalTag(BlockTags.REPLACEABLE)
                .addOptionalTag(BlockTags.FLOWERS)
                .addOptionalTag(BlockTags.SAPLINGS)
                .add(
                        Blocks.TORCH, Blocks.WALL_TORCH,
                        Blocks.SOUL_TORCH, Blocks.SOUL_WALL_TORCH
                );
    }
}
