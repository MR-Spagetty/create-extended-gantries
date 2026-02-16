package com.spag.extended_gantries.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.simibubi.create.content.kinetics.gantry.GantryShaftBlockEntity;
import com.spag.extended_gantries.registry.BlockRegistry;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.world.level.block.state.BlockState;

@Mixin(value = GantryShaftBlockEntity.class, remap = false)
public class GantryShaftBEAllowSplitGantryMixin {
    @Redirect(method = {"canAssembleOn", "getPinionMovement"}, at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean permitGantrySplit(BlockEntry<?> instance, BlockState state) {
        return instance.has(state) || BlockRegistry.GANTRY_SPLIT.has(state);
    }
    
}
