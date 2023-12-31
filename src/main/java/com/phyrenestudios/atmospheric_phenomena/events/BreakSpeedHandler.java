package com.phyrenestudios.atmospheric_phenomena.events;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class BreakSpeedHandler {

    public static void smithingModifiers(PlayerEvent.BreakSpeed event) {
        ItemStack itemStack = event.getEntity().getMainHandItem();
        if (itemStack.getTag() == null || !itemStack.getTag().contains("modifier")) return;
        String modifierName = itemStack.getTag().getString("modifier");
        if (modifierName.contains("studded")) {
            event.setNewSpeed(event.getNewSpeed()*1.25f);
        } else if (modifierName.contains("plated")) {
            event.setNewSpeed(event.getNewSpeed()*1.25f);
        } else if (modifierName.contains("embossed")) {
            event.setNewSpeed(event.getNewSpeed()*1.25f);
        }

    }
}
