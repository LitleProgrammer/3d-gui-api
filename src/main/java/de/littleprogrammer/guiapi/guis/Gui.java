package de.littleprogrammer.guiapi.guis;

import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class Gui {
    public UUID uuid;
    public Player player;
    public Location centerLocation;
    public boolean open;
    public Map<UUID, Component> components;
    public Map<UUID, Button> buttons;
    public int spacing;

    public Gui(UUID uuid, boolean open) {
        this.uuid = uuid;
        this.open = open;

        this.components = new HashMap<>();
        this.buttons = new HashMap<>();
    }

    public abstract void open(Player player);
    public abstract void close();
    public abstract void updatePosition(Location location);

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCenterLocation(Location centerLocation) {
        this.centerLocation = centerLocation;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public Component getComponent(UUID uuid) {
        return components.get(uuid);
    }

    public Location getCenterLocation() {
        return centerLocation;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Component> getComponents() {
        return new ArrayList<>(components.values());
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public int getSpacing() {
        return spacing;
    }

    public int getButtonAmount() {
        return buttons.size();
    }
}
