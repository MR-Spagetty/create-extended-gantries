package com.spag.extended_gantries.gantry_split;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;
import com.mojang.blaze3d.vertex.PoseStack;

public class GantrySplitRenderer extends SafeBlockEntityRenderer<GantrySplitBlockEntity> {

    public GantrySplitRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    protected void renderSafe(GantrySplitBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
            int light, int overlay) {
        BlockState state = be.getBlockState();
        KineticBlockEntityRenderer.renderRotatingKineticBlock(be, state, ms, buffer.getBuffer(RenderType.solid()), light);
    }
}