package me.wsman217.healthblocker.database;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.multiblock.craftingalter.CraftingAlterHolder;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MultiblockHandler {

    private Database db;
    private HealthBlocker plugin = HealthBlocker.getInstance();

    public MultiblockHandler(Database db) {
        this.db = db;
    }

    public void generateTable() {
        Connection connection = this.db.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS multiblock_locs(uuid VARCHAR(40) PRIMARY KEY, x INTEGER, y INTEGER, z INTEGER, world VARCHAR(40))");
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Location getLocation(Player p) {
        Connection connection = this.db.getConnection();
        Location loc = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM multiblock_locs WHERE uuid='"
                    + p.getUniqueId() + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                loc = new Location(plugin.getServer().getWorld(rs.getString("world")),
                        rs.getInt("x"), rs.getInt("y"), rs.getInt("z"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loc;
    }

    private Location getLocation(UUID p) {
        Connection connection = this.db.getConnection();
        Location loc = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM multiblock_locs WHERE uuid='" + p + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                loc = new Location(plugin.getServer().getWorld(rs.getString("world")),
                        rs.getInt("x"), rs.getInt("y"), rs.getInt("z"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loc;
    }

    public CraftingAlterHolder getHolder(Player p) {
        return new CraftingAlterHolder(getLocation(p));
    }

    //I DID A STUPID SOMEWHERE HERE ALSO WILL NOT WANT TO USE .distance JUST DO IT BY HAND BY CHECKING WORLD AND SUCH.
    public boolean isWithing30Blocks(Location ploc) {
        Connection connection = this.db.getConnection();
        boolean isWithin = false;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM multiblock_locs");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UUID pUUID = UUID.fromString(rs.getString("uuid"));
                Location uLoc = getLocation(pUUID);
                if (uLoc.getWorld() == null)
                    continue;
                if (uLoc.getWorld() != ploc.getWorld())
                    continue;
                if (ploc.distance(uLoc) <= 30)
                    isWithin = true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isWithin;
    }

    public boolean isInDatabase(Player p) {
        Connection connection = this.db.getConnection();
        boolean isIn = false;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM multiblock_locs WHERE uuid='" + p.getUniqueId() + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                isIn = true;
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isIn;
    }

    public void addToDatabase(Player p, Location loc) {
        Connection connection = this.db.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO multiblock_locs VALUES(?,?,?,?,?)");
            ps.setString(1, p.getUniqueId().toString());
            ps.setInt(2, loc.getBlockX());
            ps.setInt(3, loc.getBlockY());
            ps.setInt(4, loc.getBlockZ());
            ps.setString(5, loc.getWorld().getName());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFromDatabase(Player p) {
        Connection connection = this.db.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM multiblock_locs WHERE uuid='"
                    + p.getUniqueId() + "'");
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
