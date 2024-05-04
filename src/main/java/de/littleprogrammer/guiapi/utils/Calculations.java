package de.littleprogrammer.guiapi.utils;

import org.bukkit.Location;

public class Calculations {

    public static Location calculateInventoryLoc(Location midLocation) {
        double distance = 4; // 4 blocks away
        double yawRadians = Math.toRadians(midLocation.getYaw());
        double pitchRadians = Math.toRadians(midLocation.getPitch());
        double x = midLocation.getX() - distance * Math.sin(yawRadians);
        double y = midLocation.getY() - 1;
        double z = midLocation.getZ() + distance * Math.cos(yawRadians); // Negative here to match player rotation direction

        return new Location(midLocation.getWorld(), x, y, z, midLocation.getYaw(), 0);
    }

}
