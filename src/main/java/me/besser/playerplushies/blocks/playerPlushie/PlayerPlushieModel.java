package me.besser.playerplushies.blocks.playerPlushie;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class PlayerPlushieModel {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(
                    ResourceLocation.fromNamespaceAndPath("playerplushies", "plushie"),
                    "main"
            );

    private final ModelPart group;

    public PlayerPlushieModel(ModelPart root) {
        this.group = root.getChild("group");
    }

    public void render(ModelPart root,
                       com.mojang.blaze3d.vertex.PoseStack poseStack,
                       com.mojang.blaze3d.vertex.VertexConsumer consumer,
                       int light,
                       int overlay) {
        group.render(poseStack, consumer, light, overlay);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition group = partdefinition.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition Head = group.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -16.0F, -1.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(28, 0).addBox(-7.5F, -16.5F, -1.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 8.0F, -3.0F));

        PartDefinition Torso = group.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(16, 18).addBox(-6.0F, -8.0F, 0.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(18, 32).addBox(-6.5F, -8.5F, -0.5F, 7.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 8.0F, -3.0F));

        PartDefinition Limbs = group.addOrReplaceChild("Limbs", CubeListBuilder.create(), PartPose.offset(3.0F, 8.0F, -3.0F));

        PartDefinition LeftLimbs = Limbs.addOrReplaceChild("LeftLimbs", CubeListBuilder.create(), PartPose.offset(-3.0F, -8.0F, 3.0F));

        PartDefinition ArmLeftOuter_r1 = LeftLimbs.addOrReplaceChild("ArmLeftOuter_r1", CubeListBuilder.create().texOffs(42, 53).addBox(-7.0475F, -4.5F, 2.8024F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-6.5475F, -4.0F, 3.3024F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, 0.0F, -0.3927F, 0.0F));

        PartDefinition LegLeftOuter_r1 = LeftLimbs.addOrReplaceChild("LegLeftOuter_r1", CubeListBuilder.create().texOffs(0, 48).addBox(-4.2716F, 0.5F, -5.648F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(-3.8478F, 1.0F, -4.7654F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, 0.0F, 0.3927F, 0.0F));

        PartDefinition RightLimbs = Limbs.addOrReplaceChild("RightLimbs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition ArmRightOuter_r1 = RightLimbs.addOrReplaceChild("ArmRightOuter_r1", CubeListBuilder.create().texOffs(52, 64).addBox(0.0475F, -4.5F, 2.8024F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(17, 53).addBox(0.5475F, -4.0F, 3.3024F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.0F, -1.0F, 0.0F, 0.3927F, 0.0F));

        PartDefinition LegRightOuter_r1 = RightLimbs.addOrReplaceChild("LegRightOuter_r1", CubeListBuilder.create().texOffs(0, 64).addBox(0.2716F, 0.5F, -5.648F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(16, 64).addBox(0.7716F, 1.0F, -5.148F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}