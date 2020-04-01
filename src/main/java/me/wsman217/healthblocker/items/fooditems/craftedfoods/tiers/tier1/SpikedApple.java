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

import java.util.ArrayList;
import java.util.HashMap;

public class SpikedApple extends CustomFoodItem {
    public SpikedApple() {
        super(ChatColor.GREEN + "" + ChatColor.BOLD + "Spiked Apple", Material.APPLE, 2,
                "", "healthblocker.food.tier1.spiked_apple",
                ChatColor.LIGHT_PURPLE + "Restores 1 hearts.", CustomFoodHandler.tier1);

        ItemStack item = createItem();
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> apple = new HashMap<>();
        apple.put(new ItemStack(Material.APPLE), 1);
        inputs.add(apple);

        HashMap<ItemStack, Integer> puffFish = new HashMap<>();
        puffFish.put(new ItemStack(Material.PUFFERFISH), 1);
        inputs.add(puffFish);

        Recipe recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1);
        recipe.createShapelessRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), inputs, getPermedItem());
        setRecipe(recipe);

        addPotionEffects(new PotionEffect(PotionEffectType.CONFUSION, 10, 1, false, true, true),
                new PotionEffect(PotionEffectType.BLINDNESS, 10, 1, false, true, true));
    }
}
