package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier1;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
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

public class AppleJuice extends CustomFoodItem {
    public AppleJuice() {
        super(ChatColor.GREEN + "" + ChatColor.BOLD + "Apple Juice", Material.POTION, 4,
                "apple_juice", "healthblocker.food.tier1.applejuice",
                ChatColor.LIGHT_PURPLE + "Restores 2 hearts.", CustomFoodHandler.tier1);

        ItemStack item = createItem();
        PotionMeta pm = (PotionMeta) item.getItemMeta();
        if (pm != null) {
            pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            pm.setColor(Color.fromBGR(50, 0, 255));
            item.setItemMeta(pm);
        }
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();
        HashMap<ItemStack, Integer> apple = new HashMap<>();
        apple.put(new ItemStack(Material.APPLE, 1), 1);
        inputs.add(apple);
        HashMap<ItemStack, Integer> glassBottle = new HashMap<>();
        glassBottle.put(new ItemStack(Material.GLASS_BOTTLE, 1), 1);
        inputs.add(glassBottle);
        Recipe recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1);
        recipe.createShapelessRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), inputs, getPermedItem());
        setRecipe(recipe);
    }
}
