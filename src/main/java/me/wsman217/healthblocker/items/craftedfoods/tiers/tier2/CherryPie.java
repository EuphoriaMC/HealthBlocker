package me.wsman217.healthblocker.items.craftedfoods.tiers.tier2;

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
import java.util.List;

public class CherryPie implements FoodInterface {

    private Material mat = Material.PUMPKIN_PIE;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "cherry_pie";
    private String permission = "healthblocker.food.tier2.cherrypie";

    public CherryPie() {
        this.item = new ItemStack(mat);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("custom_food", true);
        nbtItem.setString("food_type", nameSpace);
        this.item = nbtItem.getItem();

        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Cherry Pie");
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "Restores 3 hearts.");
        im.setLore(lore);
        item.setItemMeta(im);

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
        return item;
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
