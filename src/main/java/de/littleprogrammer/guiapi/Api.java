package de.littleprogrammer.guiapi;

import de.littleprogrammer.guiapi.enums.ServerVersion;
import de.littleprogrammer.guiapi.listeners.GuiEvents;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Api {

    private final JavaPlugin plugin;
    private static Api instance;
    private ServerVersion version;
    private final Listener listener = new GuiEvents();
    private Map<UUID, GUI> guis = new HashMap<>();

    private Api(JavaPlugin plugin) {
        this.plugin = plugin;
        instance = this;
    }

    public void init() {
        String secIndicator = plugin.getServer().getBukkitVersion().split("\\.")[1];
        String preTrdIndicator = plugin.getServer().getBukkitVersion().split("\\.")[2];
        String trdIndicator = preTrdIndicator.split("-")[0];

        if (Integer.parseInt(secIndicator) == 20) {
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

        this.plugin.getServer().getPluginManager().registerEvents(this.listener, this.plugin);
    }

    public JavaPlugin getPlugin() {return this.plugin;}
    public static Api getInstance() {return instance;}
    public ServerVersion getVersion() {
        return version;
    }
    public static BukkitScheduler getScheduler() {
        return Api.getInstance().getPlugin().getServer().getScheduler();
    }

    public GUI getGUI(UUID uuid) {
        return guis.get(uuid);
    }

    public GUI getGUI(Player player) {
        return guis.get(player.getUniqueId());
    }

    public Map<UUID, GUI> getGuis() {
        return guis;
    }

    public Listener getListener() {
        return listener;
    }
}
