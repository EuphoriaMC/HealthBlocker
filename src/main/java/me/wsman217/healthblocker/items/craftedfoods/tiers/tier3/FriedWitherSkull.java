package me.wsman217.healthblocker.items.craftedfoods.tiers.tier3;

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

public class FriedWitherSkull implements FoodInterface {

    private Material mat = Material.WITHER_SKELETON_SKULL;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "fried_wither_skull";
    private String permission = "healthblocker.food.tier3.friedwitherskull";

    public FriedWitherSkull() {
        this.item = new ItemStack(mat);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("custom_food", true);
        nbtItem.setString("food_type", nameSpace);
        this.item = nbtItem.getItem();

        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.LIGHT_PURPLE + "Fried Wither Skull");
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);

        this.recipe = new Recipe().createFurnaceRecipe(new NamespacedKey(HealthBlocker.getInstance()
                        , nameSpace), new ItemStack(Material.WITHER_SKELETON_SKULL)
                , item, 1f, 20);

        Permission permission = new Permission(this.permission);
        permission.addParent(CustomItemHandler.tier3, true);
        HealthBlocker.getInstance().getServer().getPluginManager().addPermission(permission);
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
        return 16;
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
