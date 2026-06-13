package com.spag.extended_gantries.registry;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.spag.extended_gantries.ExtendedGantries;
import com.spag.extended_gantries.gantry_split.CustomVisual;
import com.spag.extended_gantries.gantry_split.GantrySplitBlockEntity;
import com.spag.extended_gantries.gantry_split.GantrySplitRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public interface BlockEntityRegistry {
    BlockEntityEntry<GantrySplitBlockEntity> GANTRY_SPLIT = ExtendedGantries.REGISTRATE
            .blockEntity("gantry_split", GantrySplitBlockEntity::new)
            .visual(() -> CustomVisual::gantrySplit)
            .validBlocks(BlockRegistry.GANTRY_SPLIT)
            .renderer(() -> GantrySplitRenderer::new)
            .register();

    static void register() {
    }
}
