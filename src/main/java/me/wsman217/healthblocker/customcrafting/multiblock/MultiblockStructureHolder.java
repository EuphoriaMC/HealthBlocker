package me.wsman217.healthblocker.customcrafting.multiblock;

import me.wsman217.healthblocker.HealthBlocker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

public class MultiblockStructureHolder {

    private Block edge1, edge2, edge3, edge4;
    private Block corner1, corner2, corner3, corner4;
    private Block innEdge1, innEdge2, innEdge3, innEdge4;
    private Block redstoneBlock, pistonHead;

    /**
     * Create a new instance of MultiblockStructureHolder.
     *
     * @param loc The location of the redstone block should come directly
     *            from the database or where a multiblock will be placed.
     */
    public MultiblockStructureHolder(Location loc) {
        this.redstoneBlock = loc.getBlock();
        this.pistonHead = loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());

        this.edge1 = loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() - 3);
        this.edge2 = loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() + 3);
        this.edge3 = loc.getWorld().getBlockAt(loc.getBlockX() - 3, loc.getBlockY() + 1, loc.getBlockZ());
        this.edge4 = loc.getWorld().getBlockAt(loc.getBlockX() + 3, loc.getBlockY() + 1, loc.getBlockZ());

        this.innEdge1 = loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 2);
        this.innEdge2 = loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 2);
        this.innEdge3 = loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ());
        this.innEdge4 = loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ());

        this.corner1 = loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() + 2);
        this.corner2 = loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() - 2);
        this.corner3 = loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() - 2);
        this.corner4 = loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() + 2);
    }

    public boolean isSimilar(Location loc) {
        MultiblockStructureHolder holder = new MultiblockStructureHolder(loc);
        return holder.edge1.getLocation().equals(this.edge1.getLocation()) && holder.edge2.getLocation().equals(this.edge2.getLocation())
                && holder.edge3.getLocation().equals(this.edge3.getLocation()) && holder.edge4.getLocation().equals(this.edge4.getLocation())
                && holder.corner1.getLocation().equals(this.corner1.getLocation()) && holder.corner2.getLocation().equals(this.corner2.getLocation())
                && holder.corner3.getLocation().equals(this.corner3.getLocation()) && holder.corner4.getLocation().equals(this.corner4.getLocation())
                && holder.innEdge1.getLocation().equals(this.innEdge1.getLocation()) && holder.innEdge2.getLocation().equals(this.innEdge2.getLocation())
                && holder.innEdge3.getLocation().equals(this.innEdge3.getLocation()) && holder.innEdge4.getLocation().equals(this.innEdge4.getLocation())
                && holder.redstoneBlock.getLocation().equals(this.redstoneBlock.getLocation()) && holder.pistonHead.getLocation().equals(this.pistonHead.getLocation());
    }

    MultiblockStructureHolder createStructure() {
        this.redstoneBlock.breakNaturally();
        this.pistonHead.breakNaturally();
        innEdge1.breakNaturally();
        innEdge2.breakNaturally();
        innEdge3.breakNaturally();
        innEdge4.breakNaturally();
        edge1.breakNaturally();
        edge2.breakNaturally();
        edge3.breakNaturally();
        edge4.breakNaturally();
        corner1.breakNaturally();
        corner2.breakNaturally();
        corner3.breakNaturally();
        corner4.breakNaturally();

        Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> {
            this.redstoneBlock.setType(Material.REDSTONE_BLOCK);
            this.pistonHead.setBlockData(Bukkit.createBlockData("minecraft:piston_head[facing=up]"), false);
            innEdge1.setType(Material.SEA_LANTERN);
            innEdge2.setType(Material.SEA_LANTERN);
            innEdge3.setType(Material.SEA_LANTERN);
            innEdge4.setType(Material.SEA_LANTERN);

            BlockData daylightDetectorData = Bukkit.createBlockData("minecraft:daylight_detector[inverted=true]");
            edge1.setBlockData(daylightDetectorData);
            edge2.setBlockData(daylightDetectorData);
            edge3.setBlockData(daylightDetectorData);
            edge4.setBlockData(daylightDetectorData);
            corner1.setBlockData(daylightDetectorData);
            corner2.setBlockData(daylightDetectorData);
            corner3.setBlockData(daylightDetectorData);
            corner4.setBlockData(daylightDetectorData);
        }, 3L);
        return this;
    }

    MultiblockStructureHolder removeStructure() {
        this.redstoneBlock.setType(Material.AIR);
        this.pistonHead.setType(Material.AIR);
        this.innEdge1.setType(Material.AIR);
        this.innEdge2.setType(Material.AIR);
        this.innEdge3.setType(Material.AIR);
        this.innEdge4.setType(Material.AIR);

        this.edge1.setType(Material.AIR);
        this.edge2.setType(Material.AIR);
        this.edge3.setType(Material.AIR);
        this.edge4.setType(Material.AIR);
        this.corner1.setType(Material.AIR);
        this.corner2.setType(Material.AIR);
        this.corner3.setType(Material.AIR);
        this.corner4.setType(Material.AIR);

        World world = innEdge1.getWorld();
        System.out.println(world);
        Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () ->
                        world.dropItem(this.redstoneBlock.getLocation().add(0, 5, 0), MultiblockItem.item)
                , 3L);
        return this;
    }
}
