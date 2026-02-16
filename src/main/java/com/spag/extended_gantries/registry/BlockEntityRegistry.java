package com.spag.extended_gantries.registry;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.OrientedRotatingVisual;
import com.spag.extended_gantries.ExtendedGantries;
import com.spag.extended_gantries.gantry_split.GantrySplitBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public interface BlockEntityRegistry {
    BlockEntityEntry<GantrySplitBlockEntity> GANTRY_SPLIT = ExtendedGantries.REGISTRATE
            .blockEntity("gantry_split", GantrySplitBlockEntity::new)
            .visual(() -> OrientedRotatingVisual::gantryShaft)
            .validBlocks(BlockRegistry.GANTRY_SPLIT)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    static void register() {
    }
}
