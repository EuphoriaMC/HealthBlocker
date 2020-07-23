package me.wsman217.healthblocker.listeners;

import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.wsman217.healthblocker.alter.events.pedestal.PedestalCreateEvent;
import me.wsman217.healthblocker.alter.events.pedestal.PedestalRemoveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PedestalListeners implements Listener {

    @EventHandler
    public void onPedestalCreateEvent(PedestalCreateEvent e) {
        if (e.getPlayer() == null)
            return;
        GriefPrevention gp = GriefPrevention.instance;
        if (gp.allowBuild(e.getPlayer(), e.getLocation()) != null)
            e.setCancelled(true);
    }

    @EventHandler
    public void onPedestalRemoveEvent(PedestalRemoveEvent e) {
        if (e.getPlayer() == null)
            return;
        GriefPrevention gp = GriefPrevention.instance;
        if (gp.allowBuild(e.getPlayer(), e.getLocation()) != null)
            e.setCancelled(true);
    }
}
