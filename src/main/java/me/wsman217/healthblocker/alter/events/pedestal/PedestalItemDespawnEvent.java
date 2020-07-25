package me.wsman217.healthblocker.alter.events.pedestal;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PedestalItemDespawnEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    @Getter
    @Setter
    private boolean cancelled = true;

    @Getter
    @Setter
    private ArmorStand armorStand;

    @Getter
    @Setter
    private Item item;

    public PedestalItemDespawnEvent(ArmorStand armorStand, Item item) {
        this.armorStand = armorStand;
        this.item = item;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
