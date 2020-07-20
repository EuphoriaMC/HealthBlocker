package me.wsman217.healthblocker.alter;

import me.wsman217.healthblocker.alter.events.pedestal.PedestalCreateEvent;
import me.wsman217.healthblocker.alter.events.pedestal.PedestalDestroyEvent;
import me.wsman217.healthblocker.alter.events.pedestal.PedestalRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Pedestal implements Listener {
    private static final List<Material> pedestalBlocks = Arrays.asList(Material.QUARTZ_PILLAR, Material.POLISHED_BASALT, Material.CUT_SANDSTONE,
            Material.CUT_RED_SANDSTONE);

    @EventHandler
    public void onPedestalEvent(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getHand() != EquipmentSlot.HAND || e.getClickedBlock() == null
                || isNotPedestalBlock(e.getClickedBlock()) || e.getPlayer().isSneaking())
            return;
        Block pedestalBlock = e.getClickedBlock();
        PedestalHolder holder = getPedestal(pedestalBlock, e.getPlayer());
        ItemStack hand = e.getItem();

        if (holder == null)
            return;

        if (holder.item == null || holder.armorStand == null) {
            createPedestal(pedestalBlock, hand, e.getPlayer());
            return;
        }
        removePedestal(holder);
    }

    @EventHandler
    public void onPedestalBreakEvent(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (!pedestalBlocks.contains(block.getType()))
            return;
        PedestalHolder holder = getPedestal(block, e.getPlayer());
        if (holder == null || holder.isNotPedestal())
            return;

        removePedestal(holder);
    }

    @EventHandler
    public void onFallingEntityPedestal(EntityChangeBlockEvent e) {
        if (e.getEntity().getType() != EntityType.FALLING_BLOCK)
            return;
        Block block = e.getBlock().getWorld().getBlockAt(e.getBlock().getLocation().add(0, -1, 0));
        if (isNotPedestalBlock(block))
            return;
        PedestalHolder holder = getPedestal(block, null);
        if (holder == null || holder.isNotPedestal())
            return;
        e.setCancelled(true);
    }

    private static double random() {
        return (Math.random() * .5) - .25;
    }

    public static boolean isNotPedestalBlock(Block block) {
        return !pedestalBlocks.contains(block.getType());
    }

    private void createPedestal(Block block, ItemStack itemStack, Player player) {
        if (isNotPedestalBlock(block) || itemStack == null)
            return;

        Location location = block.getLocation().clone().add(.5, 0, .5);
        PedestalCreateEvent event = new PedestalCreateEvent(location, itemStack, player);

        Bukkit.getServer().getPluginManager().callEvent(event);

        if (event.isCancelled())
            return;

        ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomName("pedestal_armor_stand");
        armorStand.setInvulnerable(true);
        armorStand.setCustomNameVisible(false);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setBasePlate(false);
        armorStand.setCanPickupItems(false);

        EntityEquipment equipment = armorStand.getEquipment();
        if (equipment == null)
            return;
        equipment.setBoots(itemStack.clone());

        Item itemEntity = block.getWorld().dropItem(location.clone().add(0, 1, 0), itemStack.clone());
        itemEntity.setPickupDelay(32767);
        itemEntity.setInvulnerable(true);
        itemEntity.setVelocity(itemEntity.getVelocity().zero());
        itemEntity.setGravity(false);
        itemStack.setAmount(0);

        new PedestalHolder(armorStand, itemEntity, player);
    }

    public static void removePedestal(PedestalHolder pedestal) {
        if (pedestal.isNotPedestal())
            return;
        ArmorStand armorStand = pedestal.armorStand;
        Item item = pedestal.item;

        PedestalRemoveEvent event = new PedestalRemoveEvent(armorStand, item, item.getItemStack(), armorStand.getLocation(), pedestal.player);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled())
            return;
        item.setItemStack(event.getItemStack());
        item.setTicksLived(1);
        item.setGravity(true);
        item.setPickupDelay(4);
        item.setVelocity(new Vector(random(), random() + .25, random()));
        armorStand.remove();
    }

    public static void destroyPedestal(PedestalHolder pedestal) {
        ArmorStand armorStand = pedestal.armorStand;
        Item item = pedestal.item;

        if (armorStand == null)
            return;

        PedestalDestroyEvent event = new PedestalDestroyEvent(armorStand, item, armorStand.getLocation(), pedestal.player);

        Bukkit.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled())
            return;
        armorStand.remove();
        if (item != null)
            item.remove();
    }

    public static PedestalHolder getPedestal(Block block, Player player) {
        Location pedestalLocation = block.getLocation();

        if (pedestalLocation.getWorld() == null)
            return null;
        Collection<Entity> nearbyEntities = pedestalLocation.getWorld().getNearbyEntities(pedestalLocation.clone().add(.5, 0, .5), 0, 1.25, 0);
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

        return new PedestalHolder(armorStand, item, player);
    }

    public static class PedestalHolder {
        public final ArmorStand armorStand;
        public final Item item;
        public final Player player;

        public PedestalHolder(ArmorStand armorStand, Item item, Player player) {
            this.armorStand = armorStand;
            this.item = item;
            this.player = player;
        }

        public boolean isNotPedestal() {
            return armorStand == null || item == null;
        }
    }
}
