package me.wsman217.healthblocker.armor;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class EvoListener implements Listener {

    @EventHandler
    public void onPlayerTakeDamageEvent(EntityDamageEvent e) {
        if (e.getEntityType() != EntityType.PLAYER)
            return;
        Player p = (Player) e.getEntity();
        ItemStack[] armor = p.getInventory().getArmorContents();
        double damageTaken = e.getFinalDamage();
        double amountOfEvoPieces = Arrays.stream(armor).filter(EvoListener::isEvoShield).filter(EvoListener::isNotLastTier).count();
        double percentTaken = (damageTaken * .8) / amountOfEvoPieces;

        if (amountOfEvoPieces > 0)
            for (int i = 3; i >= 0; i--) {
                ItemStack armorPiece = armor[i];
                if (!isEvoShield(armorPiece) || !isNotLastTier(armorPiece))
                    continue;
                switch (i) {
                    case 3:
                        armor[i] = increaseDamageTaken(armorPiece, percentTaken, EvoHelmet.getDamageAmounts(), EvoHelmet.getItemTiers());
                        break;
                    case 2:
                        armor[i] = increaseDamageTaken(armorPiece, percentTaken, EvoChestplate.getDamageAmounts(), EvoChestplate.getItemTiers());
                        break;
                    case 1:
                        increaseDamageTaken(armorPiece, percentTaken, EvoLeggings.getDamageAmounts(), EvoLeggings.getItemTiers());
                        break;
                    case 0:
                        increaseDamageTaken(armorPiece, percentTaken, EvoBoots.getDamageAmounts(), EvoBoots.getItemTiers());
                        break;
                }
            }

        p.getInventory().setArmorContents(armor);
        p.updateInventory();
    }

    public static boolean isEvoShield(ItemStack toTest) {
        if (toTest == null)
            return false;
        NBTItem item = new NBTItem(toTest);
        if (!item.hasKey("is_evo"))
            return false;
        return item.getBoolean("is_evo");
    }

    public static int getTier(ItemStack toTest) {
        NBTItem item = new NBTItem(toTest);
        if (!item.hasKey("tier"))
            return -1;
        return item.getInteger("tier");
    }

    public static boolean isNotLastTier(ItemStack toTest) {
        return getTier(toTest) < 4;
    }

    public static ItemStack evoBase(Material mat, int tier) {
        if (tier < 1 || tier > 4)
            tier = 1;
        ItemStack baseEvo = new ItemStack(mat);
        NBTItem baseEvoNBT = new NBTItem(baseEvo);
        baseEvoNBT.setDouble("damage_taken", 0d);
        baseEvoNBT.setInteger("tier", tier);
        baseEvoNBT.setBoolean("is_evo", true);
        baseEvo = baseEvoNBT.getItem();
        return baseEvo;
    }

    public static int getDamageTaken(ItemStack item) {
        if (!EvoListener.isEvoShield(item))
            return 0;
        NBTItem nbtItem = new NBTItem(item);
        if (!nbtItem.hasKey("damage_taken"))
            return 0;
        return nbtItem.getInteger("damage_taken");
    }

    public static ItemStack increaseDamageTaken(ItemStack item, double increaseAmount, double[] damageAmounts, ItemStack[] itemTiers) {
        if (!EvoListener.isEvoShield(item))
            return item;
        NBTItem nbtItem = new NBTItem(item);
        if (!nbtItem.hasKey("damage_taken"))
            return item;
        double newDamageTaken = getDamageTaken(item) + increaseAmount;
        nbtItem.setDouble("damage_taken", newDamageTaken);
        int tier = nbtItem.getInteger("tier");
        item = nbtItem.getItem();
        if (newDamageTaken >= damageAmounts[tier - 1])
            return itemTiers[tier].clone();

        ItemMeta im = item.getItemMeta();
        if (im == null)
            return item;
        List<String> lore = im.getLore();
        if (lore == null)
            return item;
        String loreLine2 = lore.get(2);
        int lastPartOfString = loreLine2.indexOf('d') - 1;
        loreLine2 = loreLine2.substring(lastPartOfString);
        loreLine2 = ChatColor.GOLD + "" + newDamageTaken + "/" + damageAmounts[tier - 1] + " " + loreLine2;
        System.out.println(loreLine2);
        lore.set(2, loreLine2);
        im.setLore(lore);
        item.setItemMeta(im);
        return item;
    }
}
