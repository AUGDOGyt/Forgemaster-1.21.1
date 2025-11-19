package aug.forgemaster.entity;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class GreekFireballEntityRenderer extends EntityRenderer<GreekFireballEntity> {
    public GreekFireballEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(GreekFireballEntity entity) {
        return null;
    }
}
