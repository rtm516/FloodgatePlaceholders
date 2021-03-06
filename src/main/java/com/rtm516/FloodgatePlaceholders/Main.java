package com.rtm516.FloodgatePlaceholders;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static final String NAME = "FloodgatePlaceholders";
    public static final String VERSION = "DEV";

    @Getter
    private PluginConfig configuration;

    @Override
    public void onLoad() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        configuration = PluginConfig.load(getLogger(), getDataFolder().toPath().resolve("config.yml"));
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        if (Bukkit.getPluginManager().getPlugin("floodgate") != null && Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder(getConfiguration()).register();
        } else {
            getLogger().warning("Floodgate 2.0 or PlaceholderAPI not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}
