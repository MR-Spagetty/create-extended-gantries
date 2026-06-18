package com.spag.extended_gantries.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.SubscribeEvent;

public final class ExtendedGantriesDatagen {

    private ExtendedGantriesDatagen() {
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        var existingFileHelper = event.getExistingFileHelper();
        String[] variants = { "", "_powered", "_flipped", "_powered_flipped" };

        for (String variant : variants) {
            existingFileHelper.trackGenerated(
                    ResourceLocation.fromNamespaceAndPath("create", "block/gantry_shaft" + variant),
                    PackType.CLIENT_RESOURCES,
                    ".png",
                    "textures");
        }
    }
}