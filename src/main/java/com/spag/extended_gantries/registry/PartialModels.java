package com.spag.extended_gantries.registry;

import java.util.HashMap;
import java.util.Map;

import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.spag.extended_gantries.Util;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.data.Iterate;
import net.minecraft.resources.ResourceLocation;

public class PartialModels {
    public static final PartialModel
    GANTRY_SPLIT_START = block("gantry_split/block_start"),
    GANTRY_SPLIT_MIDDLE = block("gantry_split/block_middle"),
    GANTRY_SPLIT_END = block("gantry_split/block_end"),
    GANTRY_SPLIT_SINGLE = block("gantry_split/block_single");

    public static final Map<GantrySplitKey, PartialModel> GANTRY_SPLIT = new HashMap<>();

    static {
        for (boolean flipped : Iterate.trueAndFalse){
            for (boolean powered : Iterate.trueAndFalse){
                for (GantryShaftBlock.Part part : GantryShaftBlock.Part.values()) {
                    GantrySplitKey key = new GantrySplitKey(part, powered, flipped);
                    GANTRY_SPLIT.put(key, PartialModel.of(key.name()));
                }
            }
}
    }

    public record GantrySplitKey(GantryShaftBlock.Part part, boolean powered, boolean flipped) {
        private ResourceLocation name() {
            String partName = part.getSerializedName();
            if (!(this.powered || this.flipped)){
                return Util.asResource("block/gantry_split/block_" + partName);
            }
            String flipped = this.flipped ? "_flipped" : "";
            String powered = this.powered ? "_powered" : "";
            return Util.asResource("block/gantry_split_" + partName + powered + flipped);
        }
    }

    private static PartialModel block(String path) {
        return PartialModel.of(Util.asResource(path));
    }

    public static void init() {}
}
