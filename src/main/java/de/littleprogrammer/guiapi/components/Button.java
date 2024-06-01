package de.littleprogrammer.guiapi.components;

import de.littleprogrammer.guiapi.GuiApi;
import de.littleprogrammer.guiapi.guis.Gui;
import de.littleprogrammer.guiapi.guis.SimpleGui;
import de.littleprogrammer.guiapi.customeEvents.HoverButtonEvent;
import de.littleprogrammer.guiapi.customeEvents.UnHoverButtonEvent;
import de.littleprogrammer.guiapi.utils.Calculations;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.function.Consumer;

public class Button implements Component {

    private String texture;
    private String hoverTexture;
    private UUID uuid;
    private TextDisplay textDisplay;
    private Location location;
    private Consumer<PlayerInteractEntityEvent> clickAction;
    private Consumer<HoverButtonEvent> hoverAction;
    private Consumer<UnHoverButtonEvent> unHoverAction;
    private int slot;
    private float size = 2;
    private Gui gui;
    private boolean hover = false;

    public Button(String texture, String hoverTexture, int slot) {
        this.texture = texture;
        this.hoverTexture = hoverTexture;
        this.slot = slot;

        uuid = UUID.randomUUID();
    }

    public void spawn() {
        textDisplay = (TextDisplay) gui.getCenterLocation().getWorld().spawnEntity(Calculations.calculateComponentLocation(gui, this, gui.getButtonAmount(), 30), EntityType.TEXT_DISPLAY);
        textDisplay.setCustomName(uuid.toString());
        textDisplay.setCustomNameVisible(false);
        textDisplay.setText(texture);
        textDisplay.setGlowing(true);
        textDisplay.setBillboard(Display.Billboard.CENTER);
        textDisplay.setDisplayWidth(30);
        textDisplay.setDisplayHeight(30);
        textDisplay.setVisibleByDefault(false);
        textDisplay.setDefaultBackground(false);
        Transformation transformation = textDisplay.getTransformation();
        transformation.getScale().set(new Vector3f(size, size, size));
        textDisplay.setTransformation(transformation);
    }

    public void show(Player player) {
        player.showEntity(GuiApi.getInstance().getPlugin(), textDisplay);
    }

    public void hide(Player player) {
        player.hideEntity(GuiApi.getInstance().getPlugin(), textDisplay);
    }

    public void remove() {
       textDisplay.remove();
    }

    @Nonnull
    public Consumer<PlayerInteractEntityEvent> getClickAction() {
        if (clickAction != null) {
            return clickAction;
        } else {
            return (event -> {});
        }
    }

    @Nonnull
    public Button onClick(Consumer<PlayerInteractEntityEvent> clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    public Consumer<HoverButtonEvent> getHoverAction() {
        if (hoverAction != null) {
            return hoverAction;
        } else {
            return (event -> {});
        }
    }

    public Button onHover(Consumer<HoverButtonEvent> hoverAction) {
        this.hoverAction = hoverAction;
        return this;
    }

    public Consumer<UnHoverButtonEvent> getUnHoverAction() {
        if (unHoverAction != null) {
            return unHoverAction;
        } else {
            return (event -> {});
        }
    }

    public Button onUnHover(Consumer<UnHoverButtonEvent> unHoverAction) {
        this.unHoverAction = unHoverAction;
        return this;
    }

    public Entity getEntity() {
        return textDisplay;
    }

    public TextDisplay getDisplay() {
        return textDisplay;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public Gui getGui() {
        return gui;
    }

    public String getText() {
        return texture;
    }

    public String getHoverText() {
        return hoverTexture;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public Button setSize(float size) {
        this.size = size;
        return this;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isHover() {
        return hover;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void setHoverTexture(String hoverTexture) {
        this.hoverTexture = hoverTexture;
    }
}
