package me.wsman217.healthblocker.alter.events.pedestal;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PedestalExplodedEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    @Getter
    @Setter
    private boolean cancelled = true;

    public PedestalExplodedEvent(Block block) {

    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
