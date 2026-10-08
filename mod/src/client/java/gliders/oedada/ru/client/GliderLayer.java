package gliders.oedada.ru.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class GliderLayer extends RenderLayer<AvatarRenderState, PlayerModel> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath("gliders", "textures/entity/glider.png");
    private final GliderModel model;

    public GliderLayer(RenderLayerParent<AvatarRenderState, PlayerModel> parent,
                       EntityModelSet modelSet) {
        super(parent);
        this.model = new GliderModel(modelSet.bakeLayer(GliderModel.LAYER));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords,
                       AvatarRenderState state, float yRot, float xRot) {
        model.setupAnim(state);
        collector.submitModel(model, state, poseStack,
                RenderTypes.entityCutout(TEXTURE),
                lightCoords, OverlayTexture.NO_OVERLAY, -1);
    }
}
