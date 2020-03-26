package me.wsman217.healthblocker.items.fooditems;

import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

public interface FoodInterface {
    String getName();
    Recipe getRecipe();
    ItemStack getItemStack();
    int getHealthRegenned();
    String getNamespace();
    Permission getPermission();
}
