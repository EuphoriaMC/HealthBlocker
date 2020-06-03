package me.wsman217.healthblocker.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import me.wsman217.healthblocker.utils.recipeutils.types.TypeShapedRecipe;
import me.wsman217.healthblocker.utils.recipeutils.types.TypeShapelessRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class CraftingListener implements Listener {

    @EventHandler
    public void craftPrepHandler(PrepareItemCraftEvent e) {
        /*ShapedRecipeStorage shapedRecipe = getRecipeShape(e.getInventory());
        System.out.println(Arrays.toString(shapedRecipe.shape));
        shapedRecipe.normalize();
        System.out.println(Arrays.toString(shapedRecipe.shapeNormalized));
        shapedRecipe.reverseShape();
        System.out.println(Arrays.toString(shapedRecipe.shapeReverse));*/
        //Check if its a custom food
        if (e.getInventory().getResult() == null)
            return;
        NBTItem nbtItem = new NBTItem(e.getRecipe().getResult());
        boolean isCustomFood = nbtItem.getBoolean("custom_food");
        if (!isCustomFood)
            return;
        String customFoodType = nbtItem.getString("food_type");
        CustomFoodItem foodType = CustomFoodHandler.getFromNameSpace(customFoodType);
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
            ShapedRecipeStorage shapedRecipe = getRecipeShape(e.getInventory());
            shapedRecipe.normalize();
            shapedRecipe.reverseShape();
            TypeShapedRecipe shRecipe = (TypeShapedRecipe) recipe.getRecipeType();
            boolean check = shapedRecipe.checkRecipe(shRecipe.getShape(), shRecipe.getInputs());
            if (!check)
                for (HumanEntity p : (e.getViewers())) {
                    e.getInventory().setResult(new ItemStack(Material.AIR));
                    p.sendMessage(ChatColor.RED + "You do not have the correct ingredients to craft this item.");
                }

        } else if (recipe.getRecipeType() instanceof TypeShapelessRecipe) {
            TypeShapelessRecipe shapelessRecipe = (TypeShapelessRecipe) recipe.getRecipeType();
        }
    }

    private ShapedRecipeStorage getRecipeShape(Inventory inv) {
        HashMap<ItemStack, Character> recipeStore = new HashMap<>();
        String[] shape;
        ItemStack[] contents = inv.getContents();
        ArrayList<String> holder = new ArrayList<>();
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();

        ShapedRecipeStorage recipeStorage;

        if (inv.getType() == InventoryType.WORKBENCH) {
            char index = 'A';
            for (int i = 1; i <= 9; i++) {
                ItemStack itemToTest = contents[i];
                ItemStack item = itemToTest.clone();
                item.setAmount(1);
                item:
                if (item.getType() != Material.AIR) {
                    if (i <= 3) {
                        if (recipeStore.get(item) != null)
                            line1.append(recipeStore.get(item));
                        else {
                            line1.append(index);
                            recipeStore.put(item, index);
                            index++;
                        }
                        break item;
                    } else if (i <= 6) {
                        if (recipeStore.get(item) != null)
                            line2.append(recipeStore.get(item));
                        else {
                            line2.append(index);
                            recipeStore.put(item, index);
                            index++;
                        }
                        break item;
                    } else {
                        if (recipeStore.get(item) != null)
                            line3.append(recipeStore.get(item));
                        else {
                            line3.append(index);
                            recipeStore.put(item, index);
                            index++;
                        }
                        break item;
                    }
                } else air:if (item.getType() == Material.AIR) {
                    if (i <= 3) {
                        line1.append(" ");
                        break air;
                    } else if (i <= 6) {
                        line2.append(" ");
                        break air;
                    } else {
                        line3.append(" ");
                        break air;
                    }
                }
            }
        } else if (inv.getType() == InventoryType.CRAFTING) {
            char index = 'A';
            for (int i = 1; i <= 4; i++) {
                ItemStack itemToTest = contents[i];
                ItemStack item = itemToTest.clone();
                item.setAmount(1);
                item:
                if (item.getType() != Material.AIR) {
                    if (i <= 2) {
                        if (recipeStore.get(item) != null)
                            line1.append(recipeStore.get(item));
                        else
                            line1.append(index);
                    } else {
                        if (recipeStore.get(item) != null)
                            line2.append(recipeStore.get(item));
                        else
                            line2.append(index);
                    }
                    if (recipeStore.get(item) == null) {
                        recipeStore.put(item, index);
                        index++;
                    }
                    break item;
                } else air:if (item.getType() == Material.AIR) {
                    if (i <= 2) {
                        line1.append(" ");
                    } else {
                        line2.append(" ");
                    }
                    break air;
                }
            }
            line1.append(" ");
            line2.append(" ");
            line3.append("   ");
        }
        holder.add(line1.toString());
        holder.add(line2.toString());
        holder.add(line3.toString());
        shape = new String[holder.size()];
        shape = holder.toArray(shape);

        HashMap<Character, ItemStack> recipe = new HashMap<>();
        for (ItemStack item : recipeStore.keySet())
            recipe.put(recipeStore.get(item), item);
        recipeStorage = new ShapedRecipeStorage(shape, recipe);
        return recipeStorage;
    }

    public static class ShapedRecipeStorage {
        public String[] shape;
        public String[] shapeReverse;
        public String[] shapeNormalized;
        public HashMap<Character, ItemStack> recipe;

        public ShapedRecipeStorage(String[] shape, HashMap<Character, ItemStack> recipe) {
            this.recipe = recipe;
            this.setShape(shape);
        }

        public ShapedRecipeStorage setShape(String[] shape) {
            this.shape = shape;
            return this;
        }

        public void reverseShape() {
            ArrayList<String> reversed = new ArrayList<>();
            for (String line : shapeNormalized) {
                StringBuilder sb = new StringBuilder(line);
                sb.reverse();
                reversed.add(sb.toString());
            }
            shapeReverse = new String[reversed.size()];
            shapeReverse = reversed.toArray(shapeReverse);
        }

        public void normalize() {
            //Normalize the y axis of the crafting grid.
            ArrayList<String> yNormalized = new ArrayList<>();
            for (String line : shape)
                if (!line.trim().isEmpty())
                    yNormalized.add(line);

            //Switch the axises so that it is easier to normalize the x axis will have to switch it back after done.
            ArrayList<String> switchedAxis = new ArrayList<>();
            StringBuilder line1 = new StringBuilder();
            StringBuilder line2 = new StringBuilder();
            StringBuilder line3 = new StringBuilder();
            for (String line : yNormalized) {
                char[] sepChars = line.toCharArray();
                line1.append(sepChars[0]);
                line2.append(sepChars[1]);
                line3.append(sepChars[2]);
            }
            switchedAxis.add(line1.toString());
            switchedAxis.add(line2.toString());
            switchedAxis.add(line3.toString());

            //Normalize the x axis now.
            ArrayList<String> xNormalizedSwitched = new ArrayList<>();
            for (String line : switchedAxis)
                if (!line.trim().isEmpty())
                    xNormalizedSwitched.add(line);

            //Switch x axis back to the x axis.
            ArrayList<String> xNormalized = new ArrayList<>();
            line1 = new StringBuilder();
            line2 = new StringBuilder();
            line3 = new StringBuilder();
            for (String line : xNormalizedSwitched) {
                char[] sepChars = line.toCharArray();
                if (sepChars.length >= 1)
                    line1.append(sepChars[0]);
                if (sepChars.length >= 2)
                    line2.append(sepChars[1]);
                if (sepChars.length >= 3)
                    line3.append(sepChars[2]);
            }
            String one = line1.toString(), two = line2.toString(), three = line3.toString();
            if (!one.isEmpty())
                xNormalized.add(one);
            if (!two.isEmpty())
                xNormalized.add(two);
            if (!three.isEmpty())
                xNormalized.add(three);

            //Return the normalized recipe
            String[] holder = new String[xNormalized.size()];
            holder = xNormalized.toArray(holder);
            this.shapeNormalized = holder;
        }

        public boolean checkRecipe(String[] customFoodShape, HashMap<Character, ItemStack> customFoodRecipe) {
            if (customFoodShape.length != shapeNormalized.length)
                return false;
            for (int i = 0; i < customFoodShape.length; i++)
                if (customFoodShape[i].length() != shapeNormalized[i].length())
                    return false;
            for (int i = 0; i < customFoodShape.length; i++) {
                char[] charset1 = customFoodShape[i].toCharArray(), charset2 = shapeNormalized[i].toCharArray(), charset3 = shapeReverse[i].toCharArray();
                for (int j = 0; j < charset1.length; j++) {
                    if (charset1[j] == ' ')
                        if (charset2[j] == ' ' || charset3[j] == ' ')
                            continue;
                    if (!customFoodRecipe.get(charset1[j]).isSimilar(recipe.get(charset2[j])) || !customFoodRecipe.get(charset1[j]).isSimilar(recipe.get(charset3[j])))
                        return false;
                }
            }
            return true;
        }

        public static class ShapelessRecipeStorage {

        }
    }
}