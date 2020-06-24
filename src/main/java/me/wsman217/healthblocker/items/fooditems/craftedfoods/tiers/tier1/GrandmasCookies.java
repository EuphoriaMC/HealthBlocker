package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier1;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GrandmasCookies extends CustomFoodItem {
    public GrandmasCookies() {
        super(ChatColor.GREEN + "" + ChatColor.BOLD + "Grandma's Cookies", Material.COOKIE, 4,
                "grandmas_cookies", "healthblocker.food.tier1.grandmas_cookies",
                ChatColor.LIGHT_PURPLE + "Restores 2 hearts.", CustomFoodHandler.tier1);
        setCustomModelData(3);
        ItemStack item = createItem();
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ItemStack cookie = new ItemStack(Material.COOKIE, 1);
        ItemStack redBlock = new ItemStack(Material.REDSTONE_BLOCK, 1);
        Recipe recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1);
        recipe.createShapedRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), item).shape("ABA")
                .setIngredient('A', redBlock).setIngredient('B', cookie).addRecipe();
        setRecipe(recipe);

        addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 1, false, true, true));
    }
}
