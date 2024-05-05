package de.littleprogrammer.guiapi.components;

import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface Component {

    public Entity getEntity();
    public Display getDisplay();
    public UUID getUniqueId();
    public void show(Player player);
    public void hide(Player player);
    public void remove();
    public void spawn();

}
