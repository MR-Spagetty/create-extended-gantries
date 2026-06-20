package com.spag.extended_gantries;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.spag.extended_gantries.registry.BlockRegistry;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
public class Util {
    public static boolean GantryShaftHasIncAllGantryVarients(BlockEntry<?> instance, BlockState state) {
        return instance.has(state) || (instance == AllBlocks.GANTRY_SHAFT && state.getBlock() instanceof GantryShaftBlock);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(CreateExtendedGantries.MODID, path);
    }

    public record BlockNamePair(String name, Block block) {
        public Block get() {
            return block;
        }

        public BlockNamePair(BlockEntry<?> blockEntry) {
            this(blockEntry.getRegisteredName(), blockEntry.get());
        }
    }
}
