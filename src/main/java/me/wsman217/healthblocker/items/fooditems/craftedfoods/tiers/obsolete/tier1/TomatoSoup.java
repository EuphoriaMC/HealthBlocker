package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.obsolete.tier1;

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

import java.util.ArrayList;
import java.util.HashMap;

public class TomatoSoup implements FoodInterface {

    private Material mat = Material.BEETROOT_SOUP;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "tomato_soup";
    private String permission = "healthblocker.food.tier1.tomatosoup";
    private Permission perm;

    public TomatoSoup() {
        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.GREEN + "" + ChatColor.BOLD + "Tomato Soup"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> beetSoup = new HashMap<>();
        beetSoup.put(new ItemStack(Material.BEETROOT_SOUP), 1);
        inputs.add(beetSoup);

        HashMap<ItemStack, Integer> sweetBerry = new HashMap<>();
        sweetBerry.put(new ItemStack(Material.SWEET_BERRIES), 2);
        inputs.add(sweetBerry);

 /*       HealthBlocker plugin = HealthBlocker.getInstance();
        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1).createShapelessRecipe(new NamespacedKey(plugin, nameSpace), inputs, item);

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
        return FoodUtils.getItemStack(mat, nameSpace, ChatColor.GREEN + "" + ChatColor.BOLD + "Tomato Soup"
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
