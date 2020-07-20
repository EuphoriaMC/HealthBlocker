package me.wsman217.healthblocker.alter.events.pedestal;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PedestalRemoveEvent extends PedestalEvent {

    @Getter
    @Setter
    private ItemStack itemStack;

    public PedestalRemoveEvent(ArmorStand armorStand, Item item, ItemStack itemStack, Location location, Player player) {
        super(armorStand, item, location, player);
        this.itemStack = itemStack;
    }
}
