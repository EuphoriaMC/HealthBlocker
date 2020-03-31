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

public class MapleWaffles implements FoodInterface {

    private Material mat = Material.COOKIE;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "maple_waffles";
    private String permission = "healthblocker.food.tier3.maple_waffles";
    private Permission perm;

    public MapleWaffles() {
        HealthBlocker plugin = HealthBlocker.getInstance();

        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Maple Waffles"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");

        ItemStack waffle = CustomFoodHandler.waffles.getItemStack();
        ItemStack mapleSyrup = CustomFoodHandler.mapleSyrup.getItemStack();

/*        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER3);
        recipe.createShapedRecipe(new NamespacedKey(plugin, nameSpace), item).shape("AAA", " B ")
                .setIngredient('A', mapleSyrup).setIngredient('B', waffle).addRecipe();

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
        return FoodUtils.getItemStack(mat, nameSpace, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Maple Waffles"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");
    }

    @Override
    public int getHealthRegenned() {
        return 20;
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
