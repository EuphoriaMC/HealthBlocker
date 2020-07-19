package me.wsman217.healthblocker.alter;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Pedestal implements Listener {
    private final List<Material> pedestalBlocks = Arrays.asList(Material.QUARTZ_PILLAR, Material.POLISHED_BASALT, Material.CUT_SANDSTONE,
            Material.CUT_RED_SANDSTONE);

    @EventHandler
    public void onPedestalClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        if (e.getHand() != EquipmentSlot.HAND)
            return;
        if (e.getClickedBlock() == null)
            return;
        if (!pedestalBlocks.contains(e.getClickedBlock().getType()))
            return;
        Location pedestalLocation = e.getClickedBlock().getLocation();
        if (pedestalLocation.getWorld() == null)
            return;
        Collection<Entity> nearbyEntities = pedestalLocation.getWorld().getNearbyEntities(pedestalLocation, .5, 1.5, .5);
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

        if (armorStand == null && e.getItem() != null) {
            armorStand = (ArmorStand) pedestalLocation.getWorld().spawnEntity(pedestalLocation.clone().add(.5, 0, .5), EntityType.ARMOR_STAND);
            armorStand.setCustomName("pedestal_armor_stand");
            armorStand.setCustomNameVisible(false);
            armorStand.setInvulnerable(true);
            armorStand.setGravity(false);
            armorStand.setVisible(false);
            armorStand.setBasePlate(false);
            armorStand.setCanPickupItems(false);
        }

        if (armorStand != null) {
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
                        item.setVelocity(new Vector(random(), random() + .25, random()));
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
    }

    private static double random() {
        return (Math.random() * .5) - .25;
    }

    public static PedestalHolder getPedestal(Block block) {
        Location pedestalLocation = block.getLocation();

        if (pedestalLocation.getWorld() == null)
            return null;
        Collection<Entity> nearbyEntities = pedestalLocation.getWorld().getNearbyEntities(pedestalLocation, .5, 1.25, .5);
        System.out.println(nearbyEntities);
        ArmorStand armorStand = null;
        Item item = null;
        for (Entity entity : nearbyEntities) {
            if (entity.getType() == EntityType.ARMOR_STAND) {
                if (entity.getCustomName() == null)
                    continue;
                if (!ChatColor.stripColor(entity.getCustomName()).equalsIgnoreCase("pedestal_armor_stand"))
                    continue;
                armorStand = (ArmorStand) entity;
            } else if (entity.getType() == EntityType.DROPPED_ITEM) {
                if (armorStand == null)
                    continue;

                EntityEquipment equipment = armorStand.getEquipment();
                if (equipment == null || equipment.getBoots() == null)
                    continue;

                ItemStack boots = equipment.getBoots();
                if (boots.getType() == Material.AIR)
                    continue;

                if (!((Item) entity).getItemStack().isSimilar(boots))
                    continue;
                item = (Item) entity;
            }
        }

        return new PedestalHolder(armorStand, item);
    }

    public static class PedestalHolder {
        public ArmorStand armorStand;
        public Item item;

        public PedestalHolder(ArmorStand armorStand, Item item) {
            this.armorStand = armorStand;
            this.item = item;
        }
    }
}
