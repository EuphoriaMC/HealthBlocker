package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.obsolete.tier2;

import me.wsman217.healthblocker.items.fooditems.FoodInterface;
import me.wsman217.healthblocker.items.fooditems.FoodUtils;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;

public class TropicalStew implements FoodInterface {

    private Material mat = Material.MUSHROOM_STEW;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "tropical_stew";
    private String permission = "healthblocker.food.tier2.tropicalstew";
    private Permission perm;

    public TropicalStew() {
        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.YELLOW + "" + ChatColor.BOLD + "Tropical Stew"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> mushStew = new HashMap<>();
        mushStew.put(new ItemStack(Material.MUSHROOM_STEW), 1);
        inputs.add(mushStew);

        HashMap<ItemStack, Integer> tropFish = new HashMap<>();
        tropFish.put(new ItemStack(Material.TROPICAL_FISH), 1);
        inputs.add(tropFish);
/*
        HealthBlocker plugin = HealthBlocker.getInstance();
        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER2).createShapelessRecipe(new NamespacedKey(plugin, nameSpace), inputs, item);

        perm = new Permission(this.permission);
        perm.addParent(CustomFoodHandler.tier2, true);
        plugin.getServer().getPluginManager().addPermission(perm);*/
    }

    @Override
    public String getName() {
        return item.getItemMeta().getDisplayName();
    }

    @Override
    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public ItemStack getItemStack() {
        return FoodUtils.getItemStack(mat, nameSpace, ChatColor.YELLOW + "" + ChatColor.BOLD + "Tropical Stew"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");
    }

    @Override
    public int getHealthRegenned() {
        return 6;
    }

    @Override
    public String getNamespace() {
        return nameSpace;
    }

    @Override
    public Permission getPermission() {
        return perm;
    }
}
