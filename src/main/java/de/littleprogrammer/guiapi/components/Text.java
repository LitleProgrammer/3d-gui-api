package de.littleprogrammer.guiapi.components;

import de.littleprogrammer.guiapi.GuiApi;
import de.littleprogrammer.guiapi.guis.Gui;
import de.littleprogrammer.guiapi.guis.SimpleGui;
import org.bukkit.entity.*;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

import java.util.UUID;

public class Text implements Component {

    private String text;
    private UUID uuid;
    private float size = 2;
    private TextDisplay textDisplay;
    private Gui gui;
    private int slot;

    public Text(String text) {
        this.text = text;
        uuid = UUID.randomUUID();
    }

    public Text(String text, int slot) {
        this.text = text;
        this.slot = slot < 0 || slot > 3 ? 1 : slot;
        uuid = UUID.randomUUID();
    }

    @Override
    public void spawn() {
        textDisplay = (TextDisplay) gui.getCenterLocation().getWorld().spawnEntity(gui.getCenterLocation(), EntityType.TEXT_DISPLAY);
        textDisplay.setCustomName(uuid.toString());
        textDisplay.setCustomNameVisible(false);
        textDisplay.setText(text);
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

    @Override
    public Entity getEntity() {
        return textDisplay;
    }

    @Override
    public Display getDisplay() {
        return textDisplay;
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    public Gui getGui() {
        return gui;
    }

    public float getSize() {
        return size;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public Text setSize(float size) {
        this.size = size;
        return this;
    }

    @Override
    public void show(Player player) {
        player.showEntity(GuiApi.getInstance().getPlugin(), textDisplay);
    }

    @Override
    public void hide(Player player) {
        player.hideEntity(GuiApi.getInstance().getPlugin(), textDisplay);
    }

    @Override
    public void remove() {
        textDisplay.remove();
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
