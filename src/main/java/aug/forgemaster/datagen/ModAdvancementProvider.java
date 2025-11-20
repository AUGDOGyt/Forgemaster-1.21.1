package aug.forgemaster.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    protected ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementHolder getShard = Advancement.Builder.advancement()
		.display(
				ModBlocks.ATTACCA_SHARD, 
				Text.literal("Shard of Flames"), 
				Text.literal("Found a Shard of Attacca in a meteorite"), 
				ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/adventure.png"), // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
				AdvancementFrame.GOAL, 
				true,
				true, 
				true 
		)
		.criterion("got_shard", InventoryChangedCriterion.Conditions.items(ModBlocks.ATTACCA_SHARD))
		.build(consumer, Forgemaster.MOD_ID + "/get_shard");

        AdvancementHolder getBrBlade = Advancement.Builder.advancement()
        .parent(getShard)
		.display(
				ModItems.BROKEN_ATTACCA, 
				Text.literal("Shattered Ferocity"),
				Text.literal("Reforged half of the shattered blade, Attacca"), 
				null, 
				AdvancementFrame.GOAL, 
				true, 
				true, 
				true 
		)
		.criterion("got_br_blade", InventoryChangedCriterion.Conditions.items(ModItems.BROKEN_ATTACCA))
		.build(consumer, Forgemaster.MOD_ID + "/get_br_blade");

        AdvancementHolder getBlade = Advancement.Builder.advancement()
        .parent(getBrBlade)
		.display(
				ModItems.ATTACCA, 
				Text.literal("Champion of The Forge"), 
				Text.literal("Completed the shattered sword, Attacca"), 
				null, 
				AdvancementFrame.CHALLENGE, 
				true, 
				true,
				true
        )
		.criterion("got_attacca", InventoryChangedCriterion.Conditions.items(ModItems.ATTACCA))
		.build(consumer, Forgemaster.MOD_ID + "/get_attacca");
    }
}