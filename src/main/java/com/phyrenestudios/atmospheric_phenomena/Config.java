package com.phyrenestudios.atmospheric_phenomena;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.DoubleValue CRATER_MAGMA_CHANCE = BUILDER
            .comment("The chance for a meteorite crater to generate magma blocks.")
            .defineInRange("crateMagmaChance", 0.2D, 0.0D, 1.0D);
    private static final ForgeConfigSpec.DoubleValue BURRIED_METEORITE_CHANCE = BUILDER
            .comment("The chance for a meteorite to generate with a filled in crater")
            .defineInRange("burriedMeteoriteChance", 0.3D, 0.0D, 1.0D);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static double crateMagmaChance;
    public static double burriedMeteoriteChance;


    private static boolean validateItemName(final Object obj) {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        crateMagmaChance = CRATER_MAGMA_CHANCE.get();
        burriedMeteoriteChance = BURRIED_METEORITE_CHANCE.get();

    }
}
