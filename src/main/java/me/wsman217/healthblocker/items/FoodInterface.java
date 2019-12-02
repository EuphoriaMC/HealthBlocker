package me.wsman217.healthblocker.items;

import me.wsman217.healthblocker.recipeutils.Recipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

public interface FoodInterface {
    Material getMat();

    String getName();

    Recipe getRecipe();

    ItemStack getItemStack();

    boolean isSimilar(ItemStack toTest);

    int getHealthRegenned();

    String getNamespace();

    Permission getPermission();
}
