package me.wsman217.healthblocker.items.craftedfoods.tiers.tier2;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.items.FoodInterface;
import me.wsman217.healthblocker.items.FoodUtils;
import me.wsman217.healthblocker.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LumpyBoneStew implements FoodInterface {

    private Material mat = Material.RABBIT_STEW;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "lumpy_bone_stew";
    private String permission = "healthblocker.food.tier2.lumpybonestew";
    private Permission perm;

    public LumpyBoneStew() {
        this.item = FoodUtils.getItemStack(mat, nameSpace, ChatColor.YELLOW + "" + ChatColor.BOLD + "Lumpy Bone Stew"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2 + " hearts.");

        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();

        HashMap<ItemStack, Integer> rabStew = new HashMap<>();
        rabStew.put(new ItemStack(Material.RABBIT_STEW), 1);
        inputs.add(rabStew);

        HashMap<ItemStack, Integer> glistMelon = new HashMap<>();
        glistMelon.put(new ItemStack(Material.GLISTERING_MELON_SLICE), 1);
        inputs.add(glistMelon);

        HashMap<ItemStack, Integer> boneMeal = new HashMap<>();
        boneMeal.put(new ItemStack(Material.BONE_MEAL), 1);
        inputs.add(boneMeal);

        HealthBlocker plugin = HealthBlocker.getInstance();
        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER2).createShapelessRecipe(new NamespacedKey(plugin, nameSpace), inputs, item);

        this.perm = new Permission(this.permission);
        perm.addParent(CustomItemHandler.tier2, true);
        plugin.getServer().getPluginManager().addPermission(perm);
    }

    @Override
    public Material getMat() {
        return mat;
    }

    @Override
    public String getName() {
        return Objects.requireNonNull(item.getItemMeta()).getDisplayName();
    }

    @Override
    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public ItemStack getItemStack() {
        return FoodUtils.getItemStack(mat, nameSpace, ChatColor.YELLOW + "" + ChatColor.BOLD + "Lumpy Bone Stew"
                , ChatColor.LIGHT_PURPLE + "Restores " + getHealthRegenned() / 2 + " hearts.");
    }

    @Override
    public boolean isSimilar(ItemStack toTest) {
        return item.isSimilar(toTest);
    }

    @Override
    public int getHealthRegenned() {
        return 6;
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
