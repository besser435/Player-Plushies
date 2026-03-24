package me.besser.playerplushies.blocks.playerPlushie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class PlayerPlushieRenderer implements BlockEntityRenderer<PlayerPlushieBlockEntity> {

    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(
                    ResourceLocation.fromNamespaceAndPath("playerplushies", "plushie"),
                    "main"
            );

    private final ModelPart root;

    public PlayerPlushieRenderer(BlockEntityRendererProvider.Context context) {
        this.root = context.bakeLayer(LAYER);
    }

    @Override
    public void render(
            PlayerPlushieBlockEntity be,
            float partialTick,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int combinedLight,
            int combinedOverlay,
            Vec3 cameraPos
    ) {
        BlockState state = be.getBlockState();
        BlockPos pos = be.getBlockPos();

        poseStack.pushPose();

        // Rotation
        poseStack.translate(0.5, 0.0, 0.5);  // Sets the rotation axis to the middle of the block
        float angle = RotationSegment.convertToDegrees(
                state.getValue(PlayerPlushieBlock.ROTATION)
        );
        poseStack.mulPose(Axis.YP.rotationDegrees(-angle));

        // Bed offset
        if (state.getValue(PlayerPlushieBlock.ON_BED)) {
            poseStack.translate(0.0, -7.0 / 16.0, 0.0);  // Bed is 9 pixels lower
        }

        // Scale + flip to match entity-style rendering
        poseStack.scale(-1.0F, -1.0F, 1.0F);

        // Get skin
        ResourceLocation skin = getSkin(be.getPlayerUUID());

        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(skin));

        // Render model
        PlayerPlushieModel model = new PlayerPlushieModel(root);
        model.render(root, poseStack, consumer, combinedLight, combinedOverlay);

        poseStack.popPose();
    }

    private ResourceLocation getSkin(UUID uuid) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.getConnection() != null) {
            PlayerInfo info = mc.getConnection().getPlayerInfo(uuid);
            if (info != null) {
                return info.getSkin().texture();
            }
        }

        return DefaultPlayerSkin.getDefaultSkin().texture();
    }
}