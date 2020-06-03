package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.obsolete.tier1;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.FoodInterface;
import me.wsman217.healthblocker.items.fooditems.FoodUtils;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

public class GrandmasCookies implements FoodInterface {

    private Material mat = Material.COOKIE;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "grandmas_cookie";
    private String permission = "healthblocker.food.tier1.grandmascookies";
    private Permission perm;

    public GrandmasCookies() {
        HealthBlocker plugin = HealthBlocker.getInstance();

        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.GREEN + "" + ChatColor.BOLD + "Grandma's Cookies"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");

        ItemStack cookie = new ItemStack(Material.COOKIE, 1);
        ItemStack redBlock = new ItemStack(Material.REDSTONE_BLOCK, 1);
/*
        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1);
        recipe.createShapedRecipe(new NamespacedKey(plugin, nameSpace), item).shape("ABA")
                .setIngredient('A', redBlock).setIngredient('B', cookie).addRecipe();
        perm = new Permission(this.permission);
        perm.addParent(CustomFoodHandler.tier1, true);
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
        return FoodUtils.getItemStack(mat, nameSpace, ChatColor.GREEN + "" + ChatColor.BOLD + "Grandma's Cookies"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");
    }

    @Override
    public int getHealthRegenned() {
        return 4;
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
