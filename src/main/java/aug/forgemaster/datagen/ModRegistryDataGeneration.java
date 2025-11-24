package aug.forgemaster.datagen;

import aug.forgemaster.config.conditions.CraterResourceCondition;
import aug.forgemaster.world.gen.ModStructures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;


import java.util.concurrent.CompletableFuture;

public class ModRegistryDataGeneration extends FabricDynamicRegistryProvider {
    public ModRegistryDataGeneration(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.STRUCTURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.TEMPLATE_POOL));

        var str = registries.getWrapperOrThrow(RegistryKeys.STRUCTURE_SET);
        entries.add(str, ModStructures.CRATER_SET, new CraterResourceCondition());
    }

    @Override
    public String getName() {
        return "DynReg";
    }
}
