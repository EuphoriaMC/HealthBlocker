package me.wsman217.healthblocker.items;

import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier1.*;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier2.CherryPie;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier2.LumpyBoneStew;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier2.TropicalStew;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier3.DarkMagic;
import me.wsman217.healthblocker.recipeutils.Recipe;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomItemHandler {

    //The separate permissions for the tiers
    public static Permission tier1 = new Permission("healthblocker.food.tier1.*");
    public static Permission tier2 = new Permission("healthblocker.food.tier2.*");
    public static Permission tier3 = new Permission("healthblocker.food.tier3.*");

    public static ArrayList<NamespacedKey> keys = new ArrayList<>();
    public static HashMap<String, Recipe.Tier> recipeTiers = new HashMap<>();

    @Getter
    private static ArrayList<FoodInterface> customFoods = new ArrayList<>();

    public CustomItemHandler() {
        //Add all of the items to the custom foods ArrayList
        //Shapeless
        customFoods.add(new LumpyBoneStew());
        customFoods.add(new SpikedApple());
        customFoods.add(new TropicalStew());
        customFoods.add(new CherryPie());
        customFoods.add(new TomatoSoup());
        customFoods.add(new AppleJuice());

        //Shaped
        customFoods.add(new RestoredEye());
        customFoods.add(new DarkMagic());
        customFoods.add(new GrandmasCookies());

        //For the time being there will be no furnace recipes.
        //Furnace
        //customItems.add(new FriedWitherSkull());

        //Run through the setup methods
        setUpPerms();
        setUpRecipeBook();
        recipeTiers = getRecipeTiers();
    }

    public static FoodInterface getFromNameSpace(String name) {
        for (FoodInterface cI : customFoods)
            if (cI.getNamespace().equals(name))
                return cI;
        return null;
    }

    public static FoodInterface getByItemStack(ItemStack toCompare) {
        for (FoodInterface cI : customFoods) {
            NBTItem nbtItem = new NBTItem(cI.getItemStack());
            nbtItem.setBoolean("need_perm", false);
            ItemStack cIWithoutPerm = nbtItem.getItem();
            if (cI.getItemStack().isSimilar(toCompare) || cIWithoutPerm.isSimilar(toCompare))
                return cI;
        }
        return null;
    }

    public static ArrayList<Permission> getPermissions() {
        ArrayList<Permission> perms = new ArrayList<>();
        for (FoodInterface cI : customFoods) {
            perms.add(cI.getPermission());
        }
        return perms;
    }

    private HashMap<String, Recipe.Tier> getRecipeTiers() {
        HashMap<String, Recipe.Tier> tiers = new HashMap<>();
        for (FoodInterface working : customFoods) {
            Recipe.Tier currentTier = working.getRecipe().getRecipeTier();

            if (currentTier == Recipe.Tier.TIER1)
                tiers.put(working.getNamespace(), Recipe.Tier.TIER1);
            else if (currentTier == Recipe.Tier.TIER2)
                tiers.put(working.getNamespace(), Recipe.Tier.TIER2);
            else if (currentTier == Recipe.Tier.TIER3)
                tiers.put(working.getNamespace(), Recipe.Tier.TIER3);
        }
        return tiers;
    }

    private void setUpPerms() {
        tier1.setDefault(PermissionDefault.OP);
        tier2.setDefault(PermissionDefault.OP);
        tier3.setDefault(PermissionDefault.OP);
        HealthBlocker.getInstance().getServer().getPluginManager().addPermission(tier1);
        HealthBlocker.getInstance().getServer().getPluginManager().addPermission(tier2);
        HealthBlocker.getInstance().getServer().getPluginManager().addPermission(tier3);
    }

    private void setUpRecipeBook() {
        for (FoodInterface item : customFoods) {
            keys.add(new NamespacedKey(HealthBlocker.getInstance(), item.getNamespace()));
        }
    }
}
