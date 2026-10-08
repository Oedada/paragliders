package gliders.oedada.ru.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.resources.Identifier;

public class GliderModel extends EntityModel<AvatarRenderState> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            Identifier.fromNamespaceAndPath("gliders", "glider"), "main");

    public GliderModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(-8.0F, 16.0F, 8.0F));

		PartDefinition handles = root.addOrReplaceChild("handles", CubeListBuilder.create().texOffs(26, 76).addBox(5.0F, 6.0F, -6.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(8.0F, -2.25F, -9.0F, 2.0F, 2.0F, 23.0F, new CubeDeformation(0.0F))
		.texOffs(0, 72).addBox(-8.0F, -2.75F, 8.25F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(78, 71).addBox(-7.0F, 6.0F, -6.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).addBox(-10.0F, -2.25F, -9.0F, 2.0F, 2.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = handles.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(78, 58).addBox(-1.0F, -1.0F, -3.5F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.35F, -1.25F, -11.5F, 0.0F, -0.9599F, 0.0F));

		PartDefinition cube_r2 = handles.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(78, 89).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(18, 89).addBox(-15.0F, -7.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 3.75F, -6.6F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r3 = handles.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(70, 84).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(62, 84).addBox(13.0F, -9.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 4.25F, 3.15F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r4 = handles.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 76).addBox(-1.0F, -1.0F, -3.5F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.35F, -1.25F, -11.5F, 0.0F, 0.9599F, 0.0F));

		PartDefinition fabric = root.addOrReplaceChild("fabric", CubeListBuilder.create().texOffs(0, 50).addBox(5.0F, -3.25F, -8.25F, 4.0F, 1.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(96, 81).addBox(4.0F, -6.25F, -1.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 98).addBox(4.0F, -5.25F, -4.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(38, 98).addBox(4.0F, -4.25F, -6.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 98).addBox(4.0F, -3.25F, -9.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 98).addBox(3.0F, -6.25F, -2.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(98, 93).addBox(3.0F, -5.25F, -5.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(96, 98).addBox(3.0F, -4.25F, -7.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(26, 99).addBox(3.0F, -3.25F, -10.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(58, 99).addBox(2.0F, -6.25F, -3.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(90, 30).addBox(0.0F, -7.25F, -2.75F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(10, 98).addBox(2.0F, -7.25F, -1.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(44, 76).addBox(3.0F, -7.25F, -0.75F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(68, 99).addBox(2.0F, -5.25F, -6.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(100, 0).addBox(2.0F, -4.25F, -8.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(100, 5).addBox(2.0F, -3.25F, -11.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(90, 35).addBox(0.0F, -6.25F, -4.75F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(90, 40).addBox(0.0F, -5.25F, -7.25F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(90, 45).addBox(0.0F, -4.25F, -9.75F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(90, 50).addBox(0.0F, -3.25F, -12.25F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(50, 22).addBox(5.0F, -4.25F, -5.75F, 3.0F, 1.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(100, 10).addBox(4.0F, -3.25F, 8.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 80).addBox(4.0F, -6.25F, -0.75F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(100, 15).addBox(4.0F, -5.25F, 4.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(10, 102).addBox(4.0F, -4.25F, 7.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(102, 30).addBox(3.0F, -3.25F, 8.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(102, 35).addBox(3.0F, -6.25F, 3.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(102, 40).addBox(3.0F, -5.25F, 4.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(102, 45).addBox(3.0F, -4.25F, 7.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 108).addBox(2.0F, -3.25F, 8.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(48, 108).addBox(2.0F, -6.25F, 3.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(108, 70).addBox(2.0F, -5.25F, 4.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(108, 74).addBox(2.0F, -4.25F, 7.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(90, 55).addBox(0.0F, -3.25F, 8.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(108, 78).addBox(0.0F, -6.25F, 3.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 108).addBox(0.0F, -5.25F, 4.75F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(108, 95).addBox(0.0F, -4.25F, 7.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(50, 58).addBox(5.0F, -5.25F, -3.25F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(44, 84).addBox(5.0F, -6.25F, -0.75F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(86, 89).addBox(0.0F, -7.25F, 0.75F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(36, 72).addBox(0.0F, -7.75F, 1.25F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(90, 26).addBox(-4.0F, -7.75F, 1.25F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(102, 50).addBox(-5.0F, -3.25F, -9.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(102, 86).addBox(-5.0F, -4.25F, -6.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 103).addBox(-5.0F, -5.25F, -4.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(36, 103).addBox(-5.0F, -6.25F, -1.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(46, 103).addBox(-4.0F, -3.25F, -10.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 103).addBox(-4.0F, -4.25F, -7.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(88, 103).addBox(-4.0F, -5.25F, -5.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(98, 103).addBox(-4.0F, -6.25F, -2.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(20, 104).addBox(-3.0F, -3.25F, -11.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(104, 55).addBox(-3.0F, -4.25F, -8.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(56, 104).addBox(-3.0F, -5.25F, -6.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(44, 79).addBox(-4.0F, -7.25F, -0.75F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(44, 93).addBox(-2.0F, -3.25F, -12.25F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 93).addBox(-2.0F, -4.25F, -9.75F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(26, 94).addBox(-2.0F, -5.25F, -7.25F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(96, 71).addBox(-2.0F, -7.25F, -2.75F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(104, 60).addBox(-5.0F, -3.25F, 8.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(104, 65).addBox(-5.0F, -4.25F, 7.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(66, 104).addBox(-5.0F, -5.25F, 4.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(26, 85).addBox(-5.0F, -6.25F, -0.75F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(106, 20).addBox(-3.0F, -6.25F, -3.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(96, 76).addBox(-2.0F, -6.25F, -4.75F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(76, 108).addBox(-3.0F, -7.25F, -1.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(90, 22).addBox(-5.0F, -7.25F, 0.75F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(106, 25).addBox(-4.0F, -4.25F, 7.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(106, 81).addBox(-4.0F, -5.25F, 4.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(106, 98).addBox(-4.0F, -6.25F, 3.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(10, 107).addBox(-4.0F, -3.25F, 8.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(84, 108).addBox(-3.0F, -4.25F, 7.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(108, 91).addBox(-3.0F, -5.25F, 4.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(92, 108).addBox(-3.0F, -6.25F, 3.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(100, 108).addBox(-3.0F, -3.25F, 8.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(108, 103).addBox(-2.0F, -4.25F, 7.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 108).addBox(-2.0F, -5.25F, 4.75F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(108, 106).addBox(-2.0F, -6.25F, 3.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 109).addBox(-2.0F, -3.25F, 8.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(50, 40).addBox(-8.0F, -4.25F, -5.75F, 3.0F, 1.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(50, 71).addBox(-7.0F, -5.25F, -3.25F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 89).addBox(-6.0F, -6.25F, -0.75F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(50, 0).addBox(-9.0F, -3.25F, -8.25F, 4.0F, 1.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
