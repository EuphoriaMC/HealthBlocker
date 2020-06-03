package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.obsolete.tier1;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.FoodInterface;
import me.wsman217.healthblocker.items.fooditems.FoodUtils;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;

/*
Most of the custom item classes are built off of a sort of template so all of the classes are very similar with minor changes.
 */
public class AppleJuice implements FoodInterface {

    private Material mat = Material.POTION;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "apple_juice";
    private String permission = "healthblocker.food.tier1.applejuice";
    private Permission perm;

    public AppleJuice() {
        HealthBlocker plugin = HealthBlocker.getInstance();

        //Create the item and add the custom nvt
        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.GREEN + "" + ChatColor.BOLD + "Apple Juice"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");
        PotionMeta pm = (PotionMeta) item.getItemMeta();
        pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        pm.setColor(Color.fromBGR(50, 0, 255));
        item.setItemMeta(pm);

        //Create the items recipe
        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();
        HashMap<ItemStack, Integer> apple = new HashMap<>();
        apple.put(new ItemStack(Material.APPLE, 1), 1);
        inputs.add(apple);
        HashMap<ItemStack, Integer> glassBottle = new HashMap<>();
        glassBottle.put(new ItemStack(Material.GLASS_BOTTLE, 1), 1);
        inputs.add(glassBottle);

        /*recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1);
        recipe.createShapelessRecipe(new NamespacedKey(plugin, nameSpace), inputs, item);

        //Create the items permission
        perm = new Permission(this.permission);
        perm.addParent(CustomFoodHandler.tier1, true);
        plugin.getServer().getPluginManager().addPermission(perm);*/
    }

    @Override
    public String getName() {
        return item.getItemMeta().getDisplayName();
    }

    @Override
    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public ItemStack getItemStack() {
        ItemStack item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.GREEN + "" + ChatColor.BOLD + "Apple Juice"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2d + " hearts.");
        PotionMeta pm = (PotionMeta) item.getItemMeta();
        pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        pm.setColor(Color.fromBGR(50, 0, 255));
        item.setItemMeta(pm);
        return item;
    }

    @Override
    public int getHealthRegenned() {
        return 4;
    }

    @Override
    public String getNamespace() {
        return nameSpace;
    }

    @Override
    public Permission getPermission() {
        return perm;
    }
}
