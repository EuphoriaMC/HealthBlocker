package me.wsman217.healthblocker.utils.recipeutils.types;

import lombok.Getter;
import me.wsman217.healthblocker.HealthBlocker;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashMap;

public class TypeShapedRecipe implements RecipeType {

    private final ShapedRecipe recipe;
    @Getter
    private final ItemStack output;
    @Getter
    private final HashMap<Character, ItemStack> inputs = new HashMap<>();
    @Getter
    private String[] shape;

    public TypeShapedRecipe(NamespacedKey key, ItemStack output) {
        this.output = output;
        this.recipe = new ShapedRecipe(key, output);
    }

    //shape("sss", "ses", "sss")
    public TypeShapedRecipe shape(String... shape) {
        this.shape = shape;
        recipe.shape(shape);
        return this;
    }

    public TypeShapedRecipe setIngredient(char key, ItemStack input) {
        recipe.setIngredient(key, input.getType());
        this.inputs.put(key, input);
        return this;
    }

    public void addRecipe() {
        HealthBlocker.getInstance().getServer().addRecipe(recipe);
    }
}
