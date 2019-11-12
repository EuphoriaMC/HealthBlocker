package me.wsman217.healthblocker.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHealth implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can not be used by the console.");
            return true;
        }

        Player p = (Player) sender;

        //Make arguments for ?/help and give

        if (!(args.length > 0))
            return noArgs(p);

        if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help") ||
                !p.hasPermission("Healthblocker.admin"))
            return helpArg(p);
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length > 1)
                return giveArgs(p, true, args);
            else
                return giveArgs(p, false, null);
        }
        return true;
    }

    private boolean noArgs(Player p) {

        return true;
    }

    private boolean helpArg(Player p) {

        //Send the help command (Not import at this moment.)
        return true;
    }

    private boolean giveArgs(Player p, boolean hasFood, String[] args) {

        if (!hasFood) {
            p.sendMessage(ChatColor.DARK_PURPLE + "=-=-=-=-Custom Foods-=-=-=-=");
            p.sendMessage(ChatColor.GREEN + "Grandmas Cookies, Restored Eye, Spiked Apple, Tomato Soup");
            p.sendMessage(ChatColor.YELLOW + "Cherry Pie, Lumpy Bone Stew, Tropical Stew");
            p.sendMessage(ChatColor.LIGHT_PURPLE + "Dark Magic");
            return true;
        }

        StringBuilder sb = new StringBuilder();
        for (String str : args) {
           sb.append(str).append(" ");
        }
        String food = sb.toString();


        return true;
    }
}
