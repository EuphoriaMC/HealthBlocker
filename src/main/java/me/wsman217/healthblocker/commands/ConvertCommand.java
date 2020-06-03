package me.wsman217.healthblocker.commands;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.FoodInterface;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.utils.Constants;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ConvertCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by a player!");
            return true;
        }

        Player p = (Player) sender;
        Inventory inv = p.getInventory();
        ItemStack[] contents = inv.getContents();

        int amountConverted = 0;
        for (int i = 0; i < contents.length; i ++) {
            ItemStack item = contents[i];
            if (item == null)
                continue;
            NBTItem nbtItem = new NBTItem(item);
            boolean isCustomFood = nbtItem.getBoolean("custom_food");
            if (!isCustomFood)
                continue;
            int amount = item.getAmount();
            FoodInterface oldCustomFood = me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.obsolete.CustomFoodHandler
                    .getByItemStack(item);
            if (oldCustomFood == null)
                continue;
            String nameSpace = oldCustomFood.getNamespace();
            if (nameSpace.equalsIgnoreCase("grandmas_cookie")) {
                nameSpace += "s";
            }
            CustomFoodItem customFoodItem = CustomFoodHandler.getFromNameSpace(nameSpace);
            if (customFoodItem == null)
                continue;

            boolean permed = nbtItem.getBoolean("need_perm");
            ItemStack newItem;
            if (permed)
                newItem = customFoodItem.getPermedItem();
            else
                newItem = customFoodItem.getNonPermedItem();
            newItem.setAmount(amount);
            contents[i] = newItem;
            amountConverted += amount;
        }
        inv.setContents(contents);
        p.sendMessage(Constants.prefix + ChatColor.DARK_PURPLE + " " + amountConverted + " custom foods have been converted.");
        return true;
    }
}