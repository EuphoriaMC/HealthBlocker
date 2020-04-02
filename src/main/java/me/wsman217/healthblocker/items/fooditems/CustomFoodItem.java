package me.wsman217.healthblocker.items.fooditems;

import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CustomFoodItem {

    @Getter
    private String name;
    @Getter
    private Material mat;
    @Getter
    private int healthRegenned;
    @Getter
    private String namespace;
    @Getter
    private String permission;
    @Getter
    private String lore;
    @Getter
    private Permission permTier;

    @Getter
    @Setter
    private Recipe recipe;
    @Setter
    private ItemStack permedItem;
    @Setter
    private ItemStack nonPermedItem;

    @Getter
    private Permission perm;

    @Setter
    private int customModelData = -1;
    @Getter
    private ArrayList<PotionEffect> effectsWhenEaten = new ArrayList<>();
    @Getter
    @Setter
    private boolean removePotionEffectsGivenByThisFood = false;

    public CustomFoodItem(String name, Material mat, int healthRegenned, String namespace, String permission, String lore, Permission permTier) {
        this.name = name;
        this.mat = mat;
        this.healthRegenned = healthRegenned;
        this.namespace = namespace;
        this.permission = permission;
        this.lore = lore;
        this.permTier = permTier;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(mat);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("custom_food", true);
        nbtItem.setString("food_type", namespace);
        nbtItem.setBoolean("need_perm", true);
        item = nbtItem.getItem();

        ItemMeta im = item.getItemMeta();
        if (im != null) {
            im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + name);
            im.addEnchant(Enchantment.LUCK, 1, false);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.LIGHT_PURPLE + "" + this.lore);
            im.setLore(lore);
            if (customModelData != -1)
                im.setCustomModelData(customModelData);
            item.setItemMeta(im);
        }

        perm = new org.bukkit.permissions.Permission(this.permission);
        perm.addParent(permTier, true);
        System.out.println(perm);
        System.out.println(HealthBlocker.getInstance());
        HealthBlocker.getInstance().getServer().getPluginManager().addPermission(perm);

        return item;
    }

    public ItemStack setNeedsPerm(boolean needsPerm) {
        ItemStack item = this.permedItem.clone();
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("need_perm", needsPerm);
        ItemStack updatedItem = nbtItem.getItem();
        if (!needsPerm) {
            ItemMeta im = updatedItem.getItemMeta();
            if (im == null)
                return updatedItem;
            List<String> lore = im.getLore() != null ? im.getLore() : new ArrayList<>();
            lore.add(ChatColor.GRAY + "This food can be eaten by anyone.");
            im.setLore(lore);
            updatedItem.setItemMeta(im);
        } else {
            ItemMeta im = updatedItem.getItemMeta();
            if (im == null)
                return updatedItem;
            List<String> lore = im.getLore();
            if (lore != null)
                lore.removeIf(loreItem -> loreItem.equalsIgnoreCase(ChatColor.GRAY + "This food can be eaten by anyone."));
        }
        return updatedItem;
    }

    public ItemStack getPermedItem() {
        return permedItem.clone();
    }

    public ItemStack getNonPermedItem() {
        return nonPermedItem.clone();
    }

    public void addPotionEffect(PotionEffect effect) {
        effectsWhenEaten.add(effect);
    }

    public void addPotionEffects(PotionEffect... effects) {
        Collections.addAll(effectsWhenEaten, effects);
    }
}