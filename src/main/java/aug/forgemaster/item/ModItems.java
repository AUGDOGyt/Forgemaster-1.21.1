package aug.forgemaster.item;

import aug.forgemaster.Forgemaster;
import aug.forgemaster.item.custom.AttaccaSword;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.item.ToolMaterials.NETHERITE;

public class ModItems {

    public static final Item SHARD_OF_ATTACCA = registerItem("shard_of_attacca", new Item(new Item.Settings()));
    public static final Item BROKEN_BLADE_OF_ATTACCA = registerItem("broken_blade_of_attacca", new Item(new Item.Settings()));

    public static final Item ATTACCA = registerItem("attacca", new AttaccaSword(new Item.Settings()
        .attributeModifiers(SwordItem.createAttributeModifiers(NETHERITE, 8, -3.2f))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Forgemaster.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Forgemaster.LOGGER.info("Registering Mod Items for " + Forgemaster.MOD_ID);

    }
}
