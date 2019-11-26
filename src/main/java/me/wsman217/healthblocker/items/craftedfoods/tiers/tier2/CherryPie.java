package me.wsman217.healthblocker.items.craftedfoods.tiers.tier2;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.items.FoodInterface;
import me.wsman217.healthblocker.items.FoodUtils;
import me.wsman217.healthblocker.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;

public class CherryPie implements FoodInterface {

    private Material mat = Material.PUMPKIN_PIE;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "cherry_pie";
    private String permission = "healthblocker.food.tier2.cherrypie";

    public CherryPie() {
        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.YELLOW + "" + ChatColor.BOLD + "Cherry Pie"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2 + " hearts.");

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> pumpPie = new HashMap<>();
        pumpPie.put(new ItemStack(Material.PUMPKIN_PIE), 1);
        inputs.add(pumpPie);

        HashMap<ItemStack, Integer> sweetBerry = new HashMap<>();
        sweetBerry.put(new ItemStack(Material.SWEET_BERRIES), 2);
        inputs.add(sweetBerry);

        HealthBlocker plugin = HealthBlocker.getInstance();
        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER2).createShapelessRecipe(new NamespacedKey(plugin, nameSpace), inputs, item);

        Permission permission = new Permission(this.permission);
        permission.addParent(CustomItemHandler.tier2, true);
        plugin.getServer().getPluginManager().addPermission(permission);
    }

    @Override
    public Material getMat() {
        return mat;
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
        return FoodUtils.getItemStack(mat, nameSpace, ChatColor.YELLOW + "" + ChatColor.BOLD + "Cherry Pie"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2 + " hearts.");
    }

    @Override
    public boolean isSimilar(ItemStack toTest) {
        return item.isSimilar(toTest);
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
    public String getPermission() {
        return null;
    }
}
