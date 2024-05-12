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
        Button button = new Button("\uE001", "\uE002", 1)
                .setSize(2)
                .onClick(event -> {
                    event.getPlayer().sendMessage("You clicked a button1");
                })
                .onHover(event -> {
                    event.getPlayer().sendMessage("You hovered over a button");
                })
                .onUnHover(event -> {
                    event.getPlayer().sendMessage("You unhovered a button");
                });

        Button button2 = new Button("\uE001", "\uE002", 2).onClick(event -> {
            event.getPlayer().sendMessage("You clicked a button2");
        });

        Button button3 = new Button("\uE001", "\uE002", 3).onClick(event -> {
            event.getPlayer().sendMessage("You clicked a button3");
        });

        Text text = new Text("Here you can put\n some text. This is just for\n testing purposes.\n As you can see, \nit's working quite nicely and \nlook at this cool hovering effect.")
                .setSize(1.4f);

        SimpleGui gui = new SimpleGui("Some title")
                .addContent(text)
                .addButton(button)
                .addButton(button2)
                .addButton(button3);
        gui.open((Player) commandSender);

        return false;
    }
}
