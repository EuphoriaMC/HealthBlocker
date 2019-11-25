package me.wsman217.healthblocker.commands;

import me.wsman217.healthblocker.items.CustomItemHandler;
import me.wsman217.healthblocker.items.FoodInterface;
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
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length >= 3) {
                //Take all the arguments after the first index to the last
                ArrayList<String> newArgs = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
                String target = newArgs.get(0);
                String foodName = newArgs.get(1);
                //If they didn't give an amount auto put 1
                String amount = newArgs.size() == 2 ? "1" : newArgs.get(2);
                return giveArg(p, true, target, foodName, amount);
            } else
                return giveArg(p, false, null, null, null);
        }
        return true;
    }

    private boolean helpArg(Player p) {

        //TODO Send the help command (Not import at this moment.)
        return true;
    }

    private boolean giveArg(Player p, boolean hasFood, String tar, String foodType, String amo) {

        if (!hasFood) {
            p.sendMessage(ChatColor.DARK_PURPLE + "=-=-=-=-Custom Foods-=-=-=-=");
            p.sendMessage(ChatColor.GREEN + "grandmas_cookie, restored_eye, spiked_apple, tomato_soup");
            p.sendMessage(ChatColor.YELLOW + "cherry_pie, lumpy_bone_stew, tropical_stew");
            p.sendMessage(ChatColor.LIGHT_PURPLE + "dark_magic");
            p.sendMessage(ChatColor.DARK_PURPLE + "Command usage; /health give target [custom food] [amount]");
            return true;
        }

        Player target = Bukkit.getPlayerExact(tar);
        if (target == null) {
            p.sendMessage(ChatColor.RED + "The player that you have provided is not online.");
            return true;
        }

        FoodInterface food = CustomItemHandler.getFromNameSpace(foodType);
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
        ItemStack customFood = food.getItemStack();
        //Save the original amount to change back the storage inside the rams amount
        int originalAmount = customFood.getAmount();
        customFood.setAmount(amount);
        target.getInventory().addItem(customFood);
        customFood.setAmount(originalAmount);
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
                for (FoodInterface fi : CustomItemHandler.getCustomFoods()) {
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
}
