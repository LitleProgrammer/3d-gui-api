package de.littleprogrammer.guiapi.listeners;

import de.littleprogrammer.guiapi.GuiApi;
import de.littleprogrammer.guiapi.guis.Gui;
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
            Gui gui = GuiApi.getInstance().getGuis().get(event.getPlayer().getUniqueId());

            if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                //Location is different
                gui.updatePosition(event.getPlayer().getEyeLocation());
            } else if (!Calculations.isInRange(event.getPlayer().getEyeLocation(), gui.getCenterLocation(), gui.getSpacing() + 15)) {
                gui.updatePosition(event.getPlayer().getEyeLocation());
            }

            Entity hoveredEntity = null;
            for (Entity entity : event.getPlayer().getNearbyEntities(8, 8, 8)) {
                if (entity instanceof Display && entity.getCustomName() != null) {
                    if (Calculations.isInRange(event.getPlayer().getEyeLocation(), entity.getLocation(), 7)) {
                        hoveredEntity = entity;
                        break;
                    }
                }
            }

            if (hoveredEntity != null) {
                if (gui.getComponent(UUID.fromString(hoveredEntity.getCustomName())) instanceof Button) {
                    Button button = (Button) gui.getComponent(UUID.fromString(hoveredEntity.getCustomName()));
                    button.getHoverAction().accept(new HoverButtonEvent(gui, event.getPlayer(), button, button.getHoverText(), button.getText()));
                    button.setHover(true);
                }
            } else {
                for (Component component : gui.getComponents()) {
                    if (component instanceof Button) {
                        Button button = (Button) component;
                        if (button.isHover()) {
                            button.getUnHoverAction().accept(new UnHoverButtonEvent(gui, event.getPlayer(), button, button.getHoverText(), button.getText()));
                            button.setHover(false);
                        }
                    }
                }
            }
        }
    }
}
