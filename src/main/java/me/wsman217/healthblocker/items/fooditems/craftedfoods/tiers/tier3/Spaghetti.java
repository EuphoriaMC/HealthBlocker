package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier3;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.CustomFoodHandler;
import me.wsman217.healthblocker.items.fooditems.FoodInterface;
import me.wsman217.healthblocker.items.fooditems.FoodUtils;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;

public class Spaghetti implements FoodInterface {

    private Material mat = Material.BEETROOT_SOUP;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "spaghetti";
    private String permission = "healthblocker.food.tier3.spaghetti";
    private Permission perm;

    public Spaghetti() {
        HealthBlocker plugin = HealthBlocker.getInstance();

        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Spaghetti"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();
        HashMap<ItemStack, Integer> soup = new HashMap<>();
        soup.put(new ItemStack(Material.BEETROOT_SOUP, 1), 1);
        inputs.add(soup);
        HashMap<ItemStack, Integer> tomatoes = new HashMap<>();
        tomatoes.put(new ItemStack(Material.BEETROOT, 1), 2);
        inputs.add(tomatoes);
        HashMap<ItemStack, Integer> noodles = new HashMap<>();
        tomatoes.put(new ItemStack(Material.SUGAR_CANE, 1), 3);
        inputs.add(noodles);

        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER3);
        recipe.createShapelessRecipe(new NamespacedKey(plugin, nameSpace), inputs, item);

        perm = new Permission(this.permission);
        perm.addParent(CustomFoodHandler.tier3, true);
        plugin.getServer().getPluginManager().addPermission(perm);
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
        return FoodUtils.getItemStack(mat, nameSpace, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Spaghetti"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");
    }

    @Override
    public int getHealthRegenned() {
        return 15;
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
