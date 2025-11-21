package aug.forgemaster.damage_type;

import aug.forgemaster.Forgemaster;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> ATTACCA = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Forgemaster.id("attacca"));
    public static final RegistryKey<DamageType> GREEK_FIREBALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Forgemaster.id("greek_fireball"));
}
