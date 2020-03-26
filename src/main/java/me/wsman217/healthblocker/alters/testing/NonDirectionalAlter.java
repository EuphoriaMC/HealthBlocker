package me.wsman217.healthblocker.alters.testing;

import me.wsman217.healthblocker.alters.AlterInterface;
import me.wsman217.healthblocker.alters.Origin;
import me.wsman217.healthblocker.utils.Triplet;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

import java.util.HashMap;

public class NonDirectionalAlter extends AlterInterface {
    private static Origin origin;
    private static HashMap<Triplet<Integer, Integer, Integer>, Material> blockList;
    private static HashMap<Triplet<Integer, Integer, Integer>, BlockData> blockData;

    static {
        origin = new Origin(Bukkit.createBlockData("minecraft:piston_head[facing=up]"));

        blockList = new HashMap<>();
        blockList.put(createTriplet(0, -1, 0), Material.SEA_LANTERN);
        blockList.put(createTriplet(0, -1, -1), Material.REDSTONE_BLOCK);
        blockList.put(createTriplet(0 , -1, -3), Material.SEA_LANTERN);

        blockData = new HashMap<>();
        BlockData daylightDetectorData = Bukkit.createBlockData("minecraft:daylight_detector[inverted=true]");
        blockData.put(createTriplet(0, 0, -3), daylightDetectorData);
        blockData.put(createTriplet(2, 0, -2), daylightDetectorData);
    }

    public NonDirectionalAlter() {
        super(origin, blockList);
        setBlockData(blockData);
    }
}
