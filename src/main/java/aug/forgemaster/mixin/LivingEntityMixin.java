package aug.forgemaster.mixin;

import aug.forgemaster.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
@SuppressWarnings("deprecation")
public abstract class LivingEntityMixin {
    @Shadow
    public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    @Inject(
            method = "getAttributeValue",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getAttributeValue(RegistryEntry<EntityAttribute> attribute, CallbackInfoReturnable<Double> cir) {
        if (attribute.matches(EntityAttributes.GENERIC_BURNING_TIME) && hasStatusEffect(ModEffects.COOLANT)) {
            cir.setReturnValue(0d);
        }
    }
}
