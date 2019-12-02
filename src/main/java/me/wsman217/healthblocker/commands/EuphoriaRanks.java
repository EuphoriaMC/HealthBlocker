package me.wsman217.healthblocker.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//Command layout (2 args needed):
//euphoriaranks %rank% %player%
public class EuphoriaRanks implements CommandExecutor {

    //Public and static so it can be used in a listener class.
    public static final String[] ranks = {"vip", "mvp", "elite", "master", "euphoric"};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "This command requires more arguments.");
            return true;
        }

        Player p = Bukkit.getPlayer(args[1]);
        if (p == null) {
            sender.sendMessage(ChatColor.RED + "The player inputted is currently not online.");
            return true;
        }

        String rank = "";
        for (String str : ranks) {
            if (!str.equalsIgnoreCase(args[0]))
                continue;
            rank = str;
            break;
        }

        switch (rank) {
            case "vip":
                p.sendMessage("Placeholder vip message");
                return true;
            case "mvp":
                p.sendMessage("Placeholder mvp message");
                return true;
            case "elite":
                p.sendMessage("Placeholder elite message");
                return true;
            case "master":
                p.sendMessage("Placeholder master message");
                return true;
            case "euphoric":
                p.sendMessage("Placeholder euphoric message");
                return true;
            default:
                sender.sendMessage(ChatColor.RED + "The rank entered is not a valid rank.");
                return true;
        }
    }
}
