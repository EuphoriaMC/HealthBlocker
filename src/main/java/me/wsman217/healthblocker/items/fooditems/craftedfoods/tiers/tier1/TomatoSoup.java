package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier1;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.gui.Tier1;
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

public class TomatoSoup extends CustomFoodItem {
    public TomatoSoup() {
        super(ChatColor.GREEN + "Tomato Soup" + ChatColor.BOLD + "", Material.BEETROOT_SOUP, 4,
                "", "healthblocker.food.tier1.beetroot_soup",
                ChatColor.LIGHT_PURPLE + "Restores 2 hearts.", CustomFoodHandler.tier1);

        ItemStack item = createItem();
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> beetSoup = new HashMap<>();
        beetSoup.put(new ItemStack(Material.BEETROOT_SOUP), 1);
        inputs.add(beetSoup);

        HashMap<ItemStack, Integer> sweetBerry = new HashMap<>();
        sweetBerry.put(new ItemStack(Material.SWEET_BERRIES), 2);
        inputs.add(sweetBerry);

        setRecipe(new Recipe().setRecipeTier(Recipe.Tier.TIER1).createShapelessRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), inputs, item));
    }
}
