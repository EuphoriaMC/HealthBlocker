package me.wsman217.healthblocker.gui;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.gui.holders.CraftingHolder;
import me.wsman217.healthblocker.gui.holders.Tier1Holder;
import me.wsman217.healthblocker.gui.holders.Tier2Holder;
import me.wsman217.healthblocker.gui.holders.Tier3Holder;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.utils.recipeutils.types.RecipeType;
import me.wsman217.healthblocker.utils.recipeutils.types.TypeShapedRecipe;
import me.wsman217.healthblocker.utils.recipeutils.types.TypeShapelessRecipe;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GUIUtils implements Listener {

    static void TierListenerStuff(InventoryClickEvent e, int tier) {
        if (!(e.getWhoClicked() instanceof Player))
            return;
        if (e.getCurrentItem() == null)
            return;
        e.setCancelled(true);
        ItemStack clickedItem = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        CustomFoodItem foodInterface = CustomFoodHandler.getByItemStack(clickedItem);
        if (foodInterface == null)
            return;
        p.closeInventory();
        RecipeType recipeType = foodInterface.getRecipe().getRecipeType();
        if (recipeType instanceof TypeShapedRecipe) {
            CraftingHolder holder = new CraftingHolder().setTier(tier);
            Inventory inv = Bukkit.createInventory(holder, InventoryType.WORKBENCH, foodInterface.getName());
            //Get the shaped recipe
            TypeShapedRecipe shapedRecipe = (TypeShapedRecipe) foodInterface.getRecipe().getRecipeType();
            //The index of the inv
            int index = 1;
            HashMap<Integer, Character> indexedChars = new HashMap<>();
            //Loop through the lines of the shaped recipe
            for (String line : shapedRecipe.getShape())
                //Loop through each char of the lines of the shaped recipe
                for (char character : line.toCharArray()) {
                    //Add the char and the index to the hashmap
                    indexedChars.put(index, character);
                    index++;
                }

            //Loop through the hashmap and put the items in the inv
            for (int key : indexedChars.keySet())
                inv.setItem(key, shapedRecipe.getInputs().get(indexedChars.get(key)));

            //Set the output and open the inv
            inv.setItem(0, shapedRecipe.getOutput());
            p.openInventory(inv);
        } else if (recipeType instanceof TypeShapelessRecipe) {
            CraftingHolder holder = new CraftingHolder().setTier(tier);
            Inventory inv = Bukkit.createInventory(holder, InventoryType.WORKBENCH, foodInterface.getName());
            TypeShapelessRecipe shapelessRecipe = (TypeShapelessRecipe) foodInterface.getRecipe().getRecipeType();

            int index = 1;
            //Loop through the inputs of the shapeless recipe
            for (HashMap<ItemStack, Integer> maps : shapelessRecipe.getInputs())
                //Then loop through the keys of the hashmap
                for (ItemStack itemStack : maps.keySet())
                    //Then add however many of the specific item to the inventory
                    for (int i = 0; i < maps.get(itemStack); i++) {
                        inv.setItem(index, itemStack);
                        index++;
                    }
            //Set the output and open the inventory
            inv.setItem(0, shapelessRecipe.getOutput());
            p.openInventory(inv);
        }
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent e) {
        InventoryHolder inventoryHolder = e.getInventory().getHolder();
        if (inventoryHolder instanceof CraftingHolder)
            if (((CraftingHolder) inventoryHolder).naturallyClosed) {
                switch (((CraftingHolder) inventoryHolder).getTier()) {
                    case 1:
                        Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> new Tier1((Player) e.getPlayer()).openInv(), 1);
                        break;
                    case 2:
                        Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> new Tier2((Player) e.getPlayer()).openInv(), 1);
                        break;
                    case 3: {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> new Tier3((Player) e.getPlayer()).openInv(), 1);
                        break;
                    }
                }
                return;
            }
        if (inventoryHolder instanceof Tier1Holder)
            if (((Tier1Holder) inventoryHolder).naturallyClosed)
                Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> new CategoryView((Player) e.getPlayer()).openInv(), 1);
        if (inventoryHolder instanceof Tier2Holder)
            if (((Tier2Holder) inventoryHolder).naturallyClosed)
                Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> new CategoryView((Player) e.getPlayer()).openInv(), 1);
        if (inventoryHolder instanceof Tier3Holder)
            if (((Tier3Holder) inventoryHolder).naturallyClosed)
                Bukkit.getScheduler().scheduleSyncDelayedTask(HealthBlocker.getInstance(), () -> new CategoryView((Player) e.getPlayer()).openInv(), 1);
    }
}
