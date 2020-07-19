package me.wsman217.healthblocker.gui;

import me.wsman217.healthblocker.gui.holders.MainHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CategoryView implements Listener {

    private final String INV_NAME = ChatColor.LIGHT_PURPLE + "Please choose a tier.";
    private Player p;

    private static final ItemStack tier1Item = new ItemStack(Material.PAPER);
    private static final ItemStack tier2Item = new ItemStack(Material.BOOK);
    private static final ItemStack tier3Item = new ItemStack(Material.WRITABLE_BOOK);

    static {
        ItemMeta tier1IM = tier1Item.getItemMeta();
        tier1IM.addEnchant(Enchantment.LUCK, 1, true);
        tier1IM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        tier1IM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tier1IM.setDisplayName(ChatColor.GREEN + "Tier 1");
        tier1Item.setItemMeta(tier1IM);

        ItemMeta tier2IM = tier2Item.getItemMeta();
        tier2IM.addEnchant(Enchantment.LUCK, 1, true);
        tier2IM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        tier2IM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tier2IM.setDisplayName(ChatColor.YELLOW + "Tier 2");
        tier2Item.setItemMeta(tier2IM);

        ItemMeta tier3IM = tier3Item.getItemMeta();
        tier3IM.addEnchant(Enchantment.LUCK, 1, true);
        tier3IM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        tier3IM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tier3IM.setDisplayName(ChatColor.LIGHT_PURPLE + "Tier 3");
        tier3Item.setItemMeta(tier3IM);
    }

    public CategoryView() {

    }

    public CategoryView(Player p) {
        this.p = p;
    }

    public void openInv() {
        //Create the inventory and add the items to it
        //Inventory inv = Bukkit.createInventory(new MainHolder(), 9, INV_NAME);
        Inventory inv = Bukkit.createInventory(new MainHolder(), 9, INV_NAME);
        /*inv.setItem(2, tier1Item);
        inv.setItem(4, tier2Item);
        inv.setItem(6, tier3Item);*/
        inv.setItem(0, tier1Item);
        inv.setItem(4, tier2Item);
        inv.setItem(8, tier3Item);
        p.openInventory(inv);
    }

    @EventHandler
    public void onTierClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof MainHolder))
            return;
        if (!(e.getWhoClicked() instanceof Player))
            return;
        if (e.getCurrentItem() == null)
            return;
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        //Open tier 1 inv
        if (clickedItem.isSimilar(tier1Item)) {
            p.closeInventory();
            new Tier1(p).openInv();
        }
        //Open tier 2 inv
        else if (clickedItem.isSimilar(tier2Item)) {
            p.closeInventory();
            new Tier2(p).openInv();
        }
        //Open tier 3 inv
        else if (clickedItem.isSimilar(tier3Item)) {
            p.closeInventory();
            new Tier3(p).openInv();
        }
    }
}
