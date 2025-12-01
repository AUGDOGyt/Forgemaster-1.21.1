package aug.forgemaster.item;

import aug.forgemaster.Forgemaster;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.List;

public class ModItems {

    public static final Item BROKEN_ATTACCA = registerItem("broken_attacca", new Item(new Item.Settings().fireproof().component(DataComponentTypes.LORE, new LoreComponent(List.of(Text.translatable("item.forgemaster.broken_attacca.desc"))))));
    public static final Item ATTACCA = registerItem("attacca", new AttaccaItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 4, -2.8f)).component(DataComponentTypes.LORE, new LoreComponent(List.of(Text.translatable("item.forgemaster.attacca.desc"))))));

    public static final Item VULCANITE_NUGGET = registerItem("vulcanite_nugget", new Item(new Item.Settings().fireproof()));

    //active charms
    public static final Item MIRROR_CHARM = registerItem("mirror_charm", new Item(new Item.Settings().component(DataComponentTypes.LORE, new LoreComponent(List.of(Text.translatable("item.forgemaster.mirror_charm.desc")))).component(ModItemComponentTypes.ACTIVE_CHARM, true)));
    public static final Item WIND_CHARM = registerItem("wind_charm", new Item(new Item.Settings().component(DataComponentTypes.LORE, new LoreComponent(List.of(Text.translatable("item.forgemaster.wind_charm.desc")))).component(ModItemComponentTypes.ACTIVE_CHARM, true)))
   
    //passive charms
    public static final Item SILENCE_CHARM = registerItem("silence_charm", new Item(new Item.Settings().component(DataComponentTypes.LORE, new LoreComponent(List.of(Text.translatable("item.forgemaster.silence_charm.desc")))).component(ModItemComponentTypes.ACTIVE_CHARM, false)));
    public static final Item WARD_CHARM = registerItem("ward_charm", new Item(new Item.Settings()))

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Forgemaster.id(name), item);
    }

    public static void register() {
        Forgemaster.LOGGER.info("Registering Mod Items for " + Forgemaster.MOD_ID);
    }
}
