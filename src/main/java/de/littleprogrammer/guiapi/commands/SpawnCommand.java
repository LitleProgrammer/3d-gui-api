package de.littleprogrammer.guiapi.commands;

import de.littleprogrammer.guiapi.SimpleGui;
import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Button button = new Button((Player) commandSender, "\uE001", "\uE002", "LcoalizedName", 1).onClick(event -> {
            event.getPlayer().sendMessage("You clicked a button1");
        });

        Button button2 = new Button((Player) commandSender, "\uE001", "\uE002", "LcoalizedName", 2).onClick(event -> {
            event.getPlayer().sendMessage("You clicked a button2");
        });

        Button button3 = new Button((Player) commandSender, "\uE001", "\uE002", "LcoalizedName", 3).onClick(event -> {
            event.getPlayer().sendMessage("You clicked a button3");
        });

        SimpleGui gui = new SimpleGui("Some title", 1, 1).addContent(new Text("Test")).addButton(button).addButton(button2).addButton(button3);
        gui.open((Player) commandSender);

        return false;
    }
}
