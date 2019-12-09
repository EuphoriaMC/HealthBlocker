package me.wsman217.healthblocker.listeners;

import me.wsman217.healthblocker.commands.EuphoriaRanks;
import me.wsman217.healthblocker.items.CustomItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ClickListeners implements Listener {

    private String[] ranks = EuphoriaRanks.ranks;

    @EventHandler
    public void onNPCClickEvent(PlayerInteractEntityEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND)
            return;

        Player p = e.getPlayer();
        Entity entity = e.getRightClicked();
        String entityName = entity.getName();

        String rank = "";
        for (String str : ranks) {
            if (!str.equalsIgnoreCase(entityName))
                continue;
            rank = str;
            break;
        }
        if (rank.length() < 1)
            return;
        sendCommand(rank, p.getName());
    }

    @EventHandler
    public void onPlayerRightClickCustomFood(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND)
            return;

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getAction() == Action.RIGHT_CLICK_AIR) {
            return;
        }
        if (e.getItem() == null)
            return;
        ItemStack item = e.getItem();
        if (CustomItemHandler.getByItemStack(item) == null) {
            return;
        }

        Player p = e.getPlayer();
        int foodLevel = p.getFoodLevel();
        if (foodLevel != 20)
            return;
        if (p.getHealth() >= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())
            return;

        p.setFoodLevel(19);
        System.out.println(p.getFoodLevel());
    }

    private void sendCommand(String rank, String player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "euphoriaranks " + rank + " " + player);
    }
}
