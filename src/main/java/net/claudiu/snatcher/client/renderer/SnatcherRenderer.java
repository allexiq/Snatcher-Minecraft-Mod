package net.claudiu.snatcher.client.renderer;

import net.claudiu.snatcher.Snatcher;
import net.claudiu.snatcher.client.model.SnatcherModel;
import net.claudiu.snatcher.entity.SnatcherEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.resources.ResourceLocation;

public class SnatcherRenderer extends MobRenderer<SnatcherEntity, EndermanRenderState, SnatcherModel> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Snatcher.MOD_ID, "textures/entity/snatcher.png");

    public SnatcherRenderer(EntityRendererProvider.Context context) {
        super(context, new SnatcherModel(context.bakeLayer(SnatcherModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EndermanRenderState state) {
        return TEXTURE;
    }

    @Override
    public EndermanRenderState createRenderState() {
        return new EndermanRenderState();
    }
}
