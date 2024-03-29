package me.wsman217.healthblocker.commands;

import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.utils.recipeutils.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CommandHealth implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Check if the console sent the command and if so stop it.
        if (!(sender instanceof Player)) {
            if (args[0].equalsIgnoreCase("give"))
                return giveMethod(sender, args);
            sender.sendMessage(ChatColor.RED + "This command can not be used by the console.");
            return true;
        }

        Player p = (Player) sender;

        //"Parse" the arguments
        if (!(args.length > 0))
            return helpArg(p);
        if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help") ||
                !p.hasPermission("Healthblocker.admin"))
            return helpArg(p);
        if (args[0].equalsIgnoreCase("give"))
            return giveMethod(p, args);
        return true;
    }

    @SuppressWarnings("SameReturnValue")
    private boolean helpArg(Player p) {
        p.sendMessage(ChatColor.LIGHT_PURPLE + "EuphoriaMC has a custom plugin that twists the way your health regenerates. " +
                "Instead of relying on your hunger bar, you must rely on natural regeneration effects (potions, beacons, etc.) " +
                "and our custom foods that will regenerate your health and your hunger. To view these foods, type the command " +
                ChatColor.GRAY + "/healthfood. " + ChatColor.RESET + "" + ChatColor.LIGHT_PURPLE + "This " +
                "will show you how to craft our custom foods and how many hearts they regenerate. You can " +
                "also view these foods on the " + ChatColor.BOLD + "" + ChatColor.GRAY + "/discord.");
        return true;
    }

    @SuppressWarnings("SameReturnValue")
    private boolean giveArg(CommandSender p, boolean hasFood, String tar, String foodType, String amo, String lean) {

        if (!hasFood) {
            StringBuilder tier1Builder = new StringBuilder(), tier2Builder = new StringBuilder(), tier3Builder = new StringBuilder();
            HashMap<String, Recipe.Tier> map = CustomFoodHandler.recipeTiers;
            for (String nameSpace : map.keySet()) {
                Recipe.Tier tier = map.get(nameSpace);
                if (tier == Recipe.Tier.TIER1)
                    tier1Builder.append(nameSpace).append(", ");
                else if (tier == Recipe.Tier.TIER2)
                    tier2Builder.append(nameSpace).append(", ");
                else if (tier == Recipe.Tier.TIER3)
                    tier3Builder.append(nameSpace).append(", ");
            }

            String tier1 = tier1Builder.toString();
            tier1 = tier1.substring(0, tier1.lastIndexOf(","));
            String tier2 = tier2Builder.toString();
            tier2 = tier2.substring(0, tier2.lastIndexOf(","));
            String tier3 = tier3Builder.toString();
            tier3 = tier3.substring(0, tier3.lastIndexOf(","));

            p.sendMessage(ChatColor.DARK_PURPLE + "=-=-=-=-Custom Foods-=-=-=-=");
            p.sendMessage(ChatColor.GREEN + tier1);
            p.sendMessage(ChatColor.YELLOW + tier2);
            p.sendMessage(ChatColor.LIGHT_PURPLE + tier3);
            p.sendMessage(ChatColor.DARK_PURPLE + "Command usage; /health give target [custom food] [amount] [need permission to eat]");
            return true;
        }

        Player target = Bukkit.getPlayerExact(tar);
        if (target == null) {
            p.sendMessage(ChatColor.RED + "The player that you have provided is not online.");
            return true;
        }

        CustomFoodItem food = CustomFoodHandler.getFromNameSpace(foodType);
        if (food == null) {
            p.sendMessage(ChatColor.RED + foodType + " is not a valid food type.");
            return true;
        }

        if (!(isNumeric(amo))) {
            p.sendMessage(ChatColor.RED + "The amount that you have entered is not a valid amount.");
            return true;
        }

        int amount = Integer.parseInt(amo);
        //Make sure the amount is within 1 and 64
        amount = Math.max(amount, 1);
        amount = Math.min(amount, 64);

        boolean needPerms = Boolean.parseBoolean(lean);
        ItemStack customFood = needPerms ? food.getPermedItem() : food.getNonPermedItem();
        if (customFood.getMaxStackSize() > amount) {
            customFood.setAmount(amount);
            target.getInventory().addItem(customFood);
        } else
            for (int i = 0; i < amount; i++)
                target.getInventory().addItem(customFood);
        p.sendMessage(ChatColor.LIGHT_PURPLE + target.getName() + " has received " + amount + " of " + food.getName());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player))
            return null;
        Player p = (Player) sender;
        ArrayList<String> tabs = new ArrayList<>();
        if (!p.hasPermission("HealthBlocker.admin"))
            return tabs;
        if (args.length <= 1)
            tabs = addAll("give", "help", "?");
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length == 2)
                tabs = addAll(Bukkit.getOnlinePlayers());
            if (args.length == 3)
                //Loop through all custom foods
                for (CustomFoodItem fi : CustomFoodHandler.getCustomFoods()) {
                    //Check if it starts with the characters that have already been entered
                    if (fi.getNamespace().startsWith(args[2]))
                        tabs.add(fi.getNamespace());
                }
            if (args.length == 4)
                tabs = addAll("1", "16", "32", "64");
        }
        return tabs;
    }

    private boolean isNumeric(String strNum) {
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private ArrayList<String> addAll(String... args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, args);
        return list;
    }

    private ArrayList<String> addAll(Collection<? extends Player> args) {
        ArrayList<String> list = new ArrayList<>();
        for (Player p : args) {
            list.add(p.getName());
        }
        return list;
    }

    private boolean giveMethod(CommandSender sender, String[] args) {
        if (args.length >= 4) {
            //Take all the arguments after the first index to the last
            ArrayList<String> newArgs = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
            String target = newArgs.get(0);
            String foodName = newArgs.get(1);
            //If they didn't give an amount auto put 1
            String amount = newArgs.size() == 2 ? "1" : newArgs.get(2);
            String lean = newArgs.get(3);
            return giveArg(sender, true, target, foodName, amount, lean);
        } else
            return giveArg(sender, false, null, null, null, "true");
    }
}
