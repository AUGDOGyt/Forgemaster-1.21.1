package aug.forgemaster.enchantment;

import aug.forgemaster.Forgemaster;
import com.mojang.serialization.MapCodec;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEnchantmentEffects {

    public static final ComponentType<EnchantmentValueEffect> FIRE_ENGINE_STRENGTH = Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Forgemaster.id("fire_engine_strength"), ComponentType.<EnchantmentValueEffect>builder().codec(EnchantmentValueEffect.CODEC).build());

    private static MapCodec<? extends EnchantmentEntityEffect> register(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {

        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Forgemaster.id(name), codec);
    }

    public static void register() {
        Forgemaster.LOGGER.info("Registering Mod Enchantment Effects for " + Forgemaster.MOD_ID);
    }
}
