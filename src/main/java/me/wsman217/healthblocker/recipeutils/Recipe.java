package me.wsman217.healthblocker.recipeutils;

import lombok.Getter;
import me.wsman217.healthblocker.recipeutils.types.RecipeType;
import me.wsman217.healthblocker.recipeutils.types.TypeFurnaceRecipe;
import me.wsman217.healthblocker.recipeutils.types.TypeShapedRecipe;
import me.wsman217.healthblocker.recipeutils.types.TypeShapelessRecipe;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {

    private RecipeType recipe;
    private Tier recipeTier;

    //Not in use because furnace recipes are hard to restrict
    public Recipe createFurnaceRecipe(NamespacedKey key, ItemStack input, ItemStack output, float exp, int cookTime) {
        this.recipe = new TypeFurnaceRecipe(key, input, output, exp, cookTime);
        return this;
    }

    /**
     * Create shapeless recipes.
     * @param key The NamespacedKey of the recipe.
     * @param inputs The inputs for the recipe.
     * @param output The output of the recipe.
     * @return An instance of the current Recipe class
     */
    public Recipe createShapelessRecipe(NamespacedKey key, ArrayList<HashMap<ItemStack, Integer>> inputs, ItemStack output) {
        this.recipe = new TypeShapelessRecipe(key, inputs, output);
        return this;
    }

    /**
     * Create a shaped recipe.
     * @param key The NamespacedKey of the recipe.
     * @param output The output of the recipe.
     * @return A new instance of TypeShapedRecipe so you can add the shape and inputs of the recipe.
     */
    public TypeShapedRecipe createShapedRecipe(NamespacedKey key, ItemStack output) {
        this.recipe = new TypeShapedRecipe(key, output);
        return (TypeShapedRecipe) recipe;
    }

    /**
     * Set the tier of the recipe.
     * @param recipeTier Recipe Tier
     * @return An instance of this class.
     */
    public Recipe setRecipeTier(Tier recipeTier) {
        this.recipeTier = recipeTier;
        return this;
    }

    public RecipeType getRecipeType() {
        return recipe;
    }
    public Tier getRecipeTier() {
        return recipeTier;
    }

    public enum Tier {
        TIER1(),
        TIER2(),
        TIER3;

        public static final int total = 3;
    }
}
