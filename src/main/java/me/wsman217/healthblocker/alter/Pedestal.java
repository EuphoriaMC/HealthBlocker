package me.wsman217.healthblocker.alter;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.*;

public class Pedestal implements Listener {
    private final List<Material> pedestalBlocks = Arrays.asList(Material.QUARTZ_PILLAR, Material.POLISHED_BASALT, Material.CUT_SANDSTONE,
            Material.CUT_RED_SANDSTONE, Material.POLISHED_BLACKSTONE_WALL);

    @EventHandler
    public void onPedestalClick(PlayerInteractEvent e) {
        System.out.println("Interact");
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        System.out.println("Right Click Block");
        if (e.getClickedBlock() == null)
            return;
        System.out.println("Not null");
        if (!pedestalBlocks.contains(e.getClickedBlock().getType()))
            return;
        System.out.println("Contains");
        Location pedestalLocation = e.getClickedBlock().getLocation();
        if (pedestalLocation.getWorld() == null)
            return;
        System.out.println("World");
        Collection<Entity> nearbyEntities = pedestalLocation.getWorld().getNearbyEntities(pedestalLocation, 0, 1, 0);
        System.out.println(nearbyEntities);
        ArmorStand armorStand = null;
        for (Entity entity : nearbyEntities) {
            if (entity.getType() != EntityType.ARMOR_STAND)
                continue;
            if (entity.getCustomName() == null)
                continue;
            if (!ChatColor.stripColor(entity.getCustomName()).equalsIgnoreCase("pedestal_armor_stand"))
                continue;
            armorStand = (ArmorStand) entity;
            break;
        }

        if (armorStand == null) {
            armorStand = (ArmorStand) pedestalLocation.getWorld().spawnEntity(pedestalLocation.clone().add(.5, 0, .5), EntityType.ARMOR_STAND);
            armorStand.setCustomName("pedestal_armor_stand");
            armorStand.setCustomNameVisible(false);
            armorStand.setInvulnerable(true);
            armorStand.setGravity(false);
            armorStand.setVisible(false);
            armorStand.setBasePlate(false);
            armorStand.setCanPickupItems(false);
        }

        EntityEquipment equipment = armorStand.getEquipment();
        if (equipment != null && equipment.getBoots() != null) {
            ItemStack boots = equipment.getBoots();
            if (boots.getType() != Material.AIR) {
                for (Entity entity : nearbyEntities) {
                    if (entity.getType() != EntityType.DROPPED_ITEM)
                        continue;
                    if (!((Item) entity).getItemStack().isSimilar(boots))
                        continue;
                    Item item = (Item) entity;
                    item.setTicksLived(1);
                    item.setPickupDelay(4);
                    item.setGravity(true);
                    item.setVelocity(new Vector(random(), random(), random()));
                    armorStand.remove();
                    break;
                }
            }
        }

        ItemStack itemInHand = e.getItem();
        if (itemInHand == null)
            return;
        armorStand.getEquipment().setBoots(itemInHand);
        Item itemEntity = pedestalLocation.getWorld().dropItem(pedestalLocation.clone().add(.5, 1, .5), itemInHand.clone());
        itemEntity.setPickupDelay(32767);
        itemEntity.setInvulnerable(true);
        //itemEntity.setTicksLived(-32768);
        itemEntity.setVelocity(itemEntity.getVelocity().zero());
        itemEntity.setGravity(false);
        itemInHand.setAmount(0);
    }

    private double random() {
        return (Math.random() * .5) - .25;
    }
}
