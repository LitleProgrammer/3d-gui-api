package de.littleprogrammer.guiapi.components;

import de.littleprogrammer.guiapi.GuiApi;
import de.littleprogrammer.guiapi.SimpleGui;
import org.bukkit.entity.*;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

import java.util.UUID;

public class Text implements Component {

    private String text;
    private UUID uuid;
    private float size = 2;
    private TextDisplay textDisplay;
    private SimpleGui simpleGui;

    public Text(String text) {
        this.text = text;
        uuid = UUID.randomUUID();
    }

    @Override
    public void spawn() {
        textDisplay = (TextDisplay) simpleGui.getCenterLocation().getWorld().spawnEntity(simpleGui.getCenterLocation(), EntityType.TEXT_DISPLAY);
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

    public SimpleGui getGui() {
        return simpleGui;
    }

    public float getSize() {
        return size;
    }

    public void setGui(SimpleGui simpleGui) {
        this.simpleGui = simpleGui;
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
}
