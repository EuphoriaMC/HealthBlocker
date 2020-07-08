package me.wsman217.healthblocker.alter;

import me.wsman217.healthblocker.utils.Triplet;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Pattern {

    private final Material origin;
    private final HashMap<Triplet<Integer, Integer, Integer>, ArrayList<Material>> schematic;

    public Pattern(Material origin) {
        this.origin = origin;
        schematic = new HashMap<>();
    }

    public void addLocation(int x, int y, int z, Material... blocks) {
        Triplet<Integer, Integer, Integer> offset = new Triplet<>(x, y, z);
        ArrayList<Material> blockList = (ArrayList<Material>) Arrays.asList(blocks);
        schematic.put(offset, blockList);
    }

    public boolean isThisPattern(Location location) {
        if (location.getBlock().getType() != origin)
            return false;
        keyLoop: for (Triplet<Integer, Integer, Integer> key : schematic.keySet()) {
            Location clonedLocation = location.clone().add(key.getKey(), key.getFirstValue(), key.getSecondValue());
            for (Material arrayedMaterial : schematic.get(key)) {
                if (arrayedMaterial != clonedLocation.getBlock().getType())
                    continue;
                continue keyLoop;
            }
            return false;
        }
        return true;
    }
}
