package aug.forgemaster.item;

import aug.forgemaster.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class HammerItem extends SwordItem implements DualModelItem {
    public HammerItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        target.removeStatusEffect(StatusEffects.FIRE_RESISTANCE);
        target.setFrozenTicks(100);

        return true;
    }

    @Override
    public Identifier worldModel() {
        return Registries.ITEM.getId(this).withSuffixedPath("_3d");
    }

    @Override
    public Identifier guiModel() {
        return Registries.ITEM.getId(this).withSuffixedPath("_2d");
    }
}
