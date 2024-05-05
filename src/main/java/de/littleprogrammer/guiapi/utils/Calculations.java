package de.littleprogrammer.guiapi.utils;

import de.littleprogrammer.guiapi.SimpleGui;
import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Component;
import org.bukkit.Location;

public class Calculations {

    public static Location calculateInventoryCenter(Location midLocation) {
        double distance = 4; // 4 blocks away
        double yawRadians = Math.toRadians(midLocation.getYaw());
        double pitchRadians = Math.toRadians(midLocation.getPitch());
        double x = midLocation.getX() - distance * Math.sin(yawRadians);
        double y = midLocation.getY() - 1;
        double z = midLocation.getZ() + distance * Math.cos(yawRadians); // Negative here to match player rotation direction

        return new Location(midLocation.getWorld(), x, y, z, midLocation.getYaw(), 0);
    }

    public static Location calculateComponentLocation(SimpleGui simpleGui, Component component, int buttonAmount) {
        Location centerLoc = simpleGui.getCenterLocation();
        if (component instanceof Button) {
            Button button = (Button) component;

            Location[] locations = calculateTrianglePoints(simpleGui.getPlayer().getLocation(), centerLoc);
            //Is button in row
            switch (buttonAmount) {
                case 1:
                    return centerLoc.clone().subtract(0, 0.5, 0);
                case 2:
                    if (button.getSlot() == 1) {
                        return locations[0];
                    } else if (button.getSlot() == 2) {
                        return locations[1];
                    }
                    break;
                case 3:
                    if (button.getSlot() == 1) {
                        return locations[0];
                    } else if (button.getSlot() == 2) {
                        return centerLoc.clone().subtract(0, 0.5, 0);
                    } else if (button.getSlot() == 3) {
                        return locations[1];
                    }
                    break;
            }
        } else {
            //Is content
            return centerLoc.clone().add(0, 1, 0);
        }
        return null;
    }

    private static Location[] calculateTrianglePoints(Location midLocation, Location centerLocation) {
        // Calculate distance between mid and center locations
        double distance = midLocation.distance(centerLocation);

        // Calculate angle between mid and center locations
        double angle = Math.atan2(centerLocation.getZ() - midLocation.getZ(), centerLocation.getX() - midLocation.getX());

        // Calculate offset angles for left and right points
        double leftOffsetAngle = angle + Math.PI / 2; // 90 degrees counterclockwise
        double rightOffsetAngle = angle - Math.PI / 2; // 90 degrees clockwise

        // Calculate left and right points using offset angles and distance
        Location leftPoint = calculatePointOnCircle(centerLocation, leftOffsetAngle, 4);
        Location rightPoint = calculatePointOnCircle(centerLocation, rightOffsetAngle, 4);

        return new Location[]{leftPoint, rightPoint};
    }

    private static Location calculatePointOnCircle(Location centerLocation, double angle, double radius) {
        double x = centerLocation.getX() + radius * Math.cos(angle);
        double z = centerLocation.getZ() + radius * Math.sin(angle);
        return new Location(centerLocation.getWorld(), x, centerLocation.getY(), z, centerLocation.getYaw(), centerLocation.getPitch());
    }

}
