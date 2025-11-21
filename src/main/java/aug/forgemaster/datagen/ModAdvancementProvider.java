package aug.forgemaster.datagen;

import aug.forgemaster.Forgemaster;
import aug.forgemaster.block.ModBlocks;
import aug.forgemaster.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    protected ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry getShard = Advancement.Builder.create()
                .display(
                        ModBlocks.ATTACCA_SHARD,
                        Text.translatable("advancements.forgemaster.get_shard.title"),
                        Text.translatable("advancements.forgemaster.get_shard.description"),
                        Identifier.of("textures/gui/advancements/backgrounds/nether.png"),
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        true
                )
                .criterion("get_shard", InventoryChangedCriterion.Conditions.items(ModBlocks.ATTACCA_SHARD))
                .build(Forgemaster.id("get_shard"));

        AdvancementEntry getBrokenBlade = Advancement.Builder.create()
                .parent(getShard)
                .display(
                        ModItems.BROKEN_ATTACCA,
                        Text.translatable("advancements.forgemaster.get_broken_blade.title"),
                        Text.translatable("advancements.forgemaster.get_broken_blade.description"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        true
                )
                .criterion("get_broken_blade", InventoryChangedCriterion.Conditions.items(ModItems.BROKEN_ATTACCA))
                .build(Forgemaster.id("get_broken"));

        AdvancementEntry getBlade = Advancement.Builder.create()
                .parent(getBrokenBlade)
                .display(
                        ModItems.ATTACCA,
                        Text.translatable("advancements.forgemaster.get_attacca.title"),
                        Text.translatable("advancements.forgemaster.get_attacca.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .criterion("get_attacca", InventoryChangedCriterion.Conditions.items(ModItems.ATTACCA))
                .build(Forgemaster.id("get_attacca"));

        consumer.accept(getShard);
        consumer.accept(getBrokenBlade);
        consumer.accept(getBlade);
    }
}