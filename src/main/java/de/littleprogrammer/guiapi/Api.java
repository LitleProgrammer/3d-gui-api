package de.littleprogrammer.guiapi;

import de.littleprogrammer.guiapi.commands.SpawnButtonCommand;
import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.enums.ServerVersion;
import de.littleprogrammer.guiapi.listeners.MoveListener;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Api extends JavaPlugin {

    private static Api instance;
    private static ServerVersion version;
    private Map<UUID, Button> buttons = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        String secIndicator = Bukkit.getBukkitVersion().split("\\.")[1];
        String preTrdIndicator = Bukkit.getBukkitVersion().split("\\.")[2];
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

        getCommand("spawnBtn").setExecutor(new SpawnButtonCommand());
        Bukkit.getPluginManager().registerEvents(new MoveListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Api getInstance() { return instance; }
    public Map<UUID, Button> getButtons() { return buttons; }
    public static ServerVersion getVersion() { return version; }
}
