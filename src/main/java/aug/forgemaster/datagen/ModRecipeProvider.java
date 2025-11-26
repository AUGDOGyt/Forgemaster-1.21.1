package aug.forgemaster.datagen;

import aug.forgemaster.Forgemaster;
import aug.forgemaster.block.ModBlocks;
import aug.forgemaster.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BROKEN_ATTACCA, 1)
                .pattern("SS")
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.ATTACCA_SHARD)
                .criterion("has_attacca_shard", FabricRecipeProvider.conditionsFromItem(ModBlocks.ATTACCA_SHARD))
                .offerTo(recipeExporter, Forgemaster.id("broken_attacca"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ATTACCA, 1)
                .pattern("B")
                .pattern("B")
                .pattern("S")
                .input('B', ModItems.BROKEN_ATTACCA)
                .input('S', Items.STICK)
                .criterion("has_broken_attacca", FabricRecipeProvider.conditionsFromItem(ModItems.BROKEN_ATTACCA))
                .offerTo(recipeExporter, Forgemaster.id("attacca"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HAMMER, 1)
                .pattern("DDD")
                .pattern("DSD")
                .pattern(" S ")
                .input('D', Items.DIAMOND)
                .input('S', Items.STICK)
                .criterion("has_diamond", FabricRecipeProvider.conditionsFromItem(Items.DIAMOND))
                .offerTo(recipeExporter, Forgemaster.id("hammer"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.GOLD_CHANDELIER, 1)
                .pattern(" H ")
                .pattern("CIC")
                .input('H', Items.CHAIN)
                .input('C', ItemTags.CANDLES)
                .input('I', Items.GOLD_INGOT)
                .criterion("has_candle", FabricRecipeProvider.conditionsFromTag(ItemTags.CANDLES))
                .offerTo(recipeExporter, Forgemaster.id("gold_chandelier"));
    }
}
