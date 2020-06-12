package me.wsman217.healthblocker.armor;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.List;

public class EvoHelmet {

    private static final Material[] materialTiers = {Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.DIAMOND_HELMET};
    private static final ItemStack[] itemTiers = new ItemStack[4];
    private static final double[] damageAmounts = {350, 650, 50};

    static {
        //Leather evo helmet
        ItemStack leatherEvo = createEvoBase(materialTiers[0], 1);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) leatherEvo.getItemMeta();
        if (leatherArmorMeta != null) {
            leatherArmorMeta.setColor(Color.fromRGB(255, 0, 255));
            leatherEvo.setItemMeta(leatherArmorMeta);
        }
        itemTiers[0] = leatherEvo;

        //Chain evo helmet
        itemTiers[1] = createEvoBase(materialTiers[1], 2);

        //Iron evo helmet
        itemTiers[2] = createEvoBase(materialTiers[2], 3);

        //Diamond evo helmet
        itemTiers[3] = createEvoBase(materialTiers[3], 4);
    }

    private static ItemStack createEvoBase(Material mat, int tier) {
        if (tier < 1 || tier > 4)
            tier = 1;
        ItemStack baseEvo = new ItemStack(mat);
        NBTItem baseEvoNBT = new NBTItem(baseEvo);
        baseEvoNBT.setDouble("damage_taken", 0d);
        baseEvoNBT.setInteger("tier", tier);
        baseEvoNBT.setBoolean("is_evo", true);
        baseEvo = baseEvoNBT.getItem();

        ItemMeta baseEvoMeta = baseEvo.getItemMeta();
        if (baseEvoMeta == null)
            return baseEvo;

        if (tier < 4)
            baseEvoMeta.setLore(Arrays.asList(ChatColor.GRAY + "The evo helmet is one of the most", ChatColor.GRAY + "powerful helmets of EuphoriaMC",
                    ChatColor.GOLD + "0/" + damageAmounts[tier - 1] + " damage taken."));
        else
            baseEvoMeta.setLore(Arrays.asList(ChatColor.GRAY + "The evo helmet is one of the most", ChatColor.GRAY + "powerful helmets of EuphoriaMC"));
        baseEvoMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Tier " + tier + " Evo Helmet");
        baseEvo.setItemMeta(baseEvoMeta);
        return baseEvo;
    }

    public static ItemStack getEvoFromTier(int tier) {
        if (tier < 1 || tier > 4)
            return null;
        return itemTiers[tier - 1].clone();
    }

    public static int getDamageTaken(ItemStack item) {
        if (!EvoListener.isEvoShield(item))
            return 0;
        NBTItem nbtItem = new NBTItem(item);
        if (!nbtItem.hasKey("damage_taken"))
            return 0;
        return nbtItem.getInteger("damage_taken");
    }

    public static ItemStack increaseDamageTaken(ItemStack item, double increaseAmount) {
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