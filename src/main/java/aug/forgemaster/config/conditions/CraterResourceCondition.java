package aug.forgemaster.config.conditions;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import static aug.forgemaster.Forgemaster.*;


public record CraterResourceCondition() implements ResourceCondition {
    public static final MapCodec<CraterResourceCondition> CODEC = MapCodec.unit(new CraterResourceCondition());

    @Override
    public ResourceConditionType<?> getType() {
        return CRATER;
    }

    @Override
    public boolean test(@Nullable RegistryWrapper.WrapperLookup registryLookup) {
        return CONFIG.spawnCraters;
    }

    public static final ResourceConditionType<CraterResourceCondition> CRATER =
            ResourceConditionType.create(id("crater"), CraterResourceCondition.CODEC);

    public static void register() {
        ResourceConditions.register(CRATER);
    }
}
