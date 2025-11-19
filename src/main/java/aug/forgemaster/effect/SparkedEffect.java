package aug.forgemaster.effect;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SparkedEffect extends StatusEffect {
    public static InGameHud.HeartType HEART_TYPE;

    public SparkedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
}