package de.littleprogrammer.guiapi;

import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Component;
import de.littleprogrammer.guiapi.enums.ServerVersion;
import de.littleprogrammer.guiapi.utils.Calculations;
import de.littleprogrammer.guiapi.utils.TeleportInterpolator;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GUI {

    private final Player player;
    private final UUID uuid;
    private final Map<UUID, Component> components;

    public GUI(Player player, String title, int gridWidth, int gridHeight) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.components = new HashMap<>();

        Api.getInstance().getGuis().put(player.getUniqueId(), this);
    }

    public void updatePosition(Location playerLoc) {
        Location location = Calculations.calculateInventoryLoc(playerLoc);

        if (Api.getInstance().getVersion().equals(ServerVersion.PRE_1_20_2)) {
            for (Component component : components.values()) {
                TeleportInterpolator teleportInterpolator = new TeleportInterpolator(component.getEntity(), location, 5, 1);
                teleportInterpolator.startInterpolation();
            }
        } else {
            for (Component component : components.values()) {
                component.getDisplay().setTeleportDuration(5);
                component.getDisplay().teleport(location);
            }
        }
    }

    public Component getComponent(UUID uuid) {
        return components.get(uuid);
    }

    public GUI addButton(String text, String localizedName) {
        Button button = new Button(this, this.player, text, localizedName);
        return this;
    }

}
