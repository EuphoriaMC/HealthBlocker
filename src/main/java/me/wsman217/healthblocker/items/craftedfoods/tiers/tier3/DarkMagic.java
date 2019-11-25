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

import java.util.ArrayList;
import java.util.List;

public class DarkMagic implements FoodInterface {

    private Material mat = Material.DRIED_KELP;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "dark_magic";
    private String permission = "healthblocker.food.tier3.darkmagic";

    public DarkMagic() {
        HealthBlocker plugin = HealthBlocker.getInstance();

        this.item = new ItemStack(mat);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("custom_food", true);
        nbtItem.setString("food_type", nameSpace);
        this.item = nbtItem.getItem();

        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Dark Magic");
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "Restores full health.");
        im.setLore(lore);
        item.setItemMeta(im);

        ItemStack driedKelpBlock = new ItemStack(Material.DRIED_KELP_BLOCK, 1);
        ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK, 1);

        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER3);
        recipe.createShapedRecipe(new NamespacedKey(plugin, nameSpace), item).shape("AAA", "ABA", "AAA")
                .setIngredient('A', goldBlock).setIngredient('B', driedKelpBlock).addRecipe();

        Permission permission = new Permission(this.permission);
        permission.addParent(CustomItemHandler.tier3, true);
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
        return 20;
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
