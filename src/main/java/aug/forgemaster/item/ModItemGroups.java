package aug.forgemaster.item;

import aug.forgemaster.Forgemaster;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup FORGEMASTER = Registry.register(Registries.ITEM_GROUP,
        Identifier.of(Forgemaster.MOD_ID, "forgemaster"),
        FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SHARD_OF_ATTACCA))
            .displayName(Text.translatable("itemgroup.forgemaster.forgemaster"))
            .entries((displayContext, entries) -> {
                entries.add(ModItems.SHARD_OF_ATTACCA);
                entries.add(ModItems.ATTACCA);
            }).build());

    public static void registerModItemGroups() {
        Forgemaster.LOGGER.info("Registering Item Groups for " + Forgemaster.MOD_ID);
    }
}