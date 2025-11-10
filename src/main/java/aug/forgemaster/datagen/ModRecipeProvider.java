package aug.forgemaster.datagen;

import aug.forgemaster.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SHARD_OF_ATTACCA, 1)
                .pattern(" N ")
                .pattern("NFN")
                .pattern(" N ")
                .input('N', Items.NETHERITE_SCRAP)
                .input('F', Items.FIRE_CHARGE)
                .criterion("has_shard_of_attacca", FabricRecipeProvider.conditionsFromItem((ModItems.SHARD_OF_ATTACCA)))
                .offerTo(recipeExporter, Identifier.of("shard_of_attacca"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BROKEN_BLADE_OF_ATTACCA, 1)
                .pattern("SS ")
                .pattern("SS ")
                .pattern("SS ")
                .input('S', ModItems.SHARD_OF_ATTACCA)
                .criterion("has_shard_of_attacca", FabricRecipeProvider.conditionsFromItem(ModItems.SHARD_OF_ATTACCA))
                .offerTo(recipeExporter, Identifier.of("broken_blade_of_attacca"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1)
                .pattern("DID")
                .pattern("DND")
                .pattern("DDD")
                .input('D', Items.DIAMOND)
                .input('I', Items.NETHERITE_INGOT)
                .input('N', Blocks.NETHERRACK)
                .criterion("has_netherite_ingot", FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(recipeExporter, Identifier.of("netherite_upgrade_template_nc"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ATTACCA, 1)
                .pattern(" B ")
                .pattern(" B ")
                .pattern(" S ")
                .input('B', ModItems.BROKEN_BLADE_OF_ATTACCA)
                .input('S', Items.STICK)
                .criterion("has_broken_blade_of_attacca", FabricRecipeProvider.conditionsFromItem(ModItems.BROKEN_BLADE_OF_ATTACCA))
                .offerTo(recipeExporter, Identifier.of("attacca"));
    }
}
