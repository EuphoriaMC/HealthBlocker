package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier2;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class TropicalStew extends CustomFoodItem {
    public TropicalStew() {
        super(ChatColor.YELLOW + "" + ChatColor.BOLD + "Tropical Stew", Material.MUSHROOM_STEW, 6,
                "tropical_stew", "healthblocker.food.tier2.tropical_stew",
                ChatColor.LIGHT_PURPLE + "Restores 3 hearts.", CustomFoodHandler.tier2);
        setCustomModelData(10);
        ItemStack item = createItem();
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> mushStew = new HashMap<>();
        mushStew.put(new ItemStack(Material.MUSHROOM_STEW), 1);
        inputs.add(mushStew);

        HashMap<ItemStack, Integer> tropFish = new HashMap<>();
        tropFish.put(new ItemStack(Material.TROPICAL_FISH), 1);
        inputs.add(tropFish);

        setRecipe(new Recipe().setRecipeTier(Recipe.Tier.TIER2).createShapelessRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), inputs, item));
    }
}
