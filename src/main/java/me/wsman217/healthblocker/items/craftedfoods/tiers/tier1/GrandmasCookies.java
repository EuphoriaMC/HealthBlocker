package me.wsman217.healthblocker.items.craftedfoods.tiers.tier1;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.items.FoodInterface;
import me.wsman217.healthblocker.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;

public class GrandmasCookies implements FoodInterface {

    private Material mat = Material.COOKIE;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "grandmas_cookie";
    private String permission = "healthblocker.food.tier1.grandmascookies";

    public GrandmasCookies() {
        HealthBlocker plugin = HealthBlocker.getInstance();

        this.item = new ItemStack(mat);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("custom_food", true);
        nbtItem.setString("food_type", nameSpace);
        this.item = nbtItem.getItem();

        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "Grandma's Cookies");
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);

        ItemStack cookie = new ItemStack(Material.COOKIE, 1);
        ItemStack redBlock = new ItemStack(Material.REDSTONE_BLOCK, 1);

        recipe = new Recipe();
        recipe.createShapedRecipe(new NamespacedKey(plugin, nameSpace), item).shape("ABA")
                .setIngredient('A', redBlock).setIngredient('B', cookie).addRecipe();
        Permission permission = new Permission(this.permission);
        permission.addParent(CustomItemHandler.tier1, true);
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
        return item;
    }

    @Override
    public boolean isSimilar(ItemStack toTest) {
        return item.isSimilar(toTest);
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
    public String getPermission() {
        return permission;
    }
}
