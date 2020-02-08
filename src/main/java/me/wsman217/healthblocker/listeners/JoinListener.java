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
        //Discover all the recipes when a player joins
        e.getPlayer().discoverRecipes(CustomItemHandler.keys);

        /*
        This was for when I was playing with health being higher than 10 hearts it fixes a visual glitch where you
        don't see the extra hearts you have until you take damage.
         */
        Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> {
            e.getPlayer().setHealth(e.getPlayer().getHealth() + 1);
            e.getPlayer().setHealth(e.getPlayer().getHealth() - 1);
        }, 3L);
    }
}
