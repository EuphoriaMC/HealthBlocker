package me.wsman217.healthblocker;

import javafx.scene.paint.Stop;
import lombok.Getter;
import me.wsman217.healthblocker.commands.CommandHealth;
import me.wsman217.healthblocker.commands.CommandHealthFood;
import me.wsman217.healthblocker.customcrafting.multiblock.MultiblockItem;
import me.wsman217.healthblocker.customcrafting.multiblock.RemovalWand;
import me.wsman217.healthblocker.database.Database;
import me.wsman217.healthblocker.database.MultiblockHandler;
import me.wsman217.healthblocker.gui.*;
import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.listeners.CraftingListener;
import me.wsman217.healthblocker.listeners.EatingListener;
import me.wsman217.healthblocker.listeners.JoinListener;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;

public class HealthBlocker extends JavaPlugin {

    @Getter
    private static HealthBlocker instance;
    @Getter
    private CustomItemHandler itemHandler;
    @Getter
    private MultiblockItem mbItem;
    @Getter
    private RemovalWand rmWand;
    @Getter
    private Database db;
    @Getter
    private MultiblockHandler mbHandler;

    @Override
    public void onEnable() {
        instance = this;
        this.itemHandler = new CustomItemHandler();
        this.mbItem = new MultiblockItem().init();
        this.rmWand = new RemovalWand().init();
        this.db = new Database().openDatabaseConnection();
        mbHandler = new MultiblockHandler(db);
        mbHandler.generateTable();
        initListeners();
        initCommands();
    }

    @Override
    public void onDisable() {
        Iterator recipeIterator = this.getServer().recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe recipe = (Recipe) recipeIterator.next();
            recipe.getResult();
        }
        this.getServer().resetRecipes();
    }

    private void initListeners() {
        PluginManager pman = this.getServer().getPluginManager();
        pman.registerEvents(mbItem, instance);
        pman.registerEvents(rmWand, instance);
        pman.registerEvents(new EatingListener(), instance);
        pman.registerEvents(new CraftingListener(), instance);
        pman.registerEvents(new JoinListener(), instance);
        pman.registerEvents(new CategoryView(), instance);
        pman.registerEvents(new Tier1(), instance);
        pman.registerEvents(new Tier2(), instance);
        pman.registerEvents(new Tier3(), instance);
        pman.registerEvents(new StopCraftClicks(), instance);
    }

    private void initCommands() {
        CommandHealth cHealth = new CommandHealth();
        this.getCommand("health").setExecutor(cHealth);
        this.getCommand("health").setTabCompleter(cHealth);
        CommandHealthFood cHealthFood = new CommandHealthFood();
        this.getCommand("healthfood").setExecutor(cHealthFood);
        this.getCommand("healthfood").setExecutor(cHealthFood);
    }
}
