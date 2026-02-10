package me.besser.playerplushies.blocks.playerPlushie;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PlayerPlushieBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty ON_BED = BooleanProperty.create("on_bed");

    protected static final VoxelShape SHAPE_NORMAL = Block.box(
            4.0D, 0.0D, 4.0D,
            12.0D, 16.0D, 12.0D
    );

    protected static final VoxelShape SHAPE_ON_BED = Block.box(
            4.0D, 0.0D, 4.0D,
            12.0D, 9.0D, 12.0D
    );

    public PlayerPlushieBlock(Properties properties) {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(ROTATION, 0)
                .setValue(WATERLOGGED, false)
                .setValue(ON_BED, false));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PlayerPlushieBlockEntity(pos, state);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(ON_BED) ? SHAPE_ON_BED : SHAPE_NORMAL;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;   // Do not render the block as the BER will handle it
    }


    // Block states
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        int rotation = RotationSegment.convertToSegment(context.getRotation());
        boolean isBed = context.getLevel().getBlockState(pos.below()).is(BlockTags.BEDS);

        return this.defaultBlockState()
                .setValue(ROTATION, rotation)
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
                .setValue(ON_BED, isBed);
    }

    @Override
    public @NotNull BlockState updateShape(
            BlockState state, Direction facing, BlockState facingState,
            LevelAccessor level, BlockPos currentPos, BlockPos facingPos
    ) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (facing == Direction.DOWN) {
            boolean isBed = level.getBlockState(facingPos).is(BlockTags.BEDS);
            return state.setValue(ON_BED, isBed);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, WATERLOGGED, ON_BED);
    }
}
