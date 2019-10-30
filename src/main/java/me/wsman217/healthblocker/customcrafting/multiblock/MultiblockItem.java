package me.wsman217.healthblocker.customcrafting.multiblock;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.HealthBlocker;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MultiblockItem implements Listener {

    public static ItemStack item;
    public static ShapedRecipe recipe;

    private boolean underDevelopment = true;
    private HealthBlocker plugin;

    public MultiblockItem() {
        this.plugin = HealthBlocker.getInstance();
    }

    public MultiblockItem init() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.PISTON));
        nbtItem.setBoolean("multiblockitem", true);
        item = nbtItem.getItem();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Multiblock Creator");
        meta.setLore(Arrays.asList(ChatColor.BLUE + "Right click with this on a block to",
                ChatColor.BLUE + "place a multiblock structure.",
                ChatColor.BLUE + "This structure will overwrite",
                ChatColor.BLUE + "blocks but the blocks will be dropped as item."));
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        recipe = new ShapedRecipe(new NamespacedKey(plugin, "multiblockitem"), item)
                .shape("ABA", "CDC", "CFC")
                .setIngredient('A', Material.PISTON)
                .setIngredient('B', Material.DAYLIGHT_DETECTOR)
                .setIngredient('C', Material.SEA_LANTERN)
                .setIngredient('D', Material.LIGHT_WEIGHTED_PRESSURE_PLATE)
                .setIngredient('F', Material.REDSTONE_BLOCK);
        plugin.getServer().addRecipe(recipe);
        return this;
    }

    @EventHandler
    public void interactEvent(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        if (!e.hasItem())
            return;
        ItemStack item = e.getItem();
        if (!item.isSimilar(MultiblockItem.item))
            return;
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(item)) {
            e.getPlayer().sendMessage(ChatColor.RED + "Multiblock builder must be used in your main hand.");
            e.setCancelled(true);
            return;
        }
        e.getPlayer().sendMessage(ChatColor.RED + "THIS FEATURE IS NOT READY YET PLEASE CONTACT A DEV AND TELL THEM TO UNCOMMENT SOME CODE.");
        if (plugin.getMbHandler().isInDatabase(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You have already placed a multiblock crafting structure. Please destroy the previous one first.");
            return;
        }
        if (plugin.getMbHandler().isWithing30Blocks(e.getClickedBlock().getLocation())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You can not place 2 crafting multiblocks" +
                    " withing 30 blocks of eachother!");
            return;
        }
        plugin.getMbHandler().addToDatabase(e.getPlayer(), e.getClickedBlock().getLocation());
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            item.setAmount(item.getAmount() - 1);
            e.getPlayer().getInventory().setItemInMainHand(item);
        }
        e.setCancelled(true);
        new MultiblockStructureHolder(e.getClickedBlock().getLocation()).createStructure();
        Particle.DustOptions particle = new Particle.DustOptions(Color.fromBGR(30, 73, 9), 3);
        e.getPlayer().spawnParticle(Particle.REDSTONE, e.getClickedBlock().getLocation().add(0, 1, 0), 100, 4.0, 0.0, 4.0, particle);
        e.getPlayer().sendMessage(ChatColor.AQUA + "Structure successfully created.");
    }

    @EventHandler
    public void craftEvent(CraftItemEvent e) {
        if (!underDevelopment)
            return;
        ItemStack output = e.getRecipe().getResult();
        if (!output.isSimilar(item))
            return;
        if (!e.getWhoClicked().isOp()) {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage(ChatColor.RED + "This feature is currently under development and this " +
                    "recipe is uncraftable at the time. If you believe you should be able to craft this item contact a " +
                    "developer.");
        }
    }
}
