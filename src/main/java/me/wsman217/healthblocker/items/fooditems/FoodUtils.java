package me.wsman217.healthblocker.items.fooditems;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.obsolete.CustomFoodHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FoodUtils {

    @Deprecated
    public static ItemStack getItemStack(Material mat, String nameSpace, String name, String loreS) {
        ItemStack item = new ItemStack(mat);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("custom_food", true);
        nbtItem.setString("food_type", nameSpace);
        nbtItem.setBoolean("need_perm", true);
        item = nbtItem.getItem();

        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + name);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + loreS);
        im.setLore(lore);
        item.setItemMeta(im);

        return item;
    }
}
