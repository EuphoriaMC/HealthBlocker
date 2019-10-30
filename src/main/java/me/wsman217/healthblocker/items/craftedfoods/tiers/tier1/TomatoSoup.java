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

import java.util.ArrayList;
import java.util.HashMap;

public class TomatoSoup implements FoodInterface {

    private Material mat = Material.BEETROOT_SOUP;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "tomato_soup";
    private String permission = "healthblocker.food.tier1.tomatosoup";

    public TomatoSoup() {
        this.item = new ItemStack(mat);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("custom_food", true);
        nbtItem.setString("food_type", nameSpace);
        this.item = nbtItem.getItem();

        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "Tomato Soup");
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> beetSoup = new HashMap<>();
        beetSoup.put(new ItemStack(Material.BEETROOT_SOUP), 1);
        inputs.add(beetSoup);

        HashMap<ItemStack, Integer> sweetBerry = new HashMap<>();
        sweetBerry.put(new ItemStack(Material.SWEET_BERRIES), 2);
        inputs.add(sweetBerry);

        HealthBlocker plugin = HealthBlocker.getInstance();
        this.recipe = new Recipe().createShapelessRecipe(new NamespacedKey(plugin, nameSpace), inputs, item);

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
