package com.phyrenestudios.atmospheric_phenomena.data.tags;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class APBiomeTagsProvider extends BiomeTagsProvider {
    public APBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
        super(output, registries, AtmosphericPhenomena.MODID, fileHelper);
    }

    public String getName() {
        return AtmosphericPhenomena.MODID + " - Biome Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        //tag(APTags.Biomes.METEORITE_BLACKLIST).add();
    }
}
