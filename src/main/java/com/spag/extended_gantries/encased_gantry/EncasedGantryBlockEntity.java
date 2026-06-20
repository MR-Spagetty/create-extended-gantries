package com.spag.extended_gantries.encased_gantry;

import com.simibubi.create.content.kinetics.gantry.GantryShaftBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EncasedGantryBlockEntity extends GantryShaftBlockEntity{

    public EncasedGantryBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }
    
}
