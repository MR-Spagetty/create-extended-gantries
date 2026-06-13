package com.spag.extended_gantries.gantry_split;

import com.spag.extended_gantries.registry.PartialModels.GantrySplitKey;
import com.spag.extended_gantries.registry.PartialModels;
import com.simibubi.create.content.kinetics.base.OrientedRotatingVisual;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock.Part;

import dev.engine_room.flywheel.api.visual.BlockEntityVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;

public class CustomVisual {
    public static BlockEntityVisual<? super GantrySplitBlockEntity> gantrySplit(VisualizationContext vizCont, GantrySplitBlockEntity BE, float partialTicks) {
		var blockState = BE.getBlockState();

		Part part = blockState.getValue(GantrySplitBlock.VISUAL_PART);

		boolean isPowered = blockState.getValue(GantryShaftBlock.POWERED);
		boolean isFlipped = blockState.getValue(GantryShaftBlock.FACING)
			.getAxisDirection() == AxisDirection.NEGATIVE;

		var model = Models.partial(PartialModels.GANTRY_SPLIT.get(new GantrySplitKey(part, isPowered, isFlipped)));

		return new OrientedRotatingVisual<>(vizCont, BE, partialTicks, Direction.UP, blockState.getValue(GantryShaftBlock.FACING), model);
    }
}
