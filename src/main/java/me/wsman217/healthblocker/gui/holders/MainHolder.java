package me.wsman217.healthblocker.gui.holders;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MainHolder implements InventoryHolder {

    private final Inventory inv = null;
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
