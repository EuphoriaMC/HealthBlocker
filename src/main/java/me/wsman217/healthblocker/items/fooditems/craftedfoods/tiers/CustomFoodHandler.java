package me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers;

import lombok.Getter;
import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier1.*;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier2.*;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.tier3.*;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomFoodHandler {
    public static Permission tier1 = new Permission("healthblocker.food.tier1.*");
    public static Permission tier2 = new Permission("healthblocker.food.tier2.*");
    public static Permission tier3 = new Permission("healthblocker.food.tier3.*");

    public static ArrayList<NamespacedKey> keys = new ArrayList<>();
    public static HashMap<String, Recipe.Tier> recipeTiers = new HashMap<>();
    @Getter
    private static ArrayList<CustomFoodItem> customFoods = new ArrayList<>();

    public static CustomFoodItem appleJuice, glassOfMilk, grandmasCookies, restoredEye, spikedApple, tomatoSoup,
            cherryPie, lumpyBoneStew, mapleSyrup, tropicalStew, darkMagic, spaghetti, waffles;

    static {
        setUpPerms();
        appleJuice = setUpFactory(new AppleJuice());
        glassOfMilk = setUpFactory(new GlassOfMilk());
        grandmasCookies = setUpFactory(new GrandmasCookies());
        restoredEye = setUpFactory(new RestoredEye());
        spikedApple = setUpFactory(new SpikedApple());
        tomatoSoup = setUpFactory(new TomatoSoup());
        cherryPie = setUpFactory(new CherryPie());
        lumpyBoneStew = setUpFactory(new LumpyBoneStew());
        mapleSyrup = setUpFactory(new MapleSyrup());
        tropicalStew = setUpFactory(new TropicalStew());
        darkMagic = setUpFactory(new DarkMagic());
        spaghetti = setUpFactory(new Spaghetti());
        waffles = setUpFactory(new Waffles());
    }

    private static CustomFoodItem setUpFactory(CustomFoodItem value) {
        customFoods.add(value);
        return value;
    }

    public CustomFoodHandler() {
        setUpRecipeBook();
        recipeTiers = getRecipeTiers();
    }

    public static CustomFoodItem getFromNameSpace(String name) {
        for (CustomFoodItem cF : customFoods)
            if (cF.getNamespace().equals(name))
                return cF;
        return null;
    }

    public static CustomFoodItem getByItemStack(ItemStack toCompare) {
        for (CustomFoodItem cF : customFoods) {
            if (cF.getPermedItem().isSimilar(toCompare) || cF.getNonPermedItem().isSimilar(toCompare))
                return cF;
        }
        return null;
    }

    public static ArrayList<Permission> getPermissions() {
        ArrayList<Permission> perms = new ArrayList<>();
        for (CustomFoodItem cF : customFoods)
            perms.add(cF.getPerm());
        return perms;
    }

    private HashMap<String, Recipe.Tier> getRecipeTiers() {
        HashMap<String, Recipe.Tier> tiers = new HashMap<>();
        for (CustomFoodItem working : customFoods) {
            Recipe.Tier currentTier = working.getRecipe().getRecipeTier();
            tiers.put(working.getNamespace(), currentTier);
        }
        return tiers;
    }

    private static void setUpPerms() {
        tier1.setDefault(PermissionDefault.OP);
        tier2.setDefault(PermissionDefault.OP);
        tier3.setDefault(PermissionDefault.OP);
        HealthBlocker.getInstance().getServer().getPluginManager().addPermission(tier1);
        HealthBlocker.getInstance().getServer().getPluginManager().addPermission(tier2);
        HealthBlocker.getInstance().getServer().getPluginManager().addPermission(tier3);
    }

    private void setUpRecipeBook() {
        for (CustomFoodItem item : customFoods) {
            keys.add(new NamespacedKey(HealthBlocker.getInstance(), item.getNamespace()));
        }
    }
}
