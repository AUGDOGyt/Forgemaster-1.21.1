package aug.forgemaster.item;

import aug.forgemaster.Forgemaster;

public class ModItemGroups {

    public static final ItemGroup FORGEMASTER = Registry.register(Registires.ITEM_GROUP, 
        Identifier.of(Forgemaster.MOD_ID, "forgemaster"),
        FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SHARD_OF_ATTACCA)
            .displayName(Text.translatable("itemgroup.forgemaster.forgemaster"))
            .entries(displayContext, entries) -> {
                entries.add(ModItems.SHARD_OF_ATTACCA);
            }).build());

    public static void registerModItemGroups() {
        Forgemaster.LOGGER.info("Registering Item Groups for " + Forgemaster.MOD_ID);
    }
}