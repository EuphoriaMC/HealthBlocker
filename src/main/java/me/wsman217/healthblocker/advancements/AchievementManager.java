package me.wsman217.healthblocker.advancements;

import eu.endercentral.crazy_advancements.CrazyAdvancements;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import lombok.Getter;

public class AchievementManager {

    @Getter
    private static final AdvancementManager manger = CrazyAdvancements.getNewAdvancementManager();
}
