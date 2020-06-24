package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier3;

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

public class Spaghetti extends CustomFoodItem {
    public Spaghetti() {
        super(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Spaghetti", Material.BEETROOT_SOUP, 15,
                "spaghetti", "healthblocker.food.tier3.spaghetti",
                ChatColor.LIGHT_PURPLE + "Restores 7.5 hearts.", CustomFoodHandler.tier3);
        setCustomModelData(12);
        ItemStack item = createItem();
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();
        HashMap<ItemStack, Integer> soup = new HashMap<>();
        soup.put(new ItemStack(Material.BEETROOT_SOUP, 1), 1);
        inputs.add(soup);
        HashMap<ItemStack, Integer> tomatoes = new HashMap<>();
        tomatoes.put(new ItemStack(Material.BEETROOT, 1), 2);
        inputs.add(tomatoes);
        HashMap<ItemStack, Integer> noodles = new HashMap<>();
        tomatoes.put(new ItemStack(Material.SUGAR_CANE, 1), 3);
        inputs.add(noodles);

        setRecipe(new Recipe().setRecipeTier(Recipe.Tier.TIER3).createShapelessRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), inputs, item));
    }
}
