package de.littleprogrammer.guiapi.listeners;

import de.littleprogrammer.guiapi.GuiApi;
import de.littleprogrammer.guiapi.SimpleGui;
import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Component;
import de.littleprogrammer.guiapi.customeEvents.HoverButtonEvent;
import de.littleprogrammer.guiapi.customeEvents.UnHoverButtonEvent;
import de.littleprogrammer.guiapi.utils.Calculations;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class MoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (GuiApi.getInstance().getGuis().containsKey(event.getPlayer().getUniqueId())) {
            SimpleGui simpleGui = GuiApi.getInstance().getGuis().get(event.getPlayer().getUniqueId());

            if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                //Location is different
                simpleGui.updatePosition(event.getPlayer().getEyeLocation());
            } else if (!Calculations.isInRange(event.getPlayer().getEyeLocation(), simpleGui.getCenterLocation(), 40)) {
                simpleGui.updatePosition(event.getPlayer().getEyeLocation());
            }

            Entity hoveredEntity = null;
            for (Entity entity : event.getPlayer().getNearbyEntities(5, 5, 5)) {
                if (entity instanceof Display && entity.getCustomName() != null) {
                    if (Calculations.isInRange(event.getPlayer().getEyeLocation(), entity.getLocation(), 7)) {
                        hoveredEntity = entity;
                        break;
                    }
                }
            }

            if (hoveredEntity != null) {
                if (simpleGui.getComponent(UUID.fromString(hoveredEntity.getCustomName())) instanceof Button) {
                    Button button = (Button) simpleGui.getComponent(UUID.fromString(hoveredEntity.getCustomName()));
                    button.getHoverAction().accept(new HoverButtonEvent(simpleGui, event.getPlayer(), button, button.getHoverText(), button.getText()));
                }
            } else {
                for (Component component : simpleGui.getComponents()) {
                    if (component instanceof Button) {
                        Button button = (Button) component;
                        button.getUnHoverAction().accept(new UnHoverButtonEvent(simpleGui, event.getPlayer(), button, button.getHoverText(), button.getText()));
                    }
                }
            }
        }
    }
}
