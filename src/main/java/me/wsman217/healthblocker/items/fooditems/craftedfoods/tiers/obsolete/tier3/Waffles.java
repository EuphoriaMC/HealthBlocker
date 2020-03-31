package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.obsolete.tier3;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.obsolete.CustomFoodHandler;
import me.wsman217.healthblocker.items.fooditems.FoodInterface;
import me.wsman217.healthblocker.items.fooditems.FoodUtils;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

public class Waffles implements FoodInterface {

    private Material mat = Material.COOKIE;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "waffles";
    private String permission = "healthblocker.food.tier3.waffles";
    private Permission perm;

    public Waffles() {
        HealthBlocker plugin = HealthBlocker.getInstance();

        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Waffles"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");

        ItemStack cookie = new ItemStack(Material.COOKIE, 1);
        ItemStack wheat = new ItemStack(Material.WHEAT, 1);
        ItemStack sugar = new ItemStack(Material.SUGAR, 1);
        ItemStack milk = CustomFoodHandler.glassOfMilk.getItemStack();
        ItemStack grill = new ItemStack(Material.MAGMA_BLOCK, 1);
/*
        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER3);
        recipe.createShapedRecipe(new NamespacedKey(plugin, nameSpace), item).shape("ABA", "CDC", "EEE")
                .setIngredient('A', wheat).setIngredient('B', sugar).setIngredient('C', milk)
                .setIngredient('D', cookie).setIngredient('E', grill).addRecipe();

        perm = new Permission(this.permission);
        perm.addParent(CustomFoodHandler.tier3, true);
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
        return FoodUtils.getItemStack(mat, nameSpace, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Waffles"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");
    }

    @Override
    public int getHealthRegenned() {
        return 16;
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
