package me.wsman217.healthblocker.customcrafting.multiblock;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.HealthBlocker;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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

public class RemovalWand implements Listener {

    public static ItemStack item;
    public static ShapedRecipe recipe;

    private boolean underDevelopment = true;
    private HealthBlocker plugin;

    public RemovalWand() {
        this.plugin = HealthBlocker.getInstance();
    }

    public RemovalWand init() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.STICK));
        nbtItem.setBoolean("removalwand", true);
        item = nbtItem.getItem();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Multiblock Remover");
        meta.setLore(Arrays.asList(ChatColor.BLUE + "Right click with this on a block to",
                ChatColor.BLUE + "remove a multiblock structure.",
                ChatColor.BLUE + "This will remove all blocks in",
                ChatColor.BLUE + "the structure and drop the structure block."));
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        recipe = new ShapedRecipe(new NamespacedKey(plugin, "removalwand"), item)
                .shape(" R ", " S ", " S ")
                .setIngredient('R', Material.REDSTONE_BLOCK)
                .setIngredient('S', Material.STICK);
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
        if (!item.isSimilar(RemovalWand.item))
            return;
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(item)) {
            e.getPlayer().sendMessage(ChatColor.RED + "Multiblock remover must be used in your main hand.");
            e.setCancelled(true);
            return;
        }
        e.getPlayer().sendMessage(ChatColor.RED + "THIS FEATURE IS NOT READY YET PLEASE CONTACT A DEV AND TELL THEM TO UNCOMMENT SOME CODE.");
        if (!plugin.getMbHandler().isInDatabase(e.getPlayer())) {
            e.getPlayer().sendMessage(ChatColor.RED + "You do not own a multiblock structure.");
            return;
        }

        //NEED TO CHECK IF IT IS A MULTIBLOCK STRUCTURE.
        MultiblockStructureHolder playersStructure = plugin.getMbHandler().getHolder(e.getPlayer());
        if (!playersStructure.isSimilar(e.getClickedBlock().getLocation())) {
            e.getPlayer().sendMessage(ChatColor.RED + "You may not remove this structure as you do not own it.");
            return;
        }
        plugin.getMbHandler().removeFromDatabase(e.getPlayer());
        playersStructure.removeStructure();
        e.getPlayer().sendMessage(ChatColor.AQUA + "Structure successfully removed.");
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
