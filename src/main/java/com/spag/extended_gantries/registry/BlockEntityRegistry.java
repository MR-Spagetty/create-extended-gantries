package com.spag.extended_gantries.registry;

import static com.spag.extended_gantries.CreateExtendedGantries.LOGGER;

import java.util.Collections;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.OrientedRotatingVisual;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.spag.extended_gantries.CreateExtendedGantries;
import com.spag.extended_gantries.gantry_split.CustomVisual;
import com.spag.extended_gantries.encased_gantry.EncasedGantryBlockEntity;
import com.spag.extended_gantries.gantry_split.GantrySplitBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.world.level.block.Block;

public interface BlockEntityRegistry {
    BlockEntityEntry<GantrySplitBlockEntity> GANTRY_SPLIT = CreateExtendedGantries.REGISTRATE
            .blockEntity("gantry_split", GantrySplitBlockEntity::new)
            .visual(() -> CustomVisual::gantrySplit, false)
            .validBlocks(BlockRegistry.GANTRY_SPLIT)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();
    BlockEntityEntry<EncasedGantryBlockEntity> ENCASED_GANTRY = CreateExtendedGantries.REGISTRATE
            .blockEntity("encased_gantry", EncasedGantryBlockEntity::new)
            .visual(() -> OrientedRotatingVisual::gantryShaft, false)
            .validBlocksDeferred(() -> Collections.unmodifiableCollection(BlockRegistry.ENCASED_GANTRIES))
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    static void register() {
        LOGGER.info("Registering block entities");
    }
}
