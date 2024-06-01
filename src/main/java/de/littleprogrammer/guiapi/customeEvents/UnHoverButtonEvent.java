package de.littleprogrammer.guiapi.customeEvents;

import de.littleprogrammer.guiapi.guis.Gui;
import de.littleprogrammer.guiapi.guis.SimpleGui;
import de.littleprogrammer.guiapi.components.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UnHoverButtonEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Gui gui;
    private final Player player;
    private final Button button;
    private final String hoverText;
    private final String text;

    public UnHoverButtonEvent(Gui gui, Player player, Button button, String hoverText, String text) {
        this.gui = gui;
        this.player = player;
        this.button = button;
        this.hoverText = hoverText;
        this.text = text;

        button.getDisplay().setText(text);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Gui getGui() {
        return gui;
    }

    public Player getPlayer() {
        return player;
    }

    public Button getButton() {
        return button;
    }

    public String getHoverText() {
        return hoverText;
    }

    public String getText() {
        return text;
    }
}
