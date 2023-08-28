package com.phyrenestudios.atmospheric_phenomena.items;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class APItems {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AtmosphericPhenomena.MODID);

    static {
        MeteorBlocks.registerItems();
    }
    public static final RegistryObject<Item> RAW_LONSDALEITE = ITEMS.register("raw_lonsdaleite", () -> new BlockItem(APBlocks.RAW_LONSDALEITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LONSDALEITE = ITEMS.register("lonsdaleite", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LONSDALEITE_BLOCK = ITEMS.register("lonsdaleite_block", () -> new BlockItem(APBlocks.RAW_LONSDALEITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> METEORIC_ICE = ITEMS.register("meteoric_ice", () -> new BlockItem(APBlocks.METEORIC_ICE.get(), new Item.Properties().stacksTo(64)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    //public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    //public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
    //        .alwaysEat().nutrition(1).saturationMod(2f).build())));

}
