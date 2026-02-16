package com.spag.extended_gantries.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.spag.extended_gantries.registry.BlockRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(GantryShaftBlock.class)
public class GantryShaftConnectMixin {
    
    @ModifyVariable(method = "updateShape", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private boolean modifyConnect(boolean connect, BlockState state, Direction direction, BlockState neighbour,
            LevelAccessor world, BlockPos pos, BlockPos neighbourPos) {
        // If already connected by vanilla logic, keep it
        if (connect) {
            return true;
        }
        
        // Get the facing direction from the current gantry shaft
        Direction facing = state.getValue(GantryShaftBlock.FACING);
        
        // Check if the neighbor is a gantry split block with matching facing
        boolean connectToSplit = BlockRegistry.GANTRY_SPLIT.has(neighbour) 
                && neighbour.getValue(GantryShaftBlock.FACING) == facing;
        
        return connectToSplit;
    }
}
