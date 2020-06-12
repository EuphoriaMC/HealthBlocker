package me.wsman217.healthblocker.armor;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;

public class EvoChestplate {

    private static final Material[] materialTiers = {Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE,
            Material.IRON_CHESTPLATE, Material.DIAMOND_CHESTPLATE};
    @Getter
    private static final ItemStack[] itemTiers = new ItemStack[4];
    @Getter
    private static final double[] damageAmounts = {350, 650, 50};

    static {
        //Leather evo Chestplate
        ItemStack leatherEvo = createEvoBase(materialTiers[0], 1);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) leatherEvo.getItemMeta();
        if (leatherArmorMeta != null) {
            leatherArmorMeta.setColor(Color.fromRGB(255, 0, 255));
            leatherEvo.setItemMeta(leatherArmorMeta);
        }
        itemTiers[0] = leatherEvo;

        //Chain evo Chestplate
        itemTiers[1] = createEvoBase(materialTiers[1], 2);

        //Iron evo Chestplate
        itemTiers[2] = createEvoBase(materialTiers[2], 3);

        //Diamond evo Chestplate
        itemTiers[3] = createEvoBase(materialTiers[3], 4);
    }

    private static ItemStack createEvoBase(Material mat, int tier) {
        ItemStack baseEvo = EvoListener.evoBase(mat, tier);

        ItemMeta baseEvoMeta = baseEvo.getItemMeta();
        if (baseEvoMeta == null)
            return baseEvo;

        if (tier < 4)
            baseEvoMeta.setLore(Arrays.asList(ChatColor.GRAY + "The evo Chestplate is one of the most", ChatColor.GRAY + "powerful Chestplates of EuphoriaMC",
                    ChatColor.GOLD + "0/" + damageAmounts[tier - 1] + " damage taken."));
        else
            baseEvoMeta.setLore(Arrays.asList(ChatColor.GRAY + "The evo Chestplate is one of the most", ChatColor.GRAY + "powerful Chestplates of EuphoriaMC"));
        baseEvoMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Tier " + tier + " Evo Chestplate");
        baseEvo.setItemMeta(baseEvoMeta);
        return baseEvo;
    }

    public static ItemStack getEvoFromTier(int tier) {
        if (tier < 1 || tier > 4)
            return null;
        return itemTiers[tier - 1].clone();
    }
}