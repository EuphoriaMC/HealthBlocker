package me.wsman217.healthblocker.listeners;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.CustomItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().discoverRecipes(CustomItemHandler.keys);

        Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> {
            e.getPlayer().setHealth(e.getPlayer().getHealth() - 1);
            e.getPlayer().setHealth(e.getPlayer().getHealth() + 1);
        }, 3L);
    }
}
