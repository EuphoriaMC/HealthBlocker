package me.wsman217.healthblocker.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.items.FoodInterface;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class EatingListener implements Listener {

    @EventHandler
    public void onConsumeEvent(PlayerItemConsumeEvent e) {
        NBTItem nbtItem = new NBTItem(e.getItem());
        boolean isCustomFood = nbtItem.getBoolean("custom_food");
        if (!isCustomFood)
            return;
        String customFoodType = nbtItem.getString("food_type");
        FoodInterface foodType = CustomItemHandler.getFromNameSpace(customFoodType);
        Player p = e.getPlayer();

        if (!p.hasPermission(foodType.getPermission())) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You do not have permission to eat this item.");
            return;
        }
        /*p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);*/
        double healthToRegen = Math.min(p.getHealth() + foodType.getHealthRegenned(), p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        p.setHealth(healthToRegen);
    }
}
