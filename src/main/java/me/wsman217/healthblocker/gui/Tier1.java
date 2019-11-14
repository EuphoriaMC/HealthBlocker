package me.wsman217.healthblocker.gui;

import org.bukkit.entity.Player;

class Tier1 {

    private Player p;

    Tier1(Player p) {
        this.p = p;
    }

    Tier1 openInv() {
        return this;
    }
}
