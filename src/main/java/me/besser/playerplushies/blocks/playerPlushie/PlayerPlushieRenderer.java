package me.besser.playerplushies.blocks.playerPlushie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.neoforged.neoforge.client.model.data.ModelData;

import java.util.Objects;

public class PlayerPlushieRenderer implements BlockEntityRenderer<PlayerPlushieBlockEntity> {
    private final BlockRenderDispatcher blockRenderer;

    public PlayerPlushieRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(
            PlayerPlushieBlockEntity be,
            float partialTick,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int combinedLight,
            int combinedOverlay
    ) {
        BlockState state = be.getBlockState();
        Level level = Objects.requireNonNull(be.getLevel());
        BlockPos pos = be.getBlockPos();

        poseStack.pushPose();

        // Rotation
        poseStack.translate(0.5, 0.0, 0.5); // Sets the rotation axis to the middle of the block
        float angle = RotationSegment.convertToDegrees(state.getValue(PlayerPlushieBlock.ROTATION));
        poseStack.mulPose(Axis.YP.rotationDegrees(-angle));

        // Bed offset
        if (state.getValue(PlayerPlushieBlock.ON_BED)) {
            poseStack.translate(0.0, -7.0 / 16.0, 0.0);  // Bed is 9 pixels lower
        }

        poseStack.translate(-0.5, 0.0, -0.5);

        BakedModel model = this.blockRenderer.getBlockModel(state);
        RandomSource random = level.random;

        for (RenderType renderType : model.getRenderTypes(state, random, ModelData.EMPTY)) {
            blockRenderer.getModelRenderer().tesselateBlock(
                    level,
                    model,
                    state,
                    pos,
                    poseStack,
                    buffer.getBuffer(renderType),
                    false,
                    random,
                    state.getSeed(pos),
                    combinedOverlay,
                    ModelData.EMPTY,
                    renderType
            );
        }

        poseStack.popPose();
    }
}
