package me.wsman217.healthblocker.listeners;

import me.wsman217.healthblocker.commands.EuphoriaRanks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class EntityClickListener implements Listener {

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

    private void sendCommand(String rank, String player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "euphoriaranks " + rank + " " + player);
    }
}
