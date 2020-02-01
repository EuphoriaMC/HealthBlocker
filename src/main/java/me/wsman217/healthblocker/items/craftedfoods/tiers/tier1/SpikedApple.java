package me.wsman217.healthblocker.items.craftedfoods.tiers.tier1;

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

public class SpikedApple implements FoodInterface {

    private Material mat = Material.APPLE;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "spiked_apple";
    private String permission = "healthblocker.food.tier1.spikedapple";
    private Permission perm;

    public SpikedApple() {
        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.GREEN + "" + ChatColor.BOLD + "Spiked Apple"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> apple = new HashMap<>();
        apple.put(new ItemStack(Material.APPLE), 1);
        inputs.add(apple);

        HashMap<ItemStack, Integer> puffFish = new HashMap<>();
        puffFish.put(new ItemStack(Material.PUFFERFISH), 1);
        inputs.add(puffFish);

        HealthBlocker plugin = HealthBlocker.getInstance();
        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1).createShapelessRecipe(new NamespacedKey(plugin, nameSpace), inputs, item);

        perm = new Permission(this.permission);
        perm.addParent(CustomItemHandler.tier1, true);
        plugin.getServer().getPluginManager().addPermission(perm);
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
        return FoodUtils.getItemStack(mat, nameSpace, ChatColor.GREEN + "" + ChatColor.BOLD + "Spiked Apple"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");
    }

    @Override
    public boolean isSimilar(ItemStack toTest) {
        return item.isSimilar(toTest);
    }

    @Override
    public int getHealthRegenned() {
        return 2;
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
