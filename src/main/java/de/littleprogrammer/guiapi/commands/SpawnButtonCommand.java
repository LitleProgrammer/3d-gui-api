package de.littleprogrammer.guiapi.commands;

import de.littleprogrammer.guiapi.components.Button;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnButtonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        new Button((Player) commandSender, "This is the \n text on the display", "localName");


        return false;
    }
}
