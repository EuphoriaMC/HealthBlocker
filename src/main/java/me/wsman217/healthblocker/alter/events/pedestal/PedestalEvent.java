package me.wsman217.healthblocker.alter.events.pedestal;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PedestalEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    @Getter
    @Setter
    private ArmorStand armorStand;

    @Getter
    @Setter
    private Item item;

    @Getter
    @Setter
    private Location location;

    @Getter
    @Setter
    private boolean cancelled = false;

    @Getter
    @Setter
    private Player player;

    public PedestalEvent(Location location, Player player) {
        this.armorStand = null;
        this.item = null;
        this.location = location;
        this.player = player;
    }

    public PedestalEvent(ArmorStand armorStand, Item item, Location location, Player player) {
        this.armorStand = armorStand;
        this.item = item;
        this.location = location;
        this.player = player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}