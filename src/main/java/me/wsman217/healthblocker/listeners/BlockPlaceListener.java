package me.wsman217.healthblocker.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @SuppressWarnings("EmptyMethod")
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        /*e.setCancelled(true);
        AlterHandler.nonDirectionalAlter.createStructure(e.getBlock());*/
    }
}
