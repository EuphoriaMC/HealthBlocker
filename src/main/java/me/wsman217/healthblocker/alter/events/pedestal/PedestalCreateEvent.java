package me.wsman217.healthblocker.alter.events.pedestal;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PedestalCreateEvent extends PedestalEvent {
    @Getter
    @Setter
    private ItemStack itemStack;

    public PedestalCreateEvent(Location location, ItemStack itemStack, Player player) {
        super(location, player);
        this.itemStack = itemStack;
    }
}
