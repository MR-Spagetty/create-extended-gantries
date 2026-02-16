package com.spag.extended_gantries.mixin;

import java.util.Queue;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.spag.extended_gantries.ExtendedGantries;
import com.spag.extended_gantries.gantry_split.GantrySplitBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(Contraption.class)
public abstract class GantrySplitMixin {
    @Shadow
    protected abstract void moveGantryShaft(Level world, BlockPos pos, Queue<BlockPos> frontier, Set<BlockPos> visited,
            BlockState state);

    @Inject(method = "moveBlock", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;getBlockEntity(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;",
            ordinal = 0, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private void exploreFromSplit(Level world, Direction forcedDirection, Queue<BlockPos> frontier,
            Set<BlockPos> visited, CallbackInfoReturnable<Boolean> cir, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        ExtendedGantries.LOGGER.debug(state.getValue(GantryShaftBlock.FACING).toString());
        if (state.getBlock() instanceof GantrySplitBlock) {
            moveGantryShaft(world, pos, frontier, visited, state);
        }
    }
}
