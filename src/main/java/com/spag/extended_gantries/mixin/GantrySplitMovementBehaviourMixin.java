package com.spag.extended_gantries.mixin;

import java.util.Queue;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.spag.extended_gantries.ExtendedGantries;
import com.spag.extended_gantries.gantry_split.GantrySplitBlock;
import com.spag.extended_gantries.registry.BlockRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(value = Contraption.class, remap = false)
public abstract class GantrySplitMovementBehaviourMixin {
    @Shadow
    protected abstract void moveGantryShaft(Level world, BlockPos pos, Queue<BlockPos> frontier, Set<BlockPos> visited,
            BlockState state);

    @Inject(method = "moveGantryShaft", at = @At("HEAD"))
    private void gantriesShaftsStickToSplitGantries(Level world, BlockPos pos, Queue<BlockPos> frontier,
            Set<BlockPos> visited,
            BlockState state, CallbackInfo cir) {
        for (Direction d : Direction.values()) {
            BlockPos offset = pos.relative(d);
            BlockState offsetState = world.getBlockState(offset);
            Direction facing = state.getValue(GantryShaftBlock.FACING);
            if (d.getAxis() == facing.getAxis() && BlockRegistry.GANTRY_SPLIT.has(offsetState)
                    && !BlockRegistry.GANTRY_SPLIT.has(state)
                    && offsetState.getValue(GantryShaftBlock.FACING) == facing) {
                frontier.add(offset);
            }
        }
    }

    @Inject(method = "moveBlock", at = @At("HEAD"))
    private void exploreFromSplit(Level world, Direction forcedDirection, Queue<BlockPos> frontier,
            Set<BlockPos> visited, CallbackInfoReturnable<Boolean> cir) {
        BlockPos pos = frontier.peek();
        if (pos == null) {
            return;
        }
        BlockState state = world.getBlockState(pos);
        if (BlockRegistry.GANTRY_SPLIT.has(state)) {
            moveGantryShaft(world, pos, frontier, visited, state);
        }
    }
}
