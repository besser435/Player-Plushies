package me.besser.playerplushies.blocks.playerPlushie;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

// TODO: fix rotation. allow for sign-like rotation.
// TODO: center model in the middle of the block. currently its a bit far back. then fix the VoxelShapeBB

public class PlayerPlushieBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;


    protected static final VoxelShape SHAPE = Block.box(
            4.0D, 0.0D, 6.0D,
            12.0D, 16.0D, 14.0D);

    public PlayerPlushieBlock(Properties properties) {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    // This logic tells Minecraft to face the block TOWARDS the player when placed
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
