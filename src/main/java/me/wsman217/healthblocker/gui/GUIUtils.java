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
            TypeShapedRecipe shapedRecipe = (TypeShapedRecipe) foodInterface.getRecipe().getRecipeType();
            int index = 0;
            HashMap<Integer, Character> indexedChars = new HashMap<>();
            for (String line : shapedRecipe.getShape())
                for (char character : line.toCharArray()) {
                    indexedChars.put(index, character);
                    index++;
                }

            for (int key : indexedChars.keySet())
                inv.setItem(key + 1, shapedRecipe.getInputs().get(indexedChars.get(key)));

            inv.setItem(0, shapedRecipe.getOutput());

            p.openInventory(inv);
        } else if (recipeType instanceof TypeShapelessRecipe) {
            Inventory inv = Bukkit.createInventory(new CraftingHolder(), InventoryType.WORKBENCH, foodInterface.getName());
            TypeShapelessRecipe shapelessRecipe = (TypeShapelessRecipe) foodInterface.getRecipe().getRecipeType();

            int index = 1;
            for (HashMap<ItemStack, Integer> maps : shapelessRecipe.getInputs())
                for (ItemStack itemStack : maps.keySet())
                    for (int i = 0; i < maps.get(itemStack); i++) {
                        inv.setItem(index, itemStack);
                        index++;
                    }
            inv.setItem(0, shapelessRecipe.getOutput());
            p.openInventory(inv);
        }
    }
}
