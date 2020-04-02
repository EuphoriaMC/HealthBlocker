package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier2;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.FoodUtils;
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

public class MapleSyrup extends CustomFoodItem {
    public MapleSyrup() {
        super(ChatColor.YELLOW + "" + ChatColor.BOLD + "Maple Syrup", Material.POTION, 3,
                "maple_syrup", "healthblocker.food.tier2.maple_syrup",
                ChatColor.LIGHT_PURPLE + "Restores 3 hearts.", CustomFoodHandler.tier2);
        setCustomModelData(9);
        ItemStack item = createItem();
        PotionMeta pm = (PotionMeta) item.getItemMeta();
        if (pm != null) {
            pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            pm.setColor(Color.fromBGR(5, 94, 158));
            item.setItemMeta(pm);
        }
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();
        HashMap<ItemStack, Integer> sugar = new HashMap<>();
        sugar.put(new ItemStack(Material.SUGAR, 1), 2);
        inputs.add(sugar);
        HashMap<ItemStack, Integer> glassBottle = new HashMap<>();
        glassBottle.put(new ItemStack(Material.GLASS_BOTTLE, 1), 1);
        inputs.add(glassBottle);

        setRecipe(new Recipe().setRecipeTier(Recipe.Tier.TIER2).createShapelessRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), inputs, item));
    }
}
