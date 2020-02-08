package me.wsman217.healthblocker.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.items.FoodInterface;
import me.wsman217.healthblocker.recipeutils.Recipe;
import me.wsman217.healthblocker.recipeutils.types.TypeShapedRecipe;
import me.wsman217.healthblocker.recipeutils.types.TypeShapelessRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CraftingListener implements Listener {

    @EventHandler
    public void craftingListener(CraftItemEvent e) {
        /*//Check if its a custom food
        NBTItem nbtItem = new NBTItem(e.getRecipe().getResult());
        boolean isCustomFood = nbtItem.getBoolean("custom_food");
        if (!isCustomFood)
            return;
        String customFoodType = nbtItem.getString("food_type");
        FoodInterface foodType = CustomItemHandler.getFromNameSpace(customFoodType);
        //Check if they have permission to craft the food
        if (!e.getWhoClicked().hasPermission(foodType.getPermission())) {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage(ChatColor.RED + "You do not have permission to craft this item.");
        }

        Recipe recipe = foodType.getRecipe();
        if (recipe.getRecipeType() instanceof TypeShapedRecipe) {
            TypeShapedRecipe shapedRecipe = (TypeShapedRecipe) recipe.getRecipeType();
            HashMap<Character, ItemStack> inputs = shapedRecipe.getInputs();
            String[] shape = shapedRecipe.getShape();
            int index = 0;
            for (String line : shape) {
                for (Character letter : line.toCharArray()) {
                    //ItemStack
                }
            }
        } else if (recipe.getRecipeType() instanceof TypeShapelessRecipe) {
            TypeShapelessRecipe shapelessRecipe = (TypeShapelessRecipe) recipe.getRecipeType();
        }*/
    }

    @EventHandler
    public void craftPrepHandler(PrepareItemCraftEvent e) {
        //Check if its a custom food
        if (e.getInventory().getResult() == null)
            return;
        NBTItem nbtItem = new NBTItem(e.getRecipe().getResult());
        boolean isCustomFood = nbtItem.getBoolean("custom_food");
        if (!isCustomFood)
            return;
        String customFoodType = nbtItem.getString("food_type");
        FoodInterface foodType = CustomItemHandler.getFromNameSpace(customFoodType);
        //Check if they have permission to craft the food
        //e.getView().getPlayer()
        for (HumanEntity p : (e.getViewers())) {
            if (!p.hasPermission(foodType.getPermission())) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
                p.sendMessage(ChatColor.RED + "You do not have permission to craft this item.");
            }
        }

        Recipe recipe = foodType.getRecipe();
        if (recipe.getRecipeType() instanceof TypeShapedRecipe) {
            TypeShapedRecipe shapedRecipe = (TypeShapedRecipe) recipe.getRecipeType();
            HashMap<Character, ItemStack> inputs = shapedRecipe.getInputs();
            String[] shape = shapedRecipe.getShape();

        } else if (recipe.getRecipeType() instanceof TypeShapelessRecipe) {
            TypeShapelessRecipe shapelessRecipe = (TypeShapelessRecipe) recipe.getRecipeType();
        }
    }

    public static class ShapedRecipeStorage {
        private String[] shape;
        public String[] shapeReverse;
        public String[] shapeNormalized;
        public ShapedRecipeStorage setShape(String... shape) {
            this.shape = shape;
            return this;
        }

        public String[] reverseShape() {
            ArrayList<String> reversed = new ArrayList<>();
            for (String line : shape) {
                StringBuilder sb = new StringBuilder(line);
                sb.reverse();
                reversed.add(sb.toString());
            }
            shapeReverse = (String[]) reversed.toArray();
            return shapeReverse;
        }

        public String[] normalize() {
            ArrayList<String> normalized = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (String line : shape)
                sb.append(sb.length() == 0 ? "" : ",").append(line);
            String combined = sb.toString();
            Pattern pat = Pattern.compile("[,]");
            Matcher match = pat.matcher(combined);
            int lineCount = 0;
            while (match.find())
                lineCount++;
            int lastCommaIndex = 0;
            String[] holder = new String[lineCount];
            for (int i = 0; i < lineCount; i++) {
                String line = combined.substring(lastCommaIndex, combined.indexOf(',', lastCommaIndex));
                
            }
        }
    }
}
