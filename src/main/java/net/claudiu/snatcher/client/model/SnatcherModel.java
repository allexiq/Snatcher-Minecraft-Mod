package net.claudiu.snatcher.client.model;

import net.claudiu.snatcher.Snatcher;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.resources.ResourceLocation;

public class SnatcherModel extends EndermanModel<EndermanRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Snatcher.MOD_ID, "snatcher"), "main");

    public SnatcherModel(ModelPart root) {
        super(root);
    }
    public static LayerDefinition createBodyLayer() {
        // Folosește EndermanModel.createBodyLayer() ca bază
        return EndermanModel.createBodyLayer();
    }
}
