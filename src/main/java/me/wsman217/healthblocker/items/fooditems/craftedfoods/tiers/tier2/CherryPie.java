package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier2;

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

import java.util.ArrayList;
import java.util.HashMap;

public class CherryPie extends CustomFoodItem {
    public CherryPie() {
        super(ChatColor.YELLOW + "" + ChatColor.BOLD + "Cherry Pie", Material.PUMPKIN_PIE, 6,
                "cherry_pie", "healthblocker.food.tier2.cherry_pie",
                ChatColor.LIGHT_PURPLE + "Restores 3 hearts.", CustomFoodHandler.tier2);
        setCustomModelData(7);
        ItemStack item = createItem();
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> pumpPie = new HashMap<>();
        pumpPie.put(new ItemStack(Material.PUMPKIN_PIE), 1);
        inputs.add(pumpPie);

        HashMap<ItemStack, Integer> sweetBerry = new HashMap<>();
        sweetBerry.put(new ItemStack(Material.SWEET_BERRIES), 2);
        inputs.add(sweetBerry);

        setRecipe(new Recipe().setRecipeTier(Recipe.Tier.TIER2).createShapelessRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), inputs, item));
    }
}
