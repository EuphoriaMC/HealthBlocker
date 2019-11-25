package me.wsman217.healthblocker.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.items.FoodInterface;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftingListener implements Listener {

    @EventHandler
    public void craftingListener(CraftItemEvent e) {
        //Check if its a custom food
        NBTItem nbtItem = new NBTItem(e.getRecipe().getResult());
        boolean isCustomFood = nbtItem.getBoolean("custom_food");
        if (!isCustomFood)
            return;
        String customFoodType = nbtItem.getString("food_type");
        FoodInterface foodType = CustomItemHandler.getFromNameSpace(customFoodType);
        //Check if they have permission to craft the food
        if (!e.getWhoClicked().hasPermission(foodType.getPermission())) {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage(ChatColor.RED + "You do not have permission to craft this item.");
        }
    }
}
