package aug.forgemaster.item;

import aug.forgemaster.Forgemaster;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SHARD_OF_ATTACCA = registerItem("shard_of_attacca", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Forgemaster.MOD_ID, name), item)
    }

    public static void registerModItems() {
        Forgemaster.LOGGER.info("Registering Mod Items for " + Forgemaster.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroup.INGREDIENTS).register(entries -> {
            entries.add(SHARD_OF_ATTACCA);
        });
    }
}
