package de.littleprogrammer.guiapi.listeners;

import de.littleprogrammer.guiapi.GuiApi;
import de.littleprogrammer.guiapi.SimpleGui;
import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Component;
import de.littleprogrammer.guiapi.utils.Calculations;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class GuiEvents implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        final SimpleGui simpleGui = GuiApi.getInstance().getGUI(event.getPlayer());

        if (simpleGui == null) return;

        Entity awaitenEntity = null;
        for (Entity entity : event.getPlayer().getNearbyEntities(7, 7, 7)) {
            if (entity instanceof Display && entity.getCustomName() != null) {
                if (Calculations.playerLookingAtEntity(event.getPlayer(), entity)) {
                    awaitenEntity = entity;
                    break;
                }
            }
        }

        if (awaitenEntity == null) { return; }

        UUID uuid = UUID.fromString(awaitenEntity.getCustomName());
        Component component = simpleGui.getComponent(uuid);
        if (!(component instanceof Button)) return;

        Button button = (Button) component;
        //System.out.println("Click on button: " + button.getUniqueId());
        button.getClickAction().accept(new PlayerInteractEntityEvent(event.getPlayer(), button.getEntity()));
    }
}
