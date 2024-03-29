package me.wsman217.healthblocker.listeners;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.commands.EuphoriaRanks;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

    private final String[] ranks = EuphoriaRanks.ranks;

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
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }
        if (e.getItem() == null)
            return;
        ItemStack item = e.getItem();
        if (CustomFoodHandler.getByItemStack(item) == null) {
            return;
        }

        Player p = e.getPlayer();
        int foodLevel = p.getFoodLevel();
        if (foodLevel == 20) {
            p.setFoodLevel(19);
            Bukkit.getScheduler().runTaskLater(HealthBlocker.getInstance(), () -> p.setFoodLevel(20), 1);
        }

        if (p.getHealth() >= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
            p.sendMessage(ChatColor.LIGHT_PURPLE + "You do not need to eat this food.");
            e.setCancelled(true);
            p.openInventory(p.getInventory()).close();
        }
    }

    private void sendCommand(String rank, String player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "euphoriaranks " + rank + " " + player);
    }
}
