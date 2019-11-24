package me.wsman217.healthblocker.gui;

import me.wsman217.healthblocker.gui.holders.CraftingHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class StopCraftClicks implements Listener {

    @EventHandler
    public void onClickEvent(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof CraftingHolder)
            e.setCancelled(true);
    }
}
