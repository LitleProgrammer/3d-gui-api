package de.littleprogrammer.guiapi.listeners;

import de.littleprogrammer.guiapi.GuiApi;
import de.littleprogrammer.guiapi.SimpleGui;
import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.UUID;

public class GuiEvents implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        final SimpleGui simpleGui = GuiApi.getInstance().getGUI(event.getPlayer());

        if (simpleGui == null) return;

        UUID uuid = event.getRightClicked().getUniqueId();
        Component component = simpleGui.getComponent(uuid);
        if (!(component instanceof Button)) return;

        ((Button) component).getClickAction().accept(event);
    }
}
