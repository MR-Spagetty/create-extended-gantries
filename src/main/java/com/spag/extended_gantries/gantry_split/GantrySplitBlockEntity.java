package com.spag.extended_gantries.gantry_split;

import com.simibubi.create.content.kinetics.gantry.GantryShaftBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class GantrySplitBlockEntity extends GantryShaftBlockEntity{

    public GantrySplitBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }
    
}
