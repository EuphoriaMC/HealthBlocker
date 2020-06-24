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

public class LumpyBoneStew extends CustomFoodItem {
    public LumpyBoneStew() {
        super(ChatColor.YELLOW + "" + ChatColor.BOLD + "Lumpy Bone Stew", Material.RABBIT_STEW, 6,
                "lumpy_bone_stew", "healthblocker.food.tier2.lumpy_bone_stew",
                ChatColor.LIGHT_PURPLE + "Restores 3 hearts.", CustomFoodHandler.tier2);
        setCustomModelData(8);
        ItemStack item = createItem();
        setPermedItem(item);
        setNonPermedItem(setNeedsPerm(false));

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> rabStew = new HashMap<>();
        rabStew.put(new ItemStack(Material.RABBIT_STEW), 1);
        inputs.add(rabStew);

        HashMap<ItemStack, Integer> glistMelon = new HashMap<>();
        glistMelon.put(new ItemStack(Material.GLISTERING_MELON_SLICE), 1);
        inputs.add(glistMelon);

        HashMap<ItemStack, Integer> boneMeal = new HashMap<>();
        boneMeal.put(new ItemStack(Material.BONE_MEAL), 1);
        inputs.add(boneMeal);

        setRecipe(new Recipe().setRecipeTier(Recipe.Tier.TIER2).createShapelessRecipe(new NamespacedKey(HealthBlocker.getInstance(), getNamespace()), inputs, item));
    }
}
