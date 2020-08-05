package me.wsman217.healthblocker.listeners;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.advancements.AchievementManager;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        AchievementManager.addPlayer(e.getPlayer());

        Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> {
            //Discover all the recipes when a player joins
            e.getPlayer().discoverRecipes(CustomFoodHandler.keys);
            double health = e.getPlayer().getHealth();
            e.getPlayer().setHealth(e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            e.getPlayer().setHealth(health);
        }, 3L);

    }
}
