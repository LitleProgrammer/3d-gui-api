package de.littleprogrammer.guiapi.components;

import de.littleprogrammer.guiapi.Api;
import de.littleprogrammer.guiapi.GUI;
import de.littleprogrammer.guiapi.utils.Calculations;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.function.Consumer;

public class Button implements Component {

    private String texture;
    private String localizedName;
    private UUID uuid;
    private TextDisplay textDisplay;
    private Location location;
    private Player player;
    private Consumer<PlayerInteractEntityEvent> clickAction;
    private GUI gui;

    public Button(GUI gui, Player player, String texture, String localizedName) {
        this.player = player;
        this.texture = texture;
        this.localizedName = localizedName;
        this.gui = gui;
        uuid = UUID.randomUUID();

        spawn();
    }

    private void spawn() {
        textDisplay = (TextDisplay) player.getWorld().spawnEntity(Calculations.calculateInventoryLoc(player.getLocation()), EntityType.TEXT_DISPLAY);
        textDisplay.setText(texture);
        textDisplay.setGlowing(true);
        textDisplay.setBillboard(Display.Billboard.CENTER);
        textDisplay.setDisplayWidth(30);
        textDisplay.setDisplayHeight(30);
    }

    @Nonnull
    public Consumer<PlayerInteractEntityEvent> getClickAction() {
        return clickAction;
    }

    @Nonnull
    public Button onClick(Consumer<PlayerInteractEntityEvent> clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    public Entity getEntity() {
        return textDisplay;
    }

    public Display getDisplay() {
        return textDisplay;
    }
}
