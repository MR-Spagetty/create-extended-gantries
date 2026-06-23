package com.spag.extended_gantries.registry;

import static com.spag.extended_gantries.CreateExtendedGantries.LOGGER;
import static com.spag.extended_gantries.CreateExtendedGantries.REGISTRATE;

import java.util.List;
import java.util.stream.Stream;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.kinetics.gantry.GantryShaftBlock;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.spag.extended_gantries.CreateExtendedGantries;
import com.spag.extended_gantries.Util;
import com.spag.extended_gantries.Util.BlockNamePair;
import com.spag.extended_gantries.encased_gantry.EncasedGantryBlock;
import com.spag.extended_gantries.gantry_split.GantrySplitBlock;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.Tags;

public interface BlockRegistry {
    public static final BlockEntry<GantrySplitBlock> GANTRY_SPLIT = CreateExtendedGantries.REGISTRATE
            .block("gantry_split", GantrySplitBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.NETHER).forceSolidOn())
            .transform(TagGen.axeOrPickaxe())
            .blockstate((c, p) -> p.directionalBlock(c.get(), s -> {
                boolean isPowered = s.getValue(GantryShaftBlock.POWERED);
                boolean isFlipped = s.getValue(GantryShaftBlock.FACING)
                        .getAxisDirection() == AxisDirection.NEGATIVE;
                String partName = s.getValue(GantrySplitBlock.VISUAL_PART)
                        .getSerializedName();
                String flipped = isFlipped ? "_flipped" : "";
                String powered = isPowered ? "_powered" : "";
                ModelFile existing = AssetLookup.partialBaseModel(c, p, partName);
                if (!isPowered && !isFlipped)
                    return existing;
                return p.models()
                        .withExistingParent("block/" + c.getName() + "_" + partName + powered + flipped,
                                existing.getLocation())
                        .texture("2", "create:block/gantry_shaft" + powered + flipped);
            })).item().recipe((c, p) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, c.get(), 8)
                .define('A', AllItems.ANDESITE_ALLOY)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .define('I', Tags.Items.NUGGETS_IRON)
                .pattern(" A ")
                .pattern("IRI")
                .pattern(" A ")
                .unlockedBy("has_gantry", RegistrateRecipeProvider.has(AllBlocks.GANTRY_SHAFT.get()))
                .save(p, Util.asResource("crafting/"+c.getName()));
            })
            .transform(ModelGen.customItemModel("_", "block_single")).register();

    public static final List<BlockNamePair> ENCASED_GANTRY_TYPES = List.of(
        new BlockNamePair("white_concrete", Blocks.WHITE_CONCRETE),
        new BlockNamePair("light_gray_concrete", Blocks.LIGHT_GRAY_CONCRETE),
        new BlockNamePair("gray_concrete", Blocks.GRAY_CONCRETE),
        new BlockNamePair("black_concrete", Blocks.BLACK_CONCRETE),
        new BlockNamePair("brown_concrete", Blocks.BROWN_CONCRETE),
        new BlockNamePair("red_concrete", Blocks.RED_CONCRETE),
        new BlockNamePair("orange_concrete", Blocks.ORANGE_CONCRETE),
        new BlockNamePair("yellow_concrete", Blocks.YELLOW_CONCRETE),
        new BlockNamePair("lime_concrete", Blocks.LIME_CONCRETE),
        new BlockNamePair("green_concrete", Blocks.GREEN_CONCRETE),
        new BlockNamePair("cyan_concrete", Blocks.CYAN_CONCRETE),
        new BlockNamePair("light_blue_concrete", Blocks.LIGHT_BLUE_CONCRETE),
        new BlockNamePair("blue_concrete", Blocks.BLUE_CONCRETE),
        new BlockNamePair("purple_concrete", Blocks.PURPLE_CONCRETE),
        new BlockNamePair("magenta_concrete", Blocks.MAGENTA_CONCRETE),
        new BlockNamePair("pink_concrete", Blocks.PINK_CONCRETE)
    );
    public static final List<BlockEntry<EncasedGantryBlock>> ENCASED_GANTRIES = Stream.concat(
        /* for some reason when having these create block based ones teh following error occurs:
        ! Caused by: net.neoforged.fml.ModLoadingException: Loading errors encountered:
        - Create: Extended Gantries (extendedgantries) has failed to load correctly
          java.lang.ExceptionInInitializerError: null*/
        /*Stream.of(
        AllBlocks.ANDESITE_CASING, AllBlocks.BRASS_CASING, AllBlocks.COPPER_CASING, AllBlocks.INDUSTRIAL_IRON_BLOCK
    ).map(block -> CreateExtendedGantries.REGISTRATE
            .block(block.getRegisteredName() + "_encased_gantry", p -> new EncasedGantryBlock(p, block.get()))
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.NETHER).forceSolidOn())
            .transform(TagGen.axeOrPickaxe())
            .blockstate((c, p) -> p.directionalBlock(c.get(), s -> {
                boolean isPowered = s.getValue(GantryShaftBlock.POWERED);
                boolean isFlipped = s.getValue(GantryShaftBlock.FACING)
                        .getAxisDirection() == AxisDirection.NEGATIVE;
                String partName = s.getValue(GantryShaftBlock.PART).getSerializedName();
                String flipped = isFlipped ? "_flipped" : "";
                String powered = isPowered ? "_powered" : "";
                ModelFile existing = p.models().getExistingFile(p.modLoc("block/encased_gantry/" + partName));
                // if (!isPowered && !isFlipped)
                //     return existing;
                return p.models()
                        .withExistingParent("block/" + c.getName() + "/block_" + partName + powered + flipped,
                                existing.getLocation())
                        .texture("casing", "create:block/" + block.getRegisteredName())
                        .texture("2", "create:block/gantry_shaft" + powered + flipped);
            })).item()
            .transform(ModelGen.customItemModel("_", "block_single"))
            .register()
    )*/ Stream.of(), ENCASED_GANTRY_TYPES.stream()
    ).map(block -> CreateExtendedGantries.REGISTRATE
            .block(block.name() + "_encased_gantry", p -> new EncasedGantryBlock(p, block.get()))
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.NETHER).forceSolidOn())
            .transform(TagGen.axeOrPickaxe())
            .blockstate((c, p) -> p.directionalBlock(c.get(), s -> {
                boolean isPowered = s.getValue(GantryShaftBlock.POWERED);
                boolean isFlipped = s.getValue(GantryShaftBlock.FACING)
                        .getAxisDirection() == AxisDirection.NEGATIVE;
                String partName = s.getValue(GantryShaftBlock.PART).getSerializedName();
                String flipped = isFlipped ? "_flipped" : "";
                String powered = isPowered ? "_powered" : "";
                ModelFile existing = p.models().getExistingFile(p.modLoc("block/encased_gantry/" + partName));
                // if (!isPowered && !isFlipped)
                //     return existing;
                return p.models()
                        .withExistingParent("block/" + c.getName() + "/block_" + partName + powered + flipped,
                                existing.getLocation())
                        .texture("casing", "minecraft:block/" + block.name())
                        .texture("2", "create:block/gantry_shaft" + powered + flipped);
            })).item()
            .transform(ModelGen.customItemModel("_", "block_single"))
            .register()
    ).toList();

    public static void register() {
        LOGGER.info("Registering blocks");
    }

    public static void registerStressImpact() {
        BlockStressValues.IMPACTS.register(GANTRY_SPLIT.get(),
                () -> BlockStressValues.getImpact(AllBlocks.GANTRY_SHAFT.get()));
    }
}
