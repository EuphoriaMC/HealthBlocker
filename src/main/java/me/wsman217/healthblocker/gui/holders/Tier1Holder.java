package me.wsman217.healthblocker.gui.holders;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class Tier1Holder implements InventoryHolder {
    public boolean naturallyClosed = true;
    private Inventory inv = null;
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
