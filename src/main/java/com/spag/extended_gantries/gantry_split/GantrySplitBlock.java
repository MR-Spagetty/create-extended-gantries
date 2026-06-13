package com.spag.extended_gantries.gantry_split;

import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntityTicker;
import com.spag.extended_gantries.registry.BlockEntityRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class GantrySplitBlock extends GantryShaftBlock {

    public static final Property<Part> VISUAL_PART = EnumProperty.create("visual_part", Part.class);

    public GantrySplitBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(VISUAL_PART, Part.SINGLE));
    }
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        // TODO Auto-generated method stub
        super.createBlockStateDefinition(builder.add(VISUAL_PART));
    }

    @Override
    public BlockEntityType<? extends GantryShaftBlockEntity> getBlockEntityType() {
        return BlockEntityRegistry.GANTRY_SPLIT.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegistry.GANTRY_SPLIT.get().create(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> type) {
        return new SmartBlockEntityTicker<>();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbour, LevelAccessor world,
            BlockPos pos, BlockPos neighbourPos) {Direction facing = state.getValue(FACING);
		Axis axis = facing.getAxis();
		if (direction.getAxis() != axis)
			return super.updateShape(state, direction, neighbour, world, pos, neighbourPos);
		boolean connect = neighbour.getBlock() instanceof GantryShaftBlock && !(neighbour.getBlock() instanceof GantrySplitBlock) && neighbour.getValue(FACING) == facing;

		Part part = state.getValue(VISUAL_PART);
		if (direction.getAxisDirection() == facing.getAxisDirection()) {
			if (connect) {
				if (part == Part.END)
					part = Part.MIDDLE;
				if (part == Part.SINGLE)
					part = Part.START;
			} else {
				if (part == Part.MIDDLE)
					part = Part.END;
				if (part == Part.START)
					part = Part.SINGLE;
			}
		} else {
			if (connect) {
				if (part == Part.START)
					part = Part.MIDDLE;
				if (part == Part.SINGLE)
					part = Part.END;
			} else {
				if (part == Part.MIDDLE)
					part = Part.START;
				if (part == Part.END)
					part = Part.SINGLE;
			}
		}
        return super.updateShape(state.setValue(VISUAL_PART, part), direction, neighbour, world, pos, neighbourPos);
    }
}
