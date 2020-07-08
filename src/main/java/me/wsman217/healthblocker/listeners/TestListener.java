package me.wsman217.healthblocker.listeners;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TestListener implements Listener {

    @EventHandler
    public void onItemClickEvent(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (item == null)
            return;
        NBTItem nbtItem = new NBTItem(item);
        System.out.println(nbtItem.getCompound());
    }
}
