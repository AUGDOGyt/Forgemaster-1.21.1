package aug.forgemaster.effect;

import aug.forgemaster.Forgemaster;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> SCORCHED = registerStatusEffect("scorched",
            new ScorchedEffect(StatusEffectCategory.HARMFUL, 0x36ebab));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Forgemaster.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        Forgemaster.LOGGER.info("Registering Mod Effects for " + Forgemaster.MOD_ID);
    }
}