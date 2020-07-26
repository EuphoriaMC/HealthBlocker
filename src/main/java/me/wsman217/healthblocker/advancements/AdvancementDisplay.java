package me.wsman217.healthblocker.advancements;

import eu.endercentral.crazy_advancements.AdvancementVisibility;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AdvancementDisplay {

    @Getter
    private ItemStack icon = new ItemStack(Material.STONE);

    @Getter
    private String title = "Title";

    @Getter
    private String description = "Description";

    @Getter
    private eu.endercentral.crazy_advancements.AdvancementDisplay.AdvancementFrame frame = eu.endercentral.crazy_advancements.AdvancementDisplay.AdvancementFrame.TASK;

    @Getter
    private boolean showToast = true;

    @Getter
    private boolean announceChat = true;

    @Getter
    private AdvancementVisibility visibility = AdvancementVisibility.VANILLA;

    @Getter
    private String background = "textures/block/stone.png";

    public eu.endercentral.crazy_advancements.AdvancementDisplay createDisplay() {
        return new eu.endercentral.crazy_advancements.AdvancementDisplay(icon, title, description, frame, background, showToast, announceChat, visibility);
    }

    public eu.endercentral.crazy_advancements.AdvancementDisplay createDisplay(float x, float y) {
        eu.endercentral.crazy_advancements.AdvancementDisplay display = createDisplay();
        display.setCoordinates(x, y);
        return display;
    }

    public AdvancementDisplay setIcon(ItemStack icon) {
        this.icon = icon;
        return this;
    }

    public AdvancementDisplay setTitle(String title) {
        this.title = title;
        return this;
    }

    public AdvancementDisplay setDescription(String description) {
        this.description = description;
        return this;
    }

    public AdvancementDisplay setFrame(eu.endercentral.crazy_advancements.AdvancementDisplay.AdvancementFrame frame) {
        this.frame = frame;
        return this;
    }

    public AdvancementDisplay setShowToast(boolean showToast) {
        this.showToast = showToast;
        return this;
    }

    public AdvancementDisplay setAnnounceChat(boolean announceChat) {
        this.announceChat = announceChat;
        return this;
    }

    public AdvancementDisplay setVisibility(AdvancementVisibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public AdvancementDisplay setBackground(String background) {
        this.background = background;
        return this;
    }
}
