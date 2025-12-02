package aug.forgemaster.effect;

import aug.forgemaster.Forgemaster;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> COOLANT = register("coolant",
            new CoolantEffect(StatusEffectCategory.BENEFICIAL, 0xA8F7FF));

    public static final RegistryEntry<StatusEffect> SPARKED = register("sparked",
            new SparkedEffect(StatusEffectCategory.HARMFUL, 0xF48522));

    public static final RegistryEntry<StatusEffect> MIRROR = register("mirror",
            new MirrorEffect(StatusEffectCategory.BENEFICIAL, OxA8F7FF));

    private static RegistryEntry<StatusEffect> register(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Forgemaster.id(name), statusEffect);
    }

    public static void register() {
        Forgemaster.LOGGER.info("Registering Mod Effects for " + Forgemaster.MOD_ID);
    }
}