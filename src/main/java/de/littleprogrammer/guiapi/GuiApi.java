package de.littleprogrammer.guiapi;

import de.littleprogrammer.guiapi.commands.SpawnCommand;
import de.littleprogrammer.guiapi.enums.ServerVersion;
import de.littleprogrammer.guiapi.listeners.GuiEvents;
import de.littleprogrammer.guiapi.listeners.MoveListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class GuiApi extends JavaPlugin {

    private JavaPlugin plugin;
    private static GuiApi instance;
    private ServerVersion version;
    private final Listener listener = new GuiEvents();
    private Map<UUID, SimpleGui> guis = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.plugin = this;
        instance = this;

        init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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

        Bukkit.getPluginManager().registerEvents(this.listener, this.plugin);
        Bukkit.getPluginManager().registerEvents(new MoveListener(), this.plugin);

        getCommand("spawnGui").setExecutor(new SpawnCommand());
    }

    public JavaPlugin getPlugin() {return this.plugin;}
    public static GuiApi getInstance() {return instance;}
    public ServerVersion getVersion() {
        return version;
    }
    public static BukkitScheduler getScheduler() {
        return GuiApi.getInstance().getPlugin().getServer().getScheduler();
    }

    public SimpleGui getGUI(UUID uuid) {
        return guis.get(uuid);
    }

    public SimpleGui getGUI(Player player) {
        return guis.get(player.getUniqueId());
    }

    public Map<UUID, SimpleGui> getGuis() {
        return guis;
    }

    public Listener getListener() {
        return listener;
    }
}
