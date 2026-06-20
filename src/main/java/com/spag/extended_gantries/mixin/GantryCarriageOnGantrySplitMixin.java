package com.spag.extended_gantries.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.simibubi.create.content.contraptions.gantry.GantryCarriageBlock;
import com.spag.extended_gantries.Util;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.world.level.block.state.BlockState;

@Mixin(value = GantryCarriageBlock.class, remap = false)
public class GantryCarriageOnGantrySplitMixin {
    @Redirect(method = {"canSurvive", "cycleAxisIfNecessary"}, at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean permitGantrySplit(BlockEntry<?> instance, BlockState state) {
        return Util.GantryShaftHasIncAllGantryVarients(instance, state);
    }
}
