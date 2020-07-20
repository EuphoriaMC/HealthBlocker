package me.wsman217.healthblocker.commands;

import me.wsman217.healthblocker.armor.EvoBoots;
import me.wsman217.healthblocker.armor.EvoChestplate;
import me.wsman217.healthblocker.armor.EvoHelmet;
import me.wsman217.healthblocker.armor.EvoLeggings;
import me.wsman217.healthblocker.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@SuppressWarnings("NullableProblems")
public class CommandEvoGive implements TabExecutor {

    private final String[] armorTypes = {"helmet", "chestplate", "leggings", "boots"};
    private final int[] tiers = {1, 2, 3, 4};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("Healthblocker.admin"))
            return true;
        if (args.length < 2)
            return true;
        if (!Utils.isInt(args[1]))
            return true;
        int tier = Integer.parseInt(args[1]);
        //TODO make sure to add a way to give this to other players. Right now just getting this in so I can test my code.
        ItemStack evoArmor = new ItemStack(Material.AIR);
        switch (args[0].toLowerCase()) {
            case "helmet":
                evoArmor = EvoHelmet.getEvoFromTier(tier);
                break;
            case "chestplate":
                evoArmor = EvoChestplate.getEvoFromTier(tier);
                break;
            case "leggings":
                evoArmor = EvoLeggings.getEvoFromTier(tier);
                break;
            case "boots":
                evoArmor = EvoBoots.getEvoFromTier(tier);
                break;
        }
        p.getInventory().addItem(evoArmor);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
