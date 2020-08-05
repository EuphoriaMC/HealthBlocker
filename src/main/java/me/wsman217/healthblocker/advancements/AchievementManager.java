package me.wsman217.healthblocker.advancements;

import eu.endercentral.crazy_advancements.CrazyAdvancements;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import lombok.Getter;
import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.advancements.achievements.LearningAdvancement;
import org.bukkit.entity.Player;

public class AchievementManager {

    @Getter
    private static final AdvancementManager manager = CrazyAdvancements.getNewAdvancementManager();

    @Getter
    private static final HealthBlocker plugin = HealthBlocker.getInstance();

    public static void init() {
        LearningAdvancement.addToManager();
    }

    public static void addPlayer(Player p) {
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> manager.addPlayer(p), 2);
    }
}
