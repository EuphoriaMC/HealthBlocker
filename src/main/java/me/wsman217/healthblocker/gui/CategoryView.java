package me.wsman217.healthblocker.gui;

import me.wsman217.healthblocker.gui.holders.MainHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CategoryView implements Listener {

    private final String INV_NAME = ChatColor.LIGHT_PURPLE + "Please choose a tier.";
    private Player p;

    private ItemStack tier1Item = new ItemStack(Material.PAPER);
    private ItemStack tier2Item = new ItemStack(Material.BOOK);
    private ItemStack tier3Item = new ItemStack(Material.WRITABLE_BOOK);

    public CategoryView(Player p) {
        this.p = p;
    }

    public void openInv() {
        Inventory inv = Bukkit.createInventory(new MainHolder(), 9, INV_NAME);
        inv.setItem(2, tier1Item);
        inv.setItem(4, tier2Item);
        inv.setItem(6, tier3Item);
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
        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem.isSimilar(tier1Item))
            new Tier1(p).openInv();
        else if (clickedItem.isSimilar(tier2Item))
            new Tier2(p).openInv();
        else if (clickedItem.isSimilar(tier3Item))
            new Tier3(p).openInv();
    }
}
