package me.besser.playerplushies.blocks.playerPlushie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class PlayerPlushieRenderer implements BlockEntityRenderer<PlayerPlushieBlockEntity, PlayerPlushieBERState> {

    public PlayerPlushieRenderer(BlockEntityRendererProvider.Context context) {

    }


    @Override
    public PlayerPlushieBERState createRenderState() {
        return new PlayerPlushieBERState();
    }

    @Override
    public void extractRenderState(PlayerPlushieBlockEntity blockEntity, PlayerPlushieBERState renderState,
                                   float partialTick, Vec3 cameraPosition,
                                   ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);


        renderState.lightPosition = blockEntity.getBlockPos();
        renderState.blockEntityLevel = blockEntity.getLevel();

        renderState.rotation = blockEntity.getRenderingRotation();


    }

    @Override
    public void submit(PlayerPlushieBERState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
                       CameraRenderState cameraRenderState) {
        BlockState state = be.getBlockState();
        Level level = Objects.requireNonNull(be.getLevel());
        BlockPos pos = be.getBlockPos();

        poseStack.pushPose();

        // Rotation
        poseStack.translate(0.5, 0.0, 0.5); // Sets the rotation axis to the middle of the block
        float angle = RotationSegment.convertToDegrees(renderState.rotation);
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
