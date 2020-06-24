package me.wsman217.healthblocker.alters;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

public class Origin {

    public Material materialOrigin= null;
    public BlockData dataOrigin = null;

    public Origin(Material materialOrigin) {
        this.materialOrigin = materialOrigin;
    }

    public Origin(BlockData dataOrigin) {
        this.dataOrigin = dataOrigin;
    }

    public boolean isMaterialOrigin() {
        return materialOrigin != null;
    }
}
