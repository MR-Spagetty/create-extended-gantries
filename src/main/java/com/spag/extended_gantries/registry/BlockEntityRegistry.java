package com.spag.extended_gantries.registry;

import static com.spag.extended_gantries.CreateExtendedGantries.LOGGER;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.spag.extended_gantries.CreateExtendedGantries;
import com.spag.extended_gantries.gantry_split.CustomVisual;
import com.spag.extended_gantries.gantry_split.GantrySplitBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public interface BlockEntityRegistry {
    BlockEntityEntry<GantrySplitBlockEntity> GANTRY_SPLIT = CreateExtendedGantries.REGISTRATE
            .blockEntity("gantry_split", GantrySplitBlockEntity::new)
            .visual(() -> CustomVisual::gantrySplit, false)
            .validBlocks(BlockRegistry.GANTRY_SPLIT)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    static void register() {
        LOGGER.info("Registering block entities");
    }
}
