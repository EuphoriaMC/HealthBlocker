package me.wsman217.healthblocker;

import lombok.Getter;
import me.wsman217.healthblocker.alters.AlterHandler;
import me.wsman217.healthblocker.commands.CommandHealth;
import me.wsman217.healthblocker.commands.CommandHealthFood;
import me.wsman217.healthblocker.commands.EuphoriaRanks;
import me.wsman217.healthblocker.listeners.*;
import me.wsman217.healthblocker.multiblock.craftingalter.CraftingAlter;
import me.wsman217.healthblocker.multiblock.RemovalWand;
import me.wsman217.healthblocker.database.Database;
import me.wsman217.healthblocker.database.MultiblockHandler;
import me.wsman217.healthblocker.gui.*;
import me.wsman217.healthblocker.items.fooditems.CustomFoodHandler;
import me.wsman217.healthblocker.utils.FileManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;

public class HealthBlocker extends JavaPlugin {

    @Getter
    private static HealthBlocker instance;
    @Getter
    private CustomFoodHandler itemHandler;
    @Getter
    private CraftingAlter mbItem;
    @Getter
    private RemovalWand rmWand;
    @Getter
    private Database db;
    @Getter
    private MultiblockHandler mbHandler;
    @Getter
    private static FileManager fileManager;

    @Override
    public void onEnable() {
        instance = this;
        fileManager = FileManager.getInstance().logInfo(true).setup(this);
        this.itemHandler = new CustomFoodHandler();
        this.mbItem = new CraftingAlter().init();
        this.rmWand = new RemovalWand().init();
        this.db = new Database().openDatabaseConnection();
        mbHandler = new MultiblockHandler(db);
        mbHandler.generateTable();
        initCommands();
        initListeners();
    }

    @Override
    public void onDisable() {
        this.getServer().getPluginManager().removePermission(CustomFoodHandler.tier1);
        this.getServer().getPluginManager().removePermission(CustomFoodHandler.tier2);
        this.getServer().getPluginManager().removePermission(CustomFoodHandler.tier3);
        for (Permission perm : CustomFoodHandler.getPermissions())
            this.getServer().getPluginManager().removePermission(perm);

        Iterator<Recipe> recipeIterator = this.getServer().recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            ItemStack result = recipe.getResult();

            if (CustomFoodHandler.getByItemStack(result) != null)
                recipeIterator.remove();
        }
        this.getServer().clearRecipes();
        while (recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            this.getServer().addRecipe(recipe);
        }
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
        pman.registerEvents(new GUIUtils(), instance);
        pman.registerEvents(new ClickListeners(), instance);
        pman.registerEvents(new BlockPlaceListener(), instance);
    }

    private void initCommands() {
        CommandHealth cHealth = new CommandHealth();
        this.getCommand("health").setExecutor(cHealth);
        this.getCommand("health").setTabCompleter(cHealth);
        CommandHealthFood cHealthFood = new CommandHealthFood();
        this.getCommand("healthfood").setExecutor(cHealthFood);
        this.getCommand("healthfood").setExecutor(cHealthFood);
        this.getCommand("euphoriaranks").setExecutor(new EuphoriaRanks());
    }
}
