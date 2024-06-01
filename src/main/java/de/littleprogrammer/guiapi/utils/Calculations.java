package de.littleprogrammer.guiapi.utils;

import de.littleprogrammer.guiapi.components.Text;
import de.littleprogrammer.guiapi.guis.Gui;
import de.littleprogrammer.guiapi.guis.SimpleGui;
import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Component;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

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

    public static Location calculateComponentLocation(Gui gui, Component component, int buttonAmount, int spacing) {
        Location centerLoc = gui.getCenterLocation();
        if (component instanceof Button) {
            Button button = (Button) component;

            Location[] locations = calculateTrianglePoints(gui.getPlayer().getLocation(), centerLoc, spacing);
            //Is button in row
            switch (buttonAmount) {
                case 1:
                    return centerLoc.clone().subtract(0, 0, 0);
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
                        return centerLoc.clone().subtract(0, 0, 0);
                    } else if (button.getSlot() == 3) {
                        return locations[1];
                    }
                    break;
            }
        } else if (component instanceof Text) {
            Text text = (Text) component;

            Location[] locations = calculateTrianglePoints(gui.getPlayer().getLocation(), centerLoc, spacing);
            //Is button in row
            switch (buttonAmount) {
                case 1:
                    return centerLoc.clone().add(0, 1.5, 0);
                case 2:
                    if (text.getSlot() == 1) {
                        return locations[0].clone().add(0, 1.5, 0);
                    } else if (text.getSlot() == 2) {
                        return locations[1].add(0, 1.5, 0);
                    }
                    break;
                case 3:
                    if (text.getSlot() == 1) {
                        return locations[0].add(0, 1.5, 0);
                    } else if (text.getSlot() == 2) {
                        return centerLoc.clone().add(0, 1.5, 0);
                    } else if (text.getSlot() == 3) {
                        return locations[1].add(0, 1.5, 0);
                    }
                    break;
            }
        } else {
            //Is content
            return centerLoc.clone().add(0, 1.5, 0);
        }
        return null;
    }


    /**
     * @param playerLocation the location of the player (the point in the middle)
     * @param centerLocation the location on the circle to get the correct height
     */
    private static Location[] calculateTrianglePoints(Location playerLocation, Location centerLocation, int spacing) {
        double radius = playerLocation.distance(centerLocation);

        Vector vector1 = playerLocation.getDirection().setY(0).normalize().multiply(radius).rotateAroundY(Math.toRadians(spacing));
        Vector vector2 = playerLocation.getDirection().setY(0).normalize().multiply(radius).rotateAroundY(Math.toRadians(spacing * -1));

        Location loc1 = playerLocation.clone().add(vector1);
        loc1.setY(centerLocation.getY());

        Location loc2 = playerLocation.clone().add(vector2);
        loc2.setY(centerLocation.getY());

        return new Location[]{loc1, loc2};
    }

    public static boolean playerLookingAtEntity(Player player, Entity entity) {
        Vector playerDirection = player.getLocation().getDirection().normalize();

        Location entityLocation = entity.getLocation();
        Location playerEyeLocation = player.getEyeLocation();

        Vector playerToEntity = entityLocation.toVector().subtract(playerEyeLocation.toVector()).normalize();
        double dotProduct = playerDirection.dot(playerToEntity);

        //System.out.println("Checking entity" + entity + " " + entity.getCustomName() + " dot: " + dotProduct);

        return dotProduct > 0.97;
    }

    public static boolean isInRange(Location playerEyeLocation, Location centerLocation, double rangeInDegrees) {
        Vector playerEyeVector = playerEyeLocation.getDirection().setY(0).normalize();
        Vector playerToCenter = centerLocation.toVector().subtract(playerEyeLocation.toVector()).setY(0).normalize();

        double dotProduct = playerToCenter.dot(playerEyeVector);
        double angle = Math.toDegrees(Math.acos(dotProduct));

        return angle <= rangeInDegrees;
    }
}
