package me.wsman217.healthblocker.gui;

import me.wsman217.healthblocker.gui.holders.Tier1Holder;
import me.wsman217.healthblocker.items.fooditems.CustomFoodHandler;
import me.wsman217.healthblocker.items.fooditems.FoodInterface;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Tier1 implements Listener {

    private Player p;

    public Tier1() {

    }

    Tier1(Player p) {
        this.p = p;
    }

    void openInv() {
        HashMap<String, Recipe.Tier> recipeTiers = CustomFoodHandler.recipeTiers;
        Set<String> keys = recipeTiers.keySet();
        ArrayList<FoodInterface> foods = new ArrayList<>();

        //Loop through all the keys of the recipeTiers hashmap
        for (String key : keys) {
            //If the are tier 1 add them to the foods ArrayList
            if (recipeTiers.get(key) == Recipe.Tier.TIER1)
                foods.add(CustomFoodHandler.getFromNameSpace(key));
        }

        //Find how many rows are needed for the inventory
        int foodAmount = foods.size();
        int rows = (foodAmount / 9) + 1;

        Inventory inv = Bukkit.createInventory(new Tier1Holder(), rows * 9, ChatColor.GREEN + "Tier 1 Foods");

        int index = 0;
        //Loop through all the foods and add them to the inv then open it
        for (FoodInterface food : foods) {
            inv.setItem(index, food.getItemStack());
            index++;
        }
        this.p.openInventory(inv);
    }

    @EventHandler
    public void onTier1Click(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof Tier1Holder))
            return;
        ((Tier1Holder) e.getInventory().getHolder()).naturallyClosed = false;
        GUIUtils.TierListenerStuff(e, 1);
    }
}
