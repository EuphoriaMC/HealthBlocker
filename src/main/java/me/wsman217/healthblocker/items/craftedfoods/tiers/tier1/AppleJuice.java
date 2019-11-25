package me.wsman217.healthblocker.items.craftedfoods.tiers.tier1;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.items.FoodInterface;
import me.wsman217.healthblocker.recipeutils.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Most of the custom item classes are built off of a sort of template so all of the classes are very similar with minor changes.
 */
public class AppleJuice implements FoodInterface {

    private Material mat = Material.POTION;
    private ItemStack item;
    private Recipe recipe;
    private String nameSpace = "apple_juice";
    private String permission = "healthblocker.food.tier1.applejuice";

    public AppleJuice() {
        HealthBlocker plugin = HealthBlocker.getInstance();

        //Create the item and add the custom nvt
        this.item = new ItemStack(mat);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("custom_food", true);
        nbtItem.setString("food_type", nameSpace);
        this.item = nbtItem.getItem();

        //Change the items meta data
        PotionMeta pm = (PotionMeta) item.getItemMeta();
        pm.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Apple Juice");
        pm.addEnchant(Enchantment.LUCK, 1, false);
        pm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pm.setColor(Color.fromBGR(50, 0, 255));
        pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "Restores 2 hearts.");
        pm.setLore(lore);
        item.setItemMeta(pm);

        //Create the items recipe
        ArrayList<HashMap<ItemStack, Integer>> inputs = new ArrayList<>();
        HashMap<ItemStack, Integer> apple = new HashMap<>();
        apple.put(new ItemStack(Material.APPLE, 1), 1);
        inputs.add(apple);
        HashMap<ItemStack, Integer> glassBottle = new HashMap<>();
        glassBottle.put(new ItemStack(Material.GLASS_BOTTLE, 1), 1);
        inputs.add(glassBottle);

        recipe = new Recipe().setRecipeTier(Recipe.Tier.TIER1);
        recipe.createShapelessRecipe(new NamespacedKey(plugin, nameSpace), inputs, item);

        //Create the items permission
        Permission permission = new Permission(this.permission);
        permission.addParent(CustomItemHandler.tier1, true);
        plugin.getServer().getPluginManager().addPermission(permission);
    }

    @Override
    public Material getMat() {
        return mat;
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
        return item;
    }

    @Override
    public boolean isSimilar(ItemStack toTest) {
        return item.isSimilar(toTest);
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
    public String getPermission() {
        return permission;
    }
}
