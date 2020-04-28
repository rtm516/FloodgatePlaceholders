package com.rtm516.FloodgatePlaceholders;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Getter
    private Config configuration;

    @Override
    public void onLoad() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        configuration = Config.load(getLogger(), getDataFolder().toPath().resolve("config.yml"));
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        if (Bukkit.getPluginManager().getPlugin("floodgate-bukkit") != null && Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder(this).register();
        } else {
            getLogger().warning("floodgate-bukkit or PlaceholderAPI not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}
