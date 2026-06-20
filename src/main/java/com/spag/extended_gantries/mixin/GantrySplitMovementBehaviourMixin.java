package com.spag.extended_gantries.mixin;

import java.util.Queue;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.spag.extended_gantries.Util;
import com.spag.extended_gantries.gantry_split.GantrySplitBlock;
import com.spag.extended_gantries.registry.BlockRegistry;
import com.tterrag.registrate.util.entry.BlockEntry;

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
    @Redirect(method = {"moveBlock", "moveGantryPinion"}, at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean permitGantryVarients(BlockEntry<?> instance, BlockState state) {
        return Util.GantryShaftHasIncAllGantryVarients(instance, state);
    }

    @Redirect(method = "moveGantryShaft", at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean permitGantryNonSplitVarientsSticky(BlockEntry<?> instance, BlockState state) {
        return Util.GantryShaftHasIncAllGantryVarients(instance, state) && !(state.getBlock() instanceof GantrySplitBlock);
    }
}
