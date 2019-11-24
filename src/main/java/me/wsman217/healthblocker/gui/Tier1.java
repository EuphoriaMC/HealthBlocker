package me.wsman217.healthblocker.gui;

import me.wsman217.healthblocker.gui.holders.Tier1Holder;
import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.items.FoodInterface;
import me.wsman217.healthblocker.recipeutils.Recipe;
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
        HashMap<String, Recipe.Tier> recipeTiers = CustomItemHandler.recipeTiers;
        Set<String> keys = recipeTiers.keySet();
        ArrayList<FoodInterface> foods = new ArrayList<>();

        for (String key : keys) {
            if (recipeTiers.get(key) == Recipe.Tier.TIER1)
                foods.add(CustomItemHandler.getFromNameSpace(key));
        }

        int foodAmount = foods.size();
        int rows = (foodAmount / 9) + 1;

        Inventory inv = Bukkit.createInventory(new Tier1Holder(), rows * 9, ChatColor.GREEN + "Tier 1 Foods");

        int index = 0;
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
        GUIUtils.TierListenerStuff(e);
    }
}
