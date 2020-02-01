package me.wsman217.healthblocker.recipeutils.types;

import lombok.Getter;
import me.wsman217.healthblocker.HealthBlocker;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeShapelessRecipe implements RecipeType {

    @Getter
    private ArrayList<HashMap<ItemStack, Integer>> inputs;
    @Getter
    private ItemStack output;

    public TypeShapelessRecipe(NamespacedKey key, ArrayList<HashMap<ItemStack, Integer>> inputs, ItemStack output) {
        this.inputs = inputs;
        this.output = output;

        ShapelessRecipe recipe = new ShapelessRecipe(key, output);
        for (HashMap<ItemStack, Integer> input : inputs)
            for (ItemStack hashKey : input.keySet()) {
                recipe.addIngredient(input.get(hashKey), hashKey.getType());
            }
        HealthBlocker.getInstance().getServer().addRecipe(recipe);
    }
}
