package me.wsman217.healthblocker.gui.holders;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CraftingHolder implements InventoryHolder {

    private int tier;
    public boolean naturallyClosed = true;
    private final Inventory inv = null;

    @Override
    public Inventory getInventory() {
        return inv;
    }

    public CraftingHolder setTier(int tier) {
        this.tier = tier;
        return this;
    }

    public int getTier() {
        return tier;
    }
}
