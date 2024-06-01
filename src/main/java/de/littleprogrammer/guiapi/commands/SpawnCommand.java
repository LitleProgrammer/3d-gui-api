package de.littleprogrammer.guiapi.commands;

import de.littleprogrammer.guiapi.guis.PagedGui;
import de.littleprogrammer.guiapi.guis.SimpleGui;
import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Text;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        /*Button button = new Button("\uE001", "\uE002", 1)
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

        Text text = new Text("Here you can put\n some text. This is just for\n testing purposes.\n As you can see, \nit's working quite nicely and \nlook at this cool hovering effect.", 0)
                .setSize(1.4f);

        SimpleGui gui = new SimpleGui("Some title")
                .addContent(text)
                .addButton(button)
                .addButton(button2)
                .addButton(button3);
        gui.open((Player) commandSender);*/

        List<String> stringList = new ArrayList<>();
        stringList.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Some Heading\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Another Heading\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.RED.toString() + ChatColor.BOLD + "3rd Heading\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.BLUE.toString() + ChatColor.BOLD + "Happy Happy\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Yellow?!\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "6th Content\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.AQUA.toString() + ChatColor.BOLD + "How many are there?\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.GRAY.toString() + ChatColor.BOLD + "Still same text\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "You can stop now\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Stop...\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.DARK_BLUE.toString() + ChatColor.BOLD + "Stooooppp!!!\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");
        stringList.add(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "The end.\n" + ChatColor.RESET + ChatColor.WHITE + "Lorem ipsum dolor\n sit amet,\n consetetur sadipscing\n elitr, sed diam\n nonumy eirmod\n tempor invidunt ut\n labore et dolore\n magna aliquyam");

        PagedGui pagedGui = new PagedGui("Title", 0);
        pagedGui.addContent(stringList);
        pagedGui.open((Player) commandSender);

        return false;
    }
}
