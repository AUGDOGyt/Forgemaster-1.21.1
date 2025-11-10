package aug.forgemaster.enchantment;

public class ModEnchantments {

    public static final RegistryKey<Enchantment> FIRE_ENGINE = 
        

    private static void register(Registerable<Enhchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
