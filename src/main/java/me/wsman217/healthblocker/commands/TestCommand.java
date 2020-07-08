package me.wsman217.healthblocker.commands;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.alter.Wand;
import me.wsman217.healthblocker.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestCommand implements CommandExecutor {

    private static final boolean IS_IN_TESTING = HealthBlocker.isIN_TESTING();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!IS_IN_TESTING)
            return true;
        if (sender instanceof ConsoleCommandSender)
            return true;
        Player p = (Player) sender;

        if (!p.hasPermission("healthblocker.testcommand"))
            return true;
        System.out.println("Past permission");
        if (args.length < 3)
            return true;

        int red = Utils.parseInt(args[0]);
        int green = Utils.parseInt(args[1]);
        int blue = Utils.parseInt(args[2]);

        ItemStack wand = new Wand().getWand(red, green, blue);

        p.getInventory().addItem(wand);
        return true;
    }
}
