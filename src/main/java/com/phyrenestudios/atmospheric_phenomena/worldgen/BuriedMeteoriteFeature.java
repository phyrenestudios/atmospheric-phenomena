package com.phyrenestudios.atmospheric_phenomena.worldgen;


import com.mojang.serialization.Codec;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import com.phyrenestudios.atmospheric_phenomena.util.FeatureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

public class BuriedMeteoriteFeature extends Feature<NoneFeatureConfiguration> {
    public BuriedMeteoriteFeature(Codec<NoneFeatureConfiguration> p_i49915_1_) {
        super(p_i49915_1_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        WorldGenLevel levelIn = context.level();
        BlockPos posIn = context.origin();
        RandomSource rand = levelIn.getRandom();
        BlockState target = levelIn.getBlockState(posIn.below());
        if (!target.is(APTags.Blocks.VALID_METEORITE_SPAWN)) return false;

        int size = 2;
        FeatureUtils.populateBlockCollections();

        List<BlockPos> centerList = getCenters(rand, posIn, size, 3);
        centerList = burriedPositions(centerList, rand);
        buildMeteor(levelIn, rand, centerList, size);
        return true;
    }

    private void buildMeteor(WorldGenLevel levelIn, RandomSource rand, List<BlockPos> centerList, int size) {
        Block meteor = FeatureUtils.meteorBlockCollection.getRandomElement();
        Block coreMeteor;
        if (rand.nextFloat() < Config.solidCoreMeteoriteChance) {
            coreMeteor = meteor;
        } else {
            coreMeteor = FeatureUtils.meteorCoreBlockCollection.getRandomElement();
        }

        for (BlockPos center : centerList) {
            int j = 1 + rand.nextInt(size);
            int k = 1 + rand.nextInt(size);
            int l = 1 + rand.nextInt(size);
            float f = (float) (j + k + l) * 0.333F + 0.75F;
            for (BlockPos blockpos : BlockPos.betweenClosed(center.offset(-size - 1, -size - 1, -size - 1), center.offset(size + 1, size + 1, size + 1))) {
                if (shortestDistance(blockpos, centerList, size) <= (double) (f * f) * 0.3) {
                    if (levelIn.getBlockState(blockpos).is(BlockTags.FEATURES_CANNOT_REPLACE)) continue;
                    levelIn.setBlock(blockpos, coreMeteor.defaultBlockState(), 3);
                } else if (shortestDistance(blockpos, centerList, size) <= (double) (f * f)) {
                    if (levelIn.getBlockState(blockpos).is(BlockTags.FEATURES_CANNOT_REPLACE)) continue;
                    levelIn.setBlock(blockpos, meteor.defaultBlockState(), 3);
                }
            }
        }
    }

    private double shortestDistance(BlockPos posIn, List<BlockPos> centerList, int size) {
        double minDist = 100D;
        for (BlockPos center : centerList) {
            minDist = Math.min(posIn.distSqr(center.below(size)), minDist);
        }
        return minDist;
    }

    private List<BlockPos> getCenters(RandomSource rand, BlockPos posIn, int size, int count) {
        List<BlockPos> list = new ArrayList<>();
        BlockPos pos = posIn.below(2);
        for (int i = 1; i <= count; i++) {
            list.add(pos.offset(rand.nextInt(3)-1, rand.nextInt(3)-1, rand.nextInt(3)-1));
        }
        return list;
    }

    private List<BlockPos> burriedPositions(List<BlockPos> centers, RandomSource rand) {
        List<BlockPos> list = new ArrayList<>();
        int depth = rand.nextInt(6)+2;
        for (BlockPos pos : centers) {
            list.add(pos.below(depth));
        }
        return list;
    }


    private BlockPos getCenterPos(List<BlockPos> centerList) {
        int Xs = 0;
        int Ys = 0;
        int Zs = 0;

        for (BlockPos center : centerList) {
            Xs += center.getX();
            Ys += center.getY();
            Zs += center.getZ();
        }
        return new BlockPos(Xs /centerList.size(), Ys /centerList.size(), Zs /centerList.size());
    }


}