package aug.forgemaster.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.ChunkRegion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static aug.forgemaster.Forgemaster.MOD_ID;

@Mixin(ChunkRegion.class)
public class ChunkRegionMixin {
    @WrapWithCondition(method = "isValidForSetBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Util;error(Ljava/lang/String;)V"))
    private boolean heal(String message) {
        return !message.contains(MOD_ID);
    }
}
