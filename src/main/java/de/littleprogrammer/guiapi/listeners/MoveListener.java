package de.littleprogrammer.guiapi.listeners;

import de.littleprogrammer.guiapi.GuiApi;
import de.littleprogrammer.guiapi.SimpleGui;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (GuiApi.getInstance().getGuis().containsKey(event.getPlayer().getUniqueId())) {
            SimpleGui simpleGui = GuiApi.getInstance().getGuis().get(event.getPlayer().getUniqueId());
            simpleGui.updatePosition(event.getPlayer().getEyeLocation());
        }
    }
}
