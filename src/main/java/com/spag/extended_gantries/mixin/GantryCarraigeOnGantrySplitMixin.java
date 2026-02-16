package com.spag.extended_gantries.mixin;

import org.apache.commons.compress.harmony.pack200.NewAttributeBands.Call;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.simibubi.create.content.contraptions.gantry.GantryCarriageBlock;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.spag.extended_gantries.ExtendedGantries;
import com.spag.extended_gantries.registry.BlockRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(GantryCarriageBlock.class)
public class GantryCarraigeOnGantrySplitMixin {

    @Inject(method = "canSurvive", at = @At("TAIL"),locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void canSurviveOnGantrySplitMixin(BlockState state, LevelReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir, Direction direction, BlockState shaft) {
        if (BlockRegistry.GANTRY_SPLIT.has(shaft) && shaft.getValue(GantryShaftBlock.FACING).getAxis() != direction.getAxis()) {
            cir.cancel();
            cir.setReturnValue(true);
        }
    }
}
