package aug.forgemaster.datagen;

import aug.forgemaster.block.ModBlocks;
import aug.forgemaster.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {
        When when = When.create()
                .set(Properties.NORTH, false)
                .set(Properties.EAST, false)
                .set(Properties.SOUTH, false)
                .set(Properties.WEST, false)
                .set(Properties.UP, false);
        List<Identifier> floor = gen.getFireFloorModels(ModBlocks.GREEK_FIRE);
        List<Identifier> side = gen.getFireSideModels(ModBlocks.GREEK_FIRE);
        List<Identifier> up = gen.getFireUpModels(ModBlocks.GREEK_FIRE);
        gen.blockStateCollector
                .accept(
                        MultipartBlockStateSupplier.create(ModBlocks.GREEK_FIRE)
                                .with(when, BlockStateModelGenerator.buildBlockStateVariants(floor, blockStateVariant -> blockStateVariant))
                                .with(When.anyOf(When.create().set(Properties.NORTH, true), when), BlockStateModelGenerator.buildBlockStateVariants(side, blockStateVariant -> blockStateVariant))
                                .with(
                                        When.anyOf(When.create().set(Properties.EAST, true), when),
                                        BlockStateModelGenerator.buildBlockStateVariants(side, blockStateVariant -> blockStateVariant.put(VariantSettings.Y, VariantSettings.Rotation.R90))
                                )
                                .with(
                                        When.anyOf(When.create().set(Properties.SOUTH, true), when),
                                        BlockStateModelGenerator.buildBlockStateVariants(side, blockStateVariant -> blockStateVariant.put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                )
                                .with(
                                        When.anyOf(When.create().set(Properties.WEST, true), when),
                                        BlockStateModelGenerator.buildBlockStateVariants(side, blockStateVariant -> blockStateVariant.put(VariantSettings.Y, VariantSettings.Rotation.R270))
                                )
                                .with(When.create().set(Properties.UP, true), BlockStateModelGenerator.buildBlockStateVariants(up, blockStateVariant -> blockStateVariant))
                );
    }

    @Override
    public void generateItemModels(ItemModelGenerator gen) {
        gen.register(ModBlocks.ATTACCA_SHARD.asItem(), Models.GENERATED);
        gen.register(ModItems.BROKEN_ATTACCA, Models.GENERATED);
    }
}
