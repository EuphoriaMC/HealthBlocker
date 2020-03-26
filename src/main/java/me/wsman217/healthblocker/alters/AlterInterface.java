package me.wsman217.healthblocker.alters;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.utils.Triplet;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AlterInterface {

    private Origin origin;
    //HashMap of directions linked to a block material.
    //Directions come in x y z order.
    private HashMap<Triplet<Integer, Integer, Integer>, Material> blockList;
    private HashMap<Triplet<Integer, Integer, Integer>, BlockData> blockData;
    private boolean isDirectional = false;

    public AlterInterface(Origin origin, HashMap<Triplet<Integer, Integer, Integer>, Material> blockList) {
        this.origin = origin;
        this.blockList = blockList;
    }

    public static Triplet<Integer, Integer, Integer> createTriplet(Integer first, Integer second, Integer third) {
        return new Triplet<>(first, second, third);
    }

    public HashMap<Triplet<Integer, Integer, Integer>, Material> getBlockList() {
        return blockList;
    }

    public void setDirectional(boolean isDirectional) {
        this.isDirectional = isDirectional;
    }

    public ArrayList<Block> toBlockArray(Block origin) {
        World world = origin.getWorld();
        Location originLoc = origin.getLocation();
        BlockState originState = origin.getState();
        ArrayList<Block> blockList = new ArrayList<>();
        if (isDirectional) {

        }
        return blockList;
    }

    public void setBlockData(HashMap<Triplet<Integer, Integer, Integer>, BlockData> blockData) {
        this.blockData = blockData;
    }

    public void createStructure(Block origin) {
        if (isDirectional) {
            return;
        } else {
            World world = origin.getWorld();
            for (int i = 0; i < 4; i++) {
                for (Triplet<Integer, Integer, Integer> coords : blockList.keySet()) {
                    Material mat = blockList.get(coords);
                    Location originLoc = origin.getLocation().clone();
                    coordManip(i, coords, originLoc);
                    System.out.println("X:" + originLoc.getBlockX() + " Y:" + originLoc.getBlockY() + " Z:" + originLoc.getBlockZ() + " Mat:" + mat.toString());
                    world.getBlockAt(originLoc).setType(mat);
                }
            }
            for (int i = 0; i < 4; i++) {
                for (Triplet<Integer, Integer, Integer> coords : blockData.keySet()) {
                    BlockData data = blockData.get(coords);
                    Location originLoc = origin.getLocation().clone();
                    coordManip(i, coords, originLoc);
                    System.out.println("X:" + originLoc.getBlockX() + " Y:" + originLoc.getBlockY() + " Z:" + originLoc.getBlockZ() + " Data:" + data.toString());
                    world.getBlockAt(originLoc).setBlockData(data);
                }
            }
            if (this.origin.isMaterialOrigin()) {
                System.out.println("TestMat");
                origin.setType(this.origin.materialOrigin);
            }
            else {
                System.out.println("TestData");
                System.out.println(this.origin.dataOrigin.getAsString());
                Bukkit.getScheduler().runTaskLater(HealthBlocker.getInstance(), () -> origin.setBlockData(this.origin.dataOrigin), 1);
//                origin.setBlockData(this.origin.dataOrigin);
            }
        }
    }

    private void coordManip(int i, Triplet<Integer, Integer, Integer> coords, Location originLoc) {
        switch (i) {
            //North
            case 0:
                originLoc.add(coords.getKey(), coords.getFirstValue(), coords.getSecondValue());
                break;
            //East
            case 1:
                originLoc.add(-coords.getSecondValue(), coords.getFirstValue(), coords.getKey());
                break;
            //South
            case 2:
                originLoc.add(-coords.getKey(), coords.getFirstValue(), -coords.getSecondValue());
                break;
            //West
            case 3:
                originLoc.add(coords.getSecondValue(), coords.getFirstValue(), -coords.getKey());
        }
    }
}
