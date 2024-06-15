package de.littleprogrammer.guiapi;

import de.littleprogrammer.guiapi.commands.SpawnCommand;
import de.littleprogrammer.guiapi.enums.ServerVersion;
import de.littleprogrammer.guiapi.guis.Gui;
import de.littleprogrammer.guiapi.guis.SimpleGui;
import de.littleprogrammer.guiapi.listeners.GuiEvents;
import de.littleprogrammer.guiapi.listeners.MoveListener;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class GuiApi /*extends JavaPlugin*/ {

    private JavaPlugin plugin;
    private static GuiApi instance;
    private ServerVersion version;
    private final Listener listener = new GuiEvents();
    private final Listener moveListener = new MoveListener();
    private Map<UUID, Gui> guis = new HashMap<>();

    public GuiApi(JavaPlugin plugin) {
        this.plugin = plugin;
        instance = this;
    }

    /*@Override
    public void onEnable() {
        // Plugin startup logic
        this.plugin = this;
        instance = this;

        init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }*/

    public void init() {
        //This method checks the server version, to determine weather it should use the new 1.20.2 teleport interpolation or my own teleport interpolation
        String secIndicator = plugin.getServer().getBukkitVersion().split("\\.")[1];
        String preTrdIndicator = plugin.getServer().getBukkitVersion().split("\\.")[2];
        String trdIndicator = preTrdIndicator.split("-")[0];

        //If version is like 1.21 it threw an error
        try {
            Integer.parseInt(secIndicator);
        } catch (NumberFormatException e) {
            secIndicator = secIndicator.split("-")[0];
        }

        //If version is like 1.21 there is no .0 in the input string, but it needs to be for the following method
        try {
            Integer.parseInt(preTrdIndicator);
        } catch (NumberFormatException e) {
            trdIndicator = "0";
        }

        //1.20.x
        if (Integer.parseInt(secIndicator) == 20) {
            //1.20.2
            if (Integer.parseInt(trdIndicator) >= 2) {
                version = ServerVersion.POST_1_20_2;
            } else {
                version = ServerVersion.PRE_1_20_2;
            }
        } else {
            if (Integer.parseInt(secIndicator) > 20) {
                version = ServerVersion.POST_1_20_2;
            } else {
                version = ServerVersion.PRE_1_20_2;
            }
        }

        //register the two listeners needed
        getPlugin().getServer().getPluginManager().registerEvents(this.listener, this.plugin);
        getPlugin().getServer().getPluginManager().registerEvents(this.moveListener, this.plugin);

        //this.getPlugin().getCommand("spawnGui").setExecutor(new SpawnCommand());
    }

    public JavaPlugin getPlugin() {return this.plugin;}
    public static GuiApi getInstance() {return instance;}
    public ServerVersion getVersion() {
        return version;
    }
    public static BukkitScheduler getScheduler() {
        return GuiApi.getInstance().getPlugin().getServer().getScheduler();
    }

    public Gui getGUI(UUID uuid) {
        return guis.get(uuid);
    }

    public Gui getGUI(Player player) {
        return guis.get(player.getUniqueId());
    }

    public Map<UUID, Gui> getGuis() {
        return guis;
    }

    public Listener getListener() {
        return listener;
    }
}
