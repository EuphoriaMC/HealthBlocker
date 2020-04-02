package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier3;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

public class DarkMagic extends CustomFoodItem {
    public DarkMagic() {
        super(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Dark Magic", Material.DRIED_KELP, 20,
                "dark_magic", "healthblocker.food.tier3.dark_magic",
                ChatColor.LIGHT_PURPLE + "Restores 10 hearts.", CustomFoodHandler.tier3);
        setCustomModelData(11);
        ItemStack item = createItem();
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ItemStack driedKelpBlock = new ItemStack(Material.DRIED_KELP_BLOCK, 1);
        ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK, 1);

        Recipe recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER3);
        recipe.createShapedRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), item)
                .shape("AAA", "ABA", "AAA").setIngredient('A', goldBlock).setIngredient('B', driedKelpBlock);
        setRecipe(recipe);
    }
}
