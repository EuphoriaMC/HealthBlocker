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

import java.util.ArrayList;
import java.util.HashMap;

public class GlassOfMilk extends CustomFoodItem {
    public GlassOfMilk() {
        super(ChatColor.GREEN + "" + ChatColor.BOLD + "Glass of Milk", Material.POTION, 5,
                "glass_of_milk", "healthblocker.food.tier1.glass_of_milk",
                ChatColor.LIGHT_PURPLE + "Restores 2.5 hearts.", CustomFoodHandler.tier1);
        setCustomModelData(2);
        ItemStack item = createItem();
        PotionMeta pm = (PotionMeta) item.getItemMeta();
        if (pm != null) {
            pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            pm.setColor(Color.fromBGR(255, 255, 255));
            item.setItemMeta(pm);
        }
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();
        HashMap<ItemStack, Integer> milk = new HashMap<>();
        milk.put(new ItemStack(Material.MILK_BUCKET, 1), 1);
        inputs.add(milk);
        HashMap<ItemStack, Integer> glassBottle = new HashMap<>();
        glassBottle.put(new ItemStack(Material.GLASS_BOTTLE, 1), 1);
        inputs.add(glassBottle);
        Recipe recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1);
        recipe.createShapelessRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), inputs, getPermedItem());
        setRecipe(recipe);
    }
}
