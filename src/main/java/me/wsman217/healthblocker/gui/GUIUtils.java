package me.wsman217.healthblocker.gui;

import me.wsman217.healthblocker.gui.holders.CraftingHolder;
import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.items.FoodInterface;
import me.wsman217.healthblocker.recipeutils.types.RecipeType;
import me.wsman217.healthblocker.recipeutils.types.TypeShapedRecipe;
import me.wsman217.healthblocker.recipeutils.types.TypeShapelessRecipe;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

class GUIUtils {

    static void TierListenerStuff(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player))
            return;
        if (e.getCurrentItem() == null)
            return;
        e.setCancelled(true);
        ItemStack clickedItem = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        FoodInterface foodInterface = CustomItemHandler.getByItemStack(clickedItem);
        if (foodInterface == null)
            return;
        p.closeInventory();
        RecipeType recipeType = foodInterface.getRecipe().getRecipeType();
        if (recipeType instanceof TypeShapedRecipe) {
            Inventory inv = Bukkit.createInventory(new CraftingHolder(), InventoryType.WORKBENCH, foodInterface.getName());
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
            Inventory inv = Bukkit.createInventory(new CraftingHolder(), InventoryType.WORKBENCH, foodInterface.getName());
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
}
