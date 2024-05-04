package de.littleprogrammer.guiapi.components;

import de.littleprogrammer.guiapi.Api;
import de.littleprogrammer.guiapi.enums.ServerVersion;
import de.littleprogrammer.guiapi.utils.Calculations;
import de.littleprogrammer.guiapi.utils.TeleportInterpolator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Transformation;

import java.util.UUID;

public class Button implements Listener {

    private String texture;
    private String localizedName;
    private UUID uuid;
    private TextDisplay textDisplay;
    private Location location;
    private Player player;

    public Button(Player player, String texture, String localizedName) {
        this.player = player;
        this.texture = texture;
        this.localizedName = localizedName;
        uuid = UUID.randomUUID();

        spawn();
    }

    private void spawn() {
        Api.getInstance().getButtons().put(player.getUniqueId(), this);

        textDisplay = (TextDisplay) player.getWorld().spawnEntity(Calculations.calculateInventoryLoc(player.getLocation()), EntityType.TEXT_DISPLAY);
        textDisplay.setText(texture);
        textDisplay.setGlowing(true);
        textDisplay.setBillboard(Display.Billboard.CENTER);
        textDisplay.setDisplayWidth(30);
        textDisplay.setDisplayHeight(30);
        Bukkit.getScheduler().runTaskLater(Api.getInstance(), () -> {

            }, 2);
    }

    public void updatePosition(Location playerLoc) {
//        textDisplay.setTeleportDuration(5);
//
        location = Calculations.calculateInventoryLoc(playerLoc);
//        textDisplay.teleport(location);
//        textDisplay.setInterpolationDuration(5);
//        textDisplay.setInterpolationDelay(-1);
//        Transformation transformation = textDisplay.getTransformation();
//        transformation.getTranslation().set(location.toVector().subtract(textDisplay.getLocation().toVector()).toVector3f());
//        textDisplay.setTransformation(transformation);
        if (Api.getVersion().equals(ServerVersion.PRE_1_20_2)) {
            TeleportInterpolator teleportInterpolator = new TeleportInterpolator(textDisplay, location, 5, 1);
            teleportInterpolator.startInterpolation();
        } else {
            textDisplay.setTeleportDuration(5);
            textDisplay.teleport(location);
        }
    }
}
