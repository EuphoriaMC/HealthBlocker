package me.wsman217.healthblocker.recipeutils.types;

import me.wsman217.healthblocker.HealthBlocker;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashMap;

public class TypeShapedRecipe implements RecipeType {

    private NamespacedKey key;
    private ItemStack output;
    private ShapedRecipe recipe;
    private HashMap<Character, ItemStack> inputs = new HashMap<>();

    public TypeShapedRecipe(NamespacedKey key, ItemStack output) {
        this.key = key;
        this.output = output;
        this.recipe = new ShapedRecipe(key, output);
    }

    public TypeShapedRecipe shape(String... shape) {
        recipe.shape(shape);
        return this;
    }

    public TypeShapedRecipe setIngredient(char key, ItemStack input) {
        recipe.setIngredient(key, input.getType());
        this.inputs.put(key, input);
        return this;
    }

    public TypeShapedRecipe addRecipe() {
        HealthBlocker.getInstance().getServer().addRecipe(recipe);
        return this;
    }
}
