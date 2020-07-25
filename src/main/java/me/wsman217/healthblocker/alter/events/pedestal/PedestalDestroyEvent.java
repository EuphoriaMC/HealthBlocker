package me.wsman217.healthblocker.alter.events.pedestal;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class PedestalDestroyEvent extends PedestalEvent {

    public PedestalDestroyEvent(ArmorStand armorStand, Item item, Location location, Player player) {
        super(armorStand, item, location, player);
    }
}
