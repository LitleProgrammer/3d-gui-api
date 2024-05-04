package de.littleprogrammer.guiapi.utils;

import de.littleprogrammer.guiapi.Api;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class TeleportInterpolator {

    private Entity entity;
    private Location targetLocation;
    private int steps;
    private int delay;

    public TeleportInterpolator(Entity entity, Location targetLocation, int steps, int delay) {
        this.entity = entity;
        this.targetLocation = targetLocation;
        this.steps = steps;
        this.delay = delay;
    }

    public void startInterpolation() {
        Location currentLocation = entity.getLocation();

        double dx = (targetLocation.getX() - currentLocation.getX()) / steps;
        double dy = (targetLocation.getY() - currentLocation.getY()) / steps;
        double dz = (targetLocation.getZ() - currentLocation.getZ()) / steps;

        for (int i = 1; i <= steps; i++) {
            double newX = currentLocation.getX() + dx * i;
            double newY = currentLocation.getY() + dy * i;
            double newZ = currentLocation.getZ() + dz * i;

            Location intermediateLocation = new Location(targetLocation.getWorld(), newX, newY, newZ);

            // Teleport the entity to the intermediate location after a delay
            teleportWithDelay(intermediateLocation, i * delay);
        }
    }

    private void teleportWithDelay(Location location, int delayTicks) {
        Api.getScheduler().runTaskLater(Api.getInstance().getPlugin(), () -> {
            entity.teleport(location);
        }, delayTicks);
    }

}
