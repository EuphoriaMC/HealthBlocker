package me.wsman217.healthblocker.alter;

import de.tr7zw.nbtapi.NBTItem;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.Collections;


public class Wand {

    private static final ItemStack BASE_WAND;

    static {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.FIREWORK_STAR));
        nbtItem.setBoolean("is_wand", true);
        BASE_WAND = nbtItem.getItem();

        if (BASE_WAND.hasItemMeta()) {
            ItemMeta im = BASE_WAND.getItemMeta();
            if (im != null) {
                im.setDisplayName(ChatColor.RESET + "" + ChatColor.of(new Color(255, 0, 255)) + "Wand");
                //im.setCustomModelData(14);
                im.setUnbreakable(true);
                im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
                im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                im.setLore(Collections.singletonList("Test"));
            }
            BASE_WAND.setItemMeta(im);
        }
    }

    public ItemStack getWand() {
        return getWand(0, 0, 0);
    }

    public ItemStack getWand(int r, int b, int g) {
        ItemStack wand = BASE_WAND.clone();
        FireworkEffectMeta fireworkMeta = (FireworkEffectMeta) wand.getItemMeta();
        if (fireworkMeta == null)
            return wand;
        FireworkEffect fireworkEffect = FireworkEffect.builder().with(FireworkEffect.Type.BURST).withColor(org.bukkit.Color.fromRGB(r, b, g)).build();
        fireworkMeta.setEffect(fireworkEffect);
        wand.setItemMeta(fireworkMeta);
        return wand;
    }

    public static boolean isWand(ItemStack wand) {
        NBTItem item = new NBTItem(wand);
        if (!item.hasKey("is_wand"))
            return false;
        return item.getBoolean("is_wand");
    }
}
