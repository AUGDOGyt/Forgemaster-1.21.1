package aug.forgemaster.util;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

import static aug.forgemaster.Forgemaster.id;

public interface Helpers {
    static <T> RegistryKey<T> key(RegistryKey<? extends Registry<T>> registry, String id) {
        return RegistryKey.of(registry, id(id));
    }
}
