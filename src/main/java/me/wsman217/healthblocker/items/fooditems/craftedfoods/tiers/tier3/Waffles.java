package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier3;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class Waffles extends CustomFoodItem {
    public Waffles() {
        super(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Waffles", Material.COOKIE, 16,
                "waffles", "healthblocker.food.tier3.waffles",
                ChatColor.LIGHT_PURPLE + "Restores 8 hearts.", CustomFoodHandler.tier3);
        setCustomModelData(13);
        ItemStack item = createItem();
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ItemStack cookie = new ItemStack(Material.COOKIE, 1);
        ItemStack wheat = new ItemStack(Material.WHEAT, 1);
        ItemStack sugar = new ItemStack(Material.SUGAR, 1);
        ItemStack milk = CustomFoodHandler.glassOfMilk.getPermedItem();
        ItemStack grill = new ItemStack(Material.MAGMA_BLOCK, 1);

        Recipe recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER3);
        recipe.createShapedRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), item).shape("ABA", "CDC", "EEE")
                .setIngredient('A', wheat).setIngredient('B', sugar).setIngredient('C', milk)
                .setIngredient('D', cookie).setIngredient('E', grill).addRecipe();
        setRecipe(recipe);
    }
}
