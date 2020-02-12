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

public class RestoredEye implements FoodInterface {

    private Material mat = Material.SPIDER_EYE;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "restored_eye";
    private String permission = "healthblocker.food.tier1.restoredeye";
    private Permission perm;

    public RestoredEye() {
        HealthBlocker plugin = HealthBlocker.getInstance();

        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.GREEN + "" + ChatColor.BOLD + "Restored Eye"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");

        ItemStack string = new ItemStack(Material.STRING, 1);
        ItemStack spiderEye = new ItemStack(Material.SPIDER_EYE, 1);

        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1);
        recipe.createShapedRecipe(new NamespacedKey(plugin, nameSpace), item).shape("AAA", "ABA", " AAA")
                .setIngredient('A', string).setIngredient('B', spiderEye).addRecipe();
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
        return FoodUtils.getItemStack(mat, nameSpace, ChatColor.GREEN + "" + ChatColor.BOLD + "Restored Eye"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");
    }

    @Override
    public boolean isSimilar(ItemStack toTest) {
        return item.isSimilar(toTest);
    }

    @Override
    public int getHealthRegenned() {
        return 1;
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
