package aug.forgemaster.enchantment;

public class ModEnchantmentEffects {

    public static final MapCodec<? extends EnchantmentEntityEffect> FIRE_ENGINE =
        registerEntityEffect("fire_engine", FireEngineEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> 
    registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {

        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(Forgemaster.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        Forgemaster.LOGGER.info("Registering Mod Enchantment Effects for " + Forgemaster.MOD_ID);
    }
}
