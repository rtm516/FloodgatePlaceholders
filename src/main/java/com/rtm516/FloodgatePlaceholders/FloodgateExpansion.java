package com.rtm516.FloodgatePlaceholders;

import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.geysermc.floodgate.util.DeviceOs;

import java.util.HashMap;
import java.util.Map;

public class FloodgateExpansion extends PlaceholderExpansion implements Configurable {

    public static final String VERSION = "DEV";

    private FloodgateExpansionConfig config;

    public FloodgateExpansion() {
        config = new FloodgateExpansionConfig(this);
    }

    @Override
    public boolean canRegister() {
        return Bukkit.getPluginManager().isPluginEnabled(getRequiredPlugin());
    }

    @Override
    public String getRequiredPlugin() {
        return "floodgate";
    }

    @Override
    public String getAuthor() {
        return "rtm516";
    }

    @Override
    public String getIdentifier() {
        return "floodgate";
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }

        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());

        switch (identifier) {
            case "device":
                return getPlayerDeviceString(player);

            case "locale":
            case "locale_upper":
                if (floodgatePlayer != null) {
                    boolean upper = identifier.endsWith("_upper");
                    return config.getLocale().getFound().replace("%locale%", upper ? floodgatePlayer.getLanguageCode().toUpperCase() : floodgatePlayer.getLanguageCode().toLowerCase());
                } else {
                    return config.getLocale().getNone(player);
                }

            case "version":
                if (floodgatePlayer != null) {
                    return config.getVersion().getFound().replace("%version%", floodgatePlayer.getVersion());
                } else {
                    return config.getVersion().getNone(player);
                }

            case "username":
                if (floodgatePlayer != null) {
                    return config.getXboxUsername().getFound().replace("%username%", floodgatePlayer.getUsername());
                } else {
                    return config.getXboxUsername().getNone(player);
                }

            case "xuid":
                if (floodgatePlayer != null) {
                    return config.getXboxXuid().getFound().replace("%xuid%", floodgatePlayer.getXuid());
                } else {
                    return config.getXboxXuid().getNone(player);
                }

            case "isbedrock":
                if (floodgatePlayer != null) {
                    return config.getIsBedrock().getFound();
                } else {
                    return config.getIsBedrock().getNone(player);
                }
        }

        return null;
    }

    /**
     * Get the device string from config for the specified player
     *
     * @param player The player to get the device for
     * @return The formatted device string from config
     */
    private String getPlayerDeviceString(Player player) {
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        if (floodgatePlayer != null) {
            if (config.isSpecificDeviceDescriptors()) {
                switch (floodgatePlayer.getDeviceOs()) {
                    case GOOGLE:
                        return config.getDevice().getGoogle().replace("&", "§");
                    case IOS:
                        return config.getDevice().getIOS().replace("&", "§");
                    case OSX:
                        return config.getDevice().getOSX().replace("&", "§");
                    case AMAZON:
                        return config.getDevice().getAmazon().replace("&", "§");
                    case GEARVR:
                        return config.getDevice().getGearVR().replace("&", "§");
                    case HOLOLENS:
                        return config.getDevice().getHololens().replace("&", "§");
                    case UWP:
                        return config.getDevice().getUwp().replace("&", "§");
                    case WIN32:
                        return config.getDevice().getWin32().replace("&", "§");
                    case DEDICATED:
                        return config.getDevice().getDedicated().replace("&", "§");
                    case PS4:
                        return config.getDevice().getPs4().replace("&", "§");
                    case NX:
                        return config.getDevice().getNX().replace("&", "§");
                    case XBOX:
                        return config.getDevice().getXbox().replace("&", "§");
                    default:
                        return config.getDevice().getUnknown().replace("&", "§");
                }
            } else {
                return config.getDevice().getGeneric().replace("&", "§");
            }
        } else {
            return config.getDevice().getJava().replace("&", "§");
        }
    }

    @Override
    public Map<String, Object> getDefaults() {
        final Map<String, Object> defaults = new HashMap<>();
        defaults.put("device.java", "Java");
        defaults.put("device.generic", "Bedrock");
        defaults.put("device.unknown", DeviceOs.UNKNOWN.name());
        defaults.put("device.google", DeviceOs.GOOGLE.name());
        defaults.put("device.ios", DeviceOs.IOS.name());
        defaults.put("device.osx", DeviceOs.OSX.name());
        defaults.put("device.amazon", DeviceOs.AMAZON.name());
        defaults.put("device.gearvr", DeviceOs.GEARVR.name());
        defaults.put("device.hololens", DeviceOs.HOLOLENS.name());
        defaults.put("device.uwp", DeviceOs.UWP.name());
        defaults.put("device.win32", DeviceOs.WIN32.name());
        defaults.put("device.dedicated", DeviceOs.DEDICATED.name());
        defaults.put("device.ps4", DeviceOs.PS4.name());
        defaults.put("device.nx", DeviceOs.NX.name());
        defaults.put("device.xbox", DeviceOs.XBOX.name());
        defaults.put("locale.found", "%locale%");
        defaults.put("locale.none", "N/A");
        defaults.put("version.found", "%version%");
        defaults.put("version.none", "N/A");
        defaults.put("xbox-username.found", "%username%");
        defaults.put("xbox-username.none", "N/A");
        defaults.put("xbox-xuid.found", "%xuid%");
        defaults.put("xbox-xuid.none", "N/A");
        defaults.put("is-bedrock.found", "true");
        defaults.put("is-bedrock.none", "false");
        return defaults;
    }
}
