package me.wsman217.healthblocker.advancements.achievements;

import eu.endercentral.crazy_advancements.Advancement;
import eu.endercentral.crazy_advancements.AdvancementDisplay;
import eu.endercentral.crazy_advancements.NameKey;
import me.wsman217.healthblocker.advancements.AchievementManager;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import net.md_5.bungee.api.ChatColor;

public class LearningAdvancement {

    private final AdvancementDisplay ROOT_DISPLAY = new me.wsman217.healthblocker.advancements.AdvancementDisplay()
            .setIcon(CustomFoodHandler.spikedApple.getPermedItem())
            .setTitle(ChatColor.LIGHT_PURPLE + "Learn The Ropes")
            .setDescription(ChatColor.GRAY + "Learn how the server works!")
            .setAnnounceChat(false)
            .createDisplay();

    private final Advancement ROOT = new Advancement(null, new NameKey("health_blocker", "learn"), ROOT_DISPLAY);

    private final AdvancementDisplay EAT_CUSTOM_FOOD_DISPLAY = new me.wsman217.healthblocker.advancements.AdvancementDisplay()
            .setIcon(CustomFoodHandler.spikedApple.getPermedItem())
            .setTitle(ChatColor.LIGHT_PURPLE + "Eat a custom food")
            .setDescription(ChatColor.GRAY + "")
            .setAnnounceChat(false)
            .createDisplay(1, 1.5f);

    private final Advancement EAT_CUSTOM_FOOD = new Advancement(ROOT, new NameKey("health_blocker", "eat"), EAT_CUSTOM_FOOD_DISPLAY);

    public LearningAdvancement() {
        AchievementManager.getManger().addAdvancement(ROOT, EAT_CUSTOM_FOOD);
    }
}
