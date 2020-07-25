package me.wsman217.healthblocker;

import lombok.Getter;
import me.wsman217.healthblocker.alter.Pedestal;
import me.wsman217.healthblocker.armor.EvoListener;
import me.wsman217.healthblocker.commands.*;
import me.wsman217.healthblocker.gui.*;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.listeners.*;
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
    private static FileManager fileManager;
    @Getter
    private static final boolean IN_TESTING = true;


    @Override
    public void onEnable() {
        instance = this;
        fileManager = FileManager.getInstance().logInfo(true).setup(this);
        this.itemHandler = new CustomFoodHandler();
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
        pman.registerEvents(new EvoListener(), instance);
        //pman.registerEvents(new TestListener(), instance);
        pman.registerEvents(new Pedestal(), instance);
        pman.registerEvents(new PedestalListeners(), instance);
    }

    private void initCommands() {
        CommandHealth cHealth = new CommandHealth();
        this.getCommand("health").setExecutor(cHealth);
        this.getCommand("health").setTabCompleter(cHealth);
        CommandHealthFood cHealthFood = new CommandHealthFood();
        this.getCommand("healthfood").setExecutor(cHealthFood);
        this.getCommand("healthfood").setExecutor(cHealthFood);
        this.getCommand("euphoriaranks").setExecutor(new EuphoriaRanks());
        this.getCommand("evogive").setExecutor(new CommandEvoGive());
        this.getCommand("healthtest").setExecutor(new TestCommand());
    }
}
