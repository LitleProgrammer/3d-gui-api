package de.littleprogrammer.guiapi.listeners;

import de.littleprogrammer.guiapi.Api;
import de.littleprogrammer.guiapi.GUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (Api.getInstance().getGuis().containsKey(event.getPlayer().getUniqueId())) {
            GUI gui = Api.getInstance().getGuis().get(event.getPlayer().getUniqueId());
            gui.updatePosition(event.getPlayer().getEyeLocation());
        }
    }
}
