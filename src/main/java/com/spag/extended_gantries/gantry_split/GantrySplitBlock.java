package com.spag.extended_gantries.gantry_split;

import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class GantrySplitBlock extends GantryShaftBlock{

    public GantrySplitBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbour, LevelAccessor world,
            BlockPos pos, BlockPos neighbourPos) {
        // TODO Auto-generated method stub
        return super.updateShape(state, direction, neighbour, world, pos, neighbourPos);
    }
}
