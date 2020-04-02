package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier1;

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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RestoredEye extends CustomFoodItem {
    public RestoredEye() {
        super(ChatColor.GREEN + "" + ChatColor.BOLD + "Restored Eye", Material.SPIDER_EYE, 1,
                "restored_eye", "healthblocker.food.tier1.restored_eye",
                ChatColor.LIGHT_PURPLE + "Restores half a heart.", CustomFoodHandler.tier1);
        setCustomModelData(4);
        ItemStack item = createItem();
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));
        setRemovePotionEffectsGivenByThisFood(true);
        addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 5, 2, false, true, true));

        ItemStack string = new ItemStack(Material.STRING, 1);
        ItemStack spiderEye = new ItemStack(Material.SPIDER_EYE, 1);
        Recipe recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1);
        recipe.createShapedRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), item).shape("AAA", "ABA", "AAA")
                .setIngredient('A', string).setIngredient('B', spiderEye).addRecipe();
        setRecipe(recipe);
    }
}
