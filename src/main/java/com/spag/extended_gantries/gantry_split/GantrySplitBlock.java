package com.spag.extended_gantries.gantry_split;

import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntityTicker;
import com.spag.extended_gantries.registry.BlockEntityRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class GantrySplitBlock extends GantryShaftBlock {

    public GantrySplitBlock(Properties properties) {
        super(properties);
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
            BlockPos pos, BlockPos neighbourPos) {
        return super.updateShape(state, direction, neighbour, world, pos, neighbourPos);
    }
}
