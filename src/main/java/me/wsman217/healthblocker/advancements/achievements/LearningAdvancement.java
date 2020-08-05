package me.wsman217.healthblocker.advancements.achievements;

import eu.endercentral.crazy_advancements.Advancement;
import eu.endercentral.crazy_advancements.AdvancementDisplay;
import eu.endercentral.crazy_advancements.AdvancementVisibility;
import eu.endercentral.crazy_advancements.NameKey;
import me.wsman217.healthblocker.advancements.AchievementManager;
import me.wsman217.healthblocker.items.fooditems.craftedfoods.tiers.CustomFoodHandler;
import me.wsman217.healthblocker.utils.Constants;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public enum LearningAdvancement {
    ROOT(CustomFoodHandler.spikedApple.getPermedItem(),
            ChatColor.LIGHT_PURPLE + "Learn the ropes",
            ChatColor.GRAY + "Learn how the server works!",
            AdvancementDisplay.AdvancementFrame.TASK,
            "textures/block/stone.png",
            false, false,
            AdvancementVisibility.VANILLA,
            "learn"),

    EAT_CUSTOM_FOOD(CustomFoodHandler.spikedApple.getPermedItem(),
            ChatColor.LIGHT_PURPLE + "Eat a custom food",
            ChatColor.GRAY + "",
            AdvancementDisplay.AdvancementFrame.TASK,
            false, false,
            1, 1.5f,
            AdvancementVisibility.VANILLA,
            ROOT.getAdvancement(),
            "eat");

    private final AdvancementDisplay display;
    private final Advancement parent;
    private final String key;

    LearningAdvancement(ItemStack icon,
                        String title,
                        String description,
                        AdvancementDisplay.AdvancementFrame frame,
                        String background,
                        boolean toast, boolean announce,
                        AdvancementVisibility visibility,
                        String key) {

        this.display = new AdvancementDisplay(icon, title, description, frame, background, toast, announce, visibility);
        this.parent = null;
        this.key = key;
    }

    LearningAdvancement(ItemStack icon,
                        String title,
                        String description,
                        AdvancementDisplay.AdvancementFrame frame,
                        boolean toast, boolean announce,
                        float x, float y,
                        AdvancementVisibility visibility,
                        Advancement parent,
                        String key) {

        this.display = new AdvancementDisplay(icon, title, description, frame, toast, announce, visibility);
        this.display.setCoordinates(x, y);
        this.parent = parent;
        this.key = key;
    }

    public Advancement getAdvancement() {
        return new Advancement(parent, new NameKey(Constants.NAMESPACE, key), display);
    }

    public static void addToManager() {
        ArrayList<Advancement> advancements = new ArrayList<>();
        for (LearningAdvancement advancement : LearningAdvancement.values())
            advancements.add(advancement.getAdvancement());

        AchievementManager.getManager().addAdvancement((Advancement[]) advancements.toArray());
    }
}
