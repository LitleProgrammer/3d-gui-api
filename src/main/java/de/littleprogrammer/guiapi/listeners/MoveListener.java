package de.littleprogrammer.guiapi.listeners;

import de.littleprogrammer.guiapi.Api;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (Api.getInstance().getButtons().containsKey(event.getPlayer().getUniqueId())) {
            Api.getInstance().getButtons().get(event.getPlayer().getUniqueId()).updatePosition(event.getPlayer().getEyeLocation());
        }
    }
}
