package com.spag.extended_gantries;

import com.simibubi.create.AllBlocks;
import com.spag.extended_gantries.registry.BlockRegistry;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
public class Util {
    public static boolean GantryShaftHasIncGantrySplit(BlockEntry<?> instance, BlockState state) {
        return instance.has(state) || (instance == AllBlocks.GANTRY_SHAFT && BlockRegistry.GANTRY_SPLIT.has(state));
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(CreateExtendedGantries.MODID, path);
    }
}
