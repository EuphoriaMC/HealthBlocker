package me.wsman217.healthblocker.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.wsman217.healthblocker.items.fooditems.CustomFoodItem;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.ArrayList;
import java.util.UUID;

public class EatingListener implements Listener {

    public static ArrayList<UUID> playersEatenFoodsWithOriginalEffects;

    @EventHandler
    public void onConsumeEvent(PlayerItemConsumeEvent e) {
        //Check if its a custom food
        NBTItem nbtItem = new NBTItem(e.getItem());
        boolean isCustomFood = nbtItem.getBoolean("custom_food");
        if (!isCustomFood)
            return;
        String customFoodType = nbtItem.getString("food_type");
        CustomFoodItem foodType = CustomFoodHandler.getFromNameSpace(customFoodType);
        Player p = e.getPlayer();

        if (foodType == null)
            return;

        //Check if they have permission to eat the food
        if (!p.hasPermission(foodType.getPermission()) && nbtItem.getBoolean("need_perm")) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You do not have permission to eat this item.");
            return;
        }
        /*p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);*/
        /*
        Get the min of either the players max health or the players current health plus what the food heals or else
        the player can end up with more hearts than they are allowed
        */
        double healthToRegen = Math.min(p.getHealth() + foodType.getHealthRegenned(), p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        p.setHealth(healthToRegen);

        if (!foodType.getEffectsWhenEaten().isEmpty())
            p.addPotionEffects(foodType.getEffectsWhenEaten());

        if (foodType.isRemovePotionEffectsGivenByThisFood())
            playersEatenFoodsWithOriginalEffects.add(p.getUniqueId());
    }

    @EventHandler
    public void onPotionEffectEvent(EntityPotionEffectEvent e) {
        if (e.getCause() != EntityPotionEffectEvent.Cause.FOOD)
            return;
        if (e.getEntityType() != EntityType.PLAYER)
            return;
        Player p = (Player) e.getEntity();
        if (!playersEatenFoodsWithOriginalEffects.contains(p.getUniqueId()))
            return;
        playersEatenFoodsWithOriginalEffects.remove(p.getUniqueId());
        p.removePotionEffect(e.getNewEffect().getType());
    }
}
