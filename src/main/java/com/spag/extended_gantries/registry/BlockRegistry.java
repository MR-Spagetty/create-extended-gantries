package com.spag.extended_gantries.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.spag.extended_gantries.ExtendedGantries;
import com.spag.extended_gantries.gantry_split.GantrySplitBlock;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.client.model.generators.ModelFile;

public interface BlockRegistry {
    public static final BlockEntry<GantrySplitBlock> GANTRY_SPLIT = ExtendedGantries.REGISTRATE
            .block("gantry_split", GantrySplitBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.NETHER).forceSolidOn())
            .transform(com.simibubi.create.foundation.data.TagGen.axeOrPickaxe())
            // TODO make this more specifc to my block
            .blockstate((c, p) -> p.directionalBlock(c.get(), s -> {
                boolean isPowered = s.getValue(GantryShaftBlock.POWERED);
                boolean isFlipped = s.getValue(GantryShaftBlock.FACING)
                        .getAxisDirection() == AxisDirection.NEGATIVE;
                String partName = s.getValue(GantryShaftBlock.PART)
                        .getSerializedName();
                String flipped = isFlipped ? "_flipped" : "";
                String powered = isPowered ? "_powered" : "";
                ModelFile existing = AssetLookup.partialBaseModel(c, p, partName);
                if (!isPowered && !isFlipped)
                    return existing;
                return p.models()
                        .withExistingParent("block/" + c.getName() + "_" + partName + powered + flipped,
                                existing.getLocation())
                        .texture("2", p.modLoc("block/" + c.getName() + powered + flipped));
            })).item()
            .transform(com.simibubi.create.foundation.data.ModelGen.customItemModel("_", "block_single")).register();

    public static void register() {
    }

    public static void registerStressImpact() {
        BlockStressValues.IMPACTS.register(GANTRY_SPLIT.get(),
                () -> BlockStressValues.getImpact(AllBlocks.GANTRY_SHAFT.get()));
    }
}
