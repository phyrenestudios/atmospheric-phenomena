package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.data.advancements.APAdvancementProvider;
import com.phyrenestudios.atmospheric_phenomena.data.lang.APEnUsLangProvider;
import com.phyrenestudios.atmospheric_phenomena.data.loot.APLootTableSubProvider;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APBlockTagsProvider;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APDamageTypesTagsProvider;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APItemTagProvider;
import com.phyrenestudios.atmospheric_phenomena.init.APDamageTypes;
import com.phyrenestudios.atmospheric_phenomena.worldgen.APFeatures;
import com.phyrenestudios.atmospheric_phenomena.worldgen.APPlacements;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        gen.addProvider(true, new APBlockstateProvider(packOutput, helper));
        gen.addProvider(true, new APItemModelProvider(packOutput, helper));
        gen.addProvider(event.includeClient(), new APEnUsLangProvider(packOutput, "en_us"));
        gen.addProvider(event.includeServer(), new APLootTableSubProvider(packOutput));
        APBlockTagsProvider blockTags = new APBlockTagsProvider(packOutput, provider, helper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new APItemTagProvider(packOutput, provider, blockTags.contentsGetter(), helper));
        //gen.addProvider(event.includeServer(), new APBiomeTagsProvider(packOutput, provider, helper));
        gen.addProvider(event.includeServer(), new APRecipesProvider(packOutput));
        gen.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, CompletableFuture.supplyAsync(DataGenerators::getProvider), Set.of(AtmosphericPhenomena.MODID)));

        gen.addProvider(event.includeServer(), new APDamageTypesTagsProvider(packOutput, provider, helper));
        gen.addProvider(event.includeServer(), new ForgeAdvancementProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper(), List.of(new APAdvancementProvider())));
    }

    private static HolderLookup.Provider getProvider() {
        final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
        registryBuilder.add(Registries.BIOME, context -> {});
        registryBuilder.add(Registries.TRIM_MATERIAL, APTrimMaterials::bootstrap);
        registryBuilder.add(Registries.DAMAGE_TYPE, APDamageTypes::bootstrap);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, APFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, APPlacements::bootstrap);
        registryBuilder.add(ForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
            HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);

            context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(AtmosphericPhenomena.MODID, "overworld_meteorite")), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biomeGetter.getOrThrow(BiomeTags.IS_OVERWORLD),
                    HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(APPlacements.OVERWORLD_METEORITE)),
                    GenerationStep.Decoration.LOCAL_MODIFICATIONS
            ));
            context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(AtmosphericPhenomena.MODID, "buried_meteorite")), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biomeGetter.getOrThrow(BiomeTags.IS_OVERWORLD),
                    HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(APPlacements.BURIED_METEORITE)),
                    GenerationStep.Decoration.UNDERGROUND_DECORATION
            ));
            //context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(AtmosphericPhenomena.MODID, "large_crater")), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
           //         biomeGetter.getOrThrow(BiomeTags.IS_OVERWORLD),
            //        HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(APPlacements.LARGE_CRATER)),
            //        GenerationStep.Decoration.RAW_GENERATION
            //));

        });
        RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
        return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
    }



}
