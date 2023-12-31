package com.phyrenestudios.atmospheric_phenomena.data.tags;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class APBlockTagsProvider extends BlockTagsProvider {
    public APBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
        super(output, registries, AtmosphericPhenomena.MODID, fileHelper);
    }

    public String getName() {
        return AtmosphericPhenomena.MODID + " - Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        for (MeteorBlocks base : MeteorBlocks.values()) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(base.getMeteorBlock(),base.getBricks(),base.getBricksSlab(),base.getBricksStairs(),base.getBricksWall(),base.getChiseled());
            tag(BlockTags.NEEDS_IRON_TOOL).add(base.getMeteorBlock(),base.getBricks(),base.getBricksSlab(),base.getBricksStairs(),base.getBricksWall(),base.getChiseled());
            tag(APTags.Blocks.COMMON_METEOR_BLOCKS).add(base.getMeteorBlock());
            tag(BlockTags.SLABS).add(base.getBricksSlab());
            tag(BlockTags.STAIRS).add(base.getBricksStairs());
            tag(BlockTags.WALLS).add(base.getBricksWall());
        }
        for (TektiteBlocks base : TektiteBlocks.values()) {
            tag(APTags.Blocks.TEKTITES).add(base.getTektite());
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(base.getTektite());
            tag(BlockTags.NEEDS_IRON_TOOL).add(base.getTektite());
            tag(APTags.Blocks.METEORITE_STREWN_BLOCKS).add(base.getTektite());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(base.getGlass());
            tag(APTags.Blocks.LIGHTNING_GLASS).add(base.getGlass());
        }
        for (CapsuleBlocks base : CapsuleBlocks.values()) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(base.getCapsule());
            tag(BlockTags.NEEDS_DIAMOND_TOOL).add(base.getCapsule());
            tag(BlockTags.FEATURES_CANNOT_REPLACE).add(base.getCapsule());
            tag(APTags.Blocks.CAPSULES).add(base.getCapsule());
        }
        tag(APTags.Blocks.CHARRED_LOGS).add(APBlocks.CHARRED_LOG.get(),APBlocks.STRIPPED_CHARRED_LOG.get(),APBlocks.CHARRED_WOOD.get(),APBlocks.STRIPPED_CHARRED_WOOD.get());
        tag(APTags.Blocks.SMOULDERING_LOGS).add(APBlocks.SMOULDERING_LOG.get(),APBlocks.SMOULDERING_WOOD.get());
        tag(APTags.Blocks.BURNING_LOGS).add(APBlocks.BURNING_LOG.get(),APBlocks.BURNING_WOOD.get());
        tag(BlockTags.LOGS_THAT_BURN).addTag(APTags.Blocks.CHARRED_LOGS).addTag(APTags.Blocks.SMOULDERING_LOGS);
        tag(BlockTags.PLANKS).add(APBlocks.CHARRED_PLANKS.get());
        tag(BlockTags.WOODEN_SLABS).add(APBlocks.CHARRED_SLAB.get());
        tag(BlockTags.WOODEN_STAIRS).add(APBlocks.CHARRED_STAIRS.get());
        tag(BlockTags.WOODEN_FENCES).add(APBlocks.CHARRED_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(APBlocks.CHARRED_FENCE_GATE.get());
        tag(BlockTags.WOODEN_DOORS).add(APBlocks.CHARRED_DOOR.get());
        tag(BlockTags.WOODEN_TRAPDOORS).add(APBlocks.CHARRED_TRAPDOOR.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(APBlocks.CHARRED_PRESSURE_PLATE.get());
        tag(BlockTags.WOODEN_BUTTONS).add(APBlocks.CHARRED_BUTTON.get());
        tag(BlockTags.STANDING_SIGNS).add(APBlocks.CHARRED_SIGN.get());
        tag(BlockTags.WALL_SIGNS).add(APBlocks.CHARRED_WALL_SIGN.get());
        tag(BlockTags.CEILING_HANGING_SIGNS).add(APBlocks.CHARRED_HANGING_SIGN.get());
        tag(BlockTags.WALL_HANGING_SIGNS).add(APBlocks.CHARRED_WALL_HANGING_SIGN.get());
        tag(BlockTags.MINEABLE_WITH_AXE).addTag(APTags.Blocks.CHARRED_LOGS).addTag(APTags.Blocks.SMOULDERING_LOGS).add(
                APBlocks.CHARRED_PLANKS.get(),
                APBlocks.CHARRED_SLAB.get(),
                APBlocks.CHARRED_STAIRS.get(),
                APBlocks.CHARRED_FENCE.get(),
                APBlocks.CHARRED_FENCE_GATE.get(),
                APBlocks.CHARRED_DOOR.get(),
                APBlocks.CHARRED_TRAPDOOR.get(),
                APBlocks.CHARRED_PRESSURE_PLATE.get(),
                APBlocks.CHARRED_BUTTON.get(),
                APBlocks.CHARRED_SIGN.get(),
                APBlocks.CHARRED_WALL_SIGN.get(),
                APBlocks.CHARRED_BOOKSHELF.get());
        tag(Tags.Blocks.GLASS).addTag(APTags.Blocks.TEKTITES).addTag(APTags.Blocks.LIGHTNING_GLASS);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get(), APBlocks.GOLDEN_MATRIX.get(), APBlocks.QUARTZ_MATRIX.get(), APBlocks.CHARGED_QUARTZ_MATRIX.get(), APBlocks.DEBRIS_MATRIX.get(), APBlocks.LONSDALEITE_MATRIX.get(), APBlocks.LONSDALEITE_BLOCK.get(), APBlocks.MOISSANITE_BLOCK.get(), APBlocks.METEORIC_IRON_BLOCK.get(), APBlocks.METEORIC_ICE.get(), APBlocks.SOIL_FULGURITE.get(), APBlocks.STONE_FULGURITE.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get(), APBlocks.GOLDEN_MATRIX.get(), APBlocks.QUARTZ_MATRIX.get(), APBlocks.CHARGED_QUARTZ_MATRIX.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(APBlocks.LONSDALEITE_MATRIX.get(), APBlocks.DEBRIS_MATRIX.get());
        tag(APTags.Blocks.UNCOMMON_METEOR_BLOCKS).addTag(Tags.Blocks.OBSIDIAN);
        tag(APTags.Blocks.RARE_METEOR_BLOCKS).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get());
        tag(APTags.Blocks.VERY_RARE_METEOR_BLOCKS).add(Blocks.CRYING_OBSIDIAN, Blocks.END_STONE);
        tag(APTags.Blocks.COMMON_METEOR_CORE_BLOCKS).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get());
        tag(APTags.Blocks.UNCOMMON_METEOR_CORE_BLOCKS).add(APBlocks.GOLDEN_MATRIX.get(), APBlocks.QUARTZ_MATRIX.get(), APBlocks.METEORIC_ICE.get());
        tag(APTags.Blocks.RARE_METEOR_CORE_BLOCKS).add(APBlocks.LONSDALEITE_MATRIX.get());
        tag(APTags.Blocks.VERY_RARE_METEOR_CORE_BLOCKS).add(APBlocks.DEBRIS_MATRIX.get());
        tag(Tags.Blocks.STORAGE_BLOCKS_DIAMOND).add(APBlocks.LONSDALEITE_BLOCK.get());
        tag(BlockTags.ICE).add(APBlocks.METEORIC_ICE.get());
        tag(APTags.Blocks.VALID_METEORITE_SPAWN).addTags(Tags.Blocks.STONE,BlockTags.DIRT,BlockTags.SNOW,BlockTags.SAND,Tags.Blocks.SANDSTONE,Tags.Blocks.OBSIDIAN,Tags.Blocks.GRAVEL,Tags.Blocks.COBBLESTONE,Tags.Blocks.ORES,BlockTags.TERRACOTTA).add(Blocks.DIRT_PATH, Blocks.CLAY);
        tag(APTags.Blocks.METEOROID_DESTROY).addTags(BlockTags.LOGS, BlockTags.LEAVES);
        tag(APTags.Blocks.VITRIFIES_TO_STONE_FULGURITE).addTags(Tags.Blocks.STONE,Tags.Blocks.SANDSTONE,Tags.Blocks.GRAVEL,Tags.Blocks.COBBLESTONE,BlockTags.STONE_BRICKS);
        tag(APTags.Blocks.VITRIFIES_TO_SOIL_FULGURITE).addTags(BlockTags.DIRT).add(Blocks.CLAY);
        tag(APTags.Blocks.VITRIFIES_TO_GLASS).addTags(BlockTags.SAND);
        tag(APTags.Blocks.LIGHTNING_CONDUCTIVE).addTags(Tags.Blocks.STORAGE_BLOCKS_COPPER, Tags.Blocks.STORAGE_BLOCKS_IRON, Tags.Blocks.STORAGE_BLOCKS_GOLD, Tags.Blocks.STORAGE_BLOCKS_REDSTONE).add(Blocks.CUT_COPPER, Blocks.EXPOSED_COPPER, Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_COPPER, Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_COPPER, Blocks.OXIDIZED_CUT_COPPER, Blocks.WAXED_COPPER_BLOCK, Blocks.WAXED_CUT_COPPER, Blocks.WAXED_EXPOSED_COPPER, Blocks.WAXED_EXPOSED_CUT_COPPER, Blocks.WAXED_WEATHERED_COPPER, Blocks.WAXED_WEATHERED_CUT_COPPER, Blocks.WAXED_OXIDIZED_COPPER, Blocks.WAXED_OXIDIZED_CUT_COPPER);

    }
}
