package me.wsman217.healthblocker.listeners;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.CustomFoodHandler;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        //Discover all the recipes when a player joins
        e.getPlayer().discoverRecipes(CustomFoodHandler.keys);

        /*
        This was for when I was playing with health being higher than 10 hearts it fixes a visual glitch where you
        don't see the extra hearts you have until you take damage.
         */
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(100);
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);
        Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> {
            double health = e.getPlayer().getHealth();
            e.getPlayer().setHealth(e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            e.getPlayer().setHealth(health);
        }, 3L);

    }
}
