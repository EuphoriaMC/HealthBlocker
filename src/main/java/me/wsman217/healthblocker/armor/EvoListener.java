package me.wsman217.healthblocker.armor;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

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
                        System.out.println("Case 0");
                        armor[i] = EvoHelmet.increaseDamageTaken(armorPiece, percentTaken);
                        break;
                    case 2:
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
}
