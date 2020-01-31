package me.wsman217.healthblocker.recipeutils.types;

import me.wsman217.healthblocker.HealthBlocker;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class TypeFurnaceRecipe implements RecipeType {

    private NamespacedKey key;
    private ItemStack input, output;
    private float exp;
    private int cookTime;

    public TypeFurnaceRecipe(NamespacedKey key, ItemStack input, ItemStack output, float exp, int cookTime) {
        this.key = key;
        this.input = input;
        this.output = output;
        this.exp = exp;
        this.cookTime = cookTime;
        HealthBlocker.getInstance().getServer().addRecipe(new FurnaceRecipe(key, output, input.getType(), exp, cookTime));
    }

    public NamespacedKey getKey() {
        return key;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public float getExp() {
        return exp;
    }

    public int getCookTime() {
        return cookTime;
    }
}
