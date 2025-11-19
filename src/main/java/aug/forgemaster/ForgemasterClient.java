package aug.forgemaster;

import aug.forgemaster.block.ModBlocks;
import aug.forgemaster.entity.GreekFireballEntityRenderer;
import aug.forgemaster.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class ForgemasterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GREEK_FIRE, RenderLayer.getCutout());
        EntityRendererRegistry.register(ModEntities.GREEK_FIREBALL, GreekFireballEntityRenderer::new);
    }
}
