package aug.forgemaster.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

import static aug.forgemaster.Forgemaster.id;

public interface Helpers {
    static <T> RegistryKey<T> key(RegistryKey<? extends Registry<T>> registry, String id) {
        return RegistryKey.of(registry, id(id));
    }

    static boolean isAugDog(PlayerEntity player) {
        return player.getUuid().toString().equals("fc60197c-3a31-498f-83e4-df0ab4ea6026") || isDev();
    }

    static boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

}
