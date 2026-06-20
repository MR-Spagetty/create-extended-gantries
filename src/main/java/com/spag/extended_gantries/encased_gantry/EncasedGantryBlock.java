package com.spag.extended_gantries.encased_gantry;

import com.simibubi.create.content.decoration.encasing.EncasedBlock;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntityTicker;
import com.spag.extended_gantries.registry.BlockEntityRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class EncasedGantryBlock extends GantryShaftBlock {

    public final Block shellBlock;
    public EncasedGantryBlock(Properties properties, Block shellBlock) {
        super(properties);
        this.shellBlock = shellBlock;
    }

    @Override
    public BlockEntityType<? extends GantryShaftBlockEntity> getBlockEntityType() {
        return BlockEntityRegistry.ENCASED_GANTRY.get();
    }
}
