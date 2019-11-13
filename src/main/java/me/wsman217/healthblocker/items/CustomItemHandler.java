package me.wsman217.healthblocker.items;

import lombok.Getter;
import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier1.GrandmasCookies;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier1.RestoredEye;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier1.SpikedApple;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier1.TomatoSoup;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier2.CherryPie;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier2.LumpyBoneStew;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier2.TropicalStew;
import me.wsman217.healthblocker.items.craftedfoods.tiers.tier3.DarkMagic;
import org.bukkit.NamespacedKey;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;

public class CustomItemHandler {

    public static Permission tier1 = new Permission("healthblocker.food.tier1.*");
    public static Permission tier2 = new Permission("healthblocker.food.tier2.*");
    public static Permission tier3 = new Permission("healthblocker.food.tier3.*");

    public static ArrayList<NamespacedKey> keys = new ArrayList<>();

    @Getter
    private static ArrayList<FoodInterface> customFoods = new ArrayList<>();

    public CustomItemHandler() {
        //Shapeless
        customFoods.add(new LumpyBoneStew());
        customFoods.add(new SpikedApple());
        customFoods.add(new TropicalStew());
        customFoods.add(new CherryPie());
        customFoods.add(new TomatoSoup());

        //Shaped
        customFoods.add(new RestoredEye());
        customFoods.add(new DarkMagic());
        customFoods.add(new GrandmasCookies());

        //For the time being there will be no furnace recipes.
        //Furnace
        //customItems.add(new FriedWitherSkull());

        setUpPerms();
        setUpRecipeBook();
    }

    public static FoodInterface getFromNameSpace(String name) {
        for (FoodInterface cI : customFoods)
            if (cI.getNamespace().equals(name))
                return cI;
        return null;
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
