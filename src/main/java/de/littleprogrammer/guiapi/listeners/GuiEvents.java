package de.littleprogrammer.guiapi.listeners;

import de.littleprogrammer.guiapi.Api;
import de.littleprogrammer.guiapi.GUI;
import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.UUID;

public class GuiEvents implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        final GUI gui = Api.getInstance().getGUI(event.getPlayer());

        if (gui == null) return;

        UUID uuid = event.getRightClicked().getUniqueId();
        Component component = gui.getComponent(uuid);
        if (!(component instanceof Button)) return;

        ((Button) component).getClickAction().accept(event);
    }
}
