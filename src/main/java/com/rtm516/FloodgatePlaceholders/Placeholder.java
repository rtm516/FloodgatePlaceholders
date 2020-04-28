package com.rtm516.FloodgatePlaceholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.FloodgateAPI;
import org.geysermc.floodgate.FloodgatePlayer;

public class Placeholder extends PlaceholderExpansion {

    private Main plugin;

    public Placeholder(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "floodgate";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if(player == null) {
            return "";
        }

        switch (identifier) {
            case "device":
                return getPlayerDeviceString(player);

            case "locale":
            case "locale_upper":
                if (FloodgateAPI.isBedrockPlayer(player.getUniqueId())) {
                    FloodgatePlayer floodgatePlayer = FloodgateAPI.getPlayer(player.getUniqueId());
                    boolean upper = identifier.endsWith("_upper");
                    return plugin.getConfiguration().getLocale().getFound().replace("%locale%", upper ? floodgatePlayer.getLanguageCode().toUpperCase() : floodgatePlayer.getLanguageCode().toLowerCase());
                } else {
                    return plugin.getConfiguration().getLocale().getNone();
                }

            case "version":
                if (FloodgateAPI.isBedrockPlayer(player.getUniqueId())) {
                    FloodgatePlayer floodgatePlayer = FloodgateAPI.getPlayer(player.getUniqueId());
                    return plugin.getConfiguration().getVersion().getFound().replace("%version%", floodgatePlayer.getVersion());
                } else {
                    return plugin.getConfiguration().getVersion().getNone();
                }

            case "username":
                if (FloodgateAPI.isBedrockPlayer(player.getUniqueId())) {
                    FloodgatePlayer floodgatePlayer = FloodgateAPI.getPlayer(player.getUniqueId());
                    return plugin.getConfiguration().getXboxUsername().getFound().replace("%username%", floodgatePlayer.getUsername());
                } else {
                    return plugin.getConfiguration().getXboxUsername().getNone();
                }

            case "xuid":
                if (FloodgateAPI.isBedrockPlayer(player.getUniqueId())) {
                    FloodgatePlayer floodgatePlayer = FloodgateAPI.getPlayer(player.getUniqueId());
                    return plugin.getConfiguration().getXboxXuid().getFound().replace("%xuid%", floodgatePlayer.getXuid());
                } else {
                    return plugin.getConfiguration().getXboxXuid().getNone();
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
        if (FloodgateAPI.isBedrockPlayer(player.getUniqueId())) {
            if (plugin.getConfiguration().isSpecificDeviceDescriptors()) {
                FloodgatePlayer floodgatePlayer = FloodgateAPI.getPlayer(player.getUniqueId());
                switch (floodgatePlayer.getDeviceOS()) {
                    case ANDROID:
                        return plugin.getConfiguration().getDevice().getAndroid().replace("&", "§");
                    case IOS:
                        return plugin.getConfiguration().getDevice().getIOS().replace("&", "§");
                    case OSX:
                        return plugin.getConfiguration().getDevice().getOSX().replace("&", "§");
                    case FIREOS:
                        return plugin.getConfiguration().getDevice().getFireos().replace("&", "§");
                    case GEARVR:
                        return plugin.getConfiguration().getDevice().getGearVR().replace("&", "§");
                    case HOLOLENS:
                        return plugin.getConfiguration().getDevice().getHololens().replace("&", "§");
                    case WIN10:
                        return plugin.getConfiguration().getDevice().getWin10().replace("&", "§");
                    case WIN32:
                        return plugin.getConfiguration().getDevice().getWin32().replace("&", "§");
                    case DEDICATED:
                        return plugin.getConfiguration().getDevice().getDedicated().replace("&", "§");
                    case ORBIS:
                        return plugin.getConfiguration().getDevice().getOrbis().replace("&", "§");
                    case NX:
                        return plugin.getConfiguration().getDevice().getNX().replace("&", "§");
                    case SWITCH:
                        return plugin.getConfiguration().getDevice().getNintendoSwitch().replace("&", "§");
                    case XBOX_ONE:
                        return plugin.getConfiguration().getDevice().getXboxOne().replace("&", "§");
                    default:
                        return plugin.getConfiguration().getDevice().getUnknown().replace("&", "§");
                }
            }else{
                return plugin.getConfiguration().getDevice().getGeneric().replace("&", "§");
            }
        } else {
            return plugin.getConfiguration().getDevice().getJava().replace("&", "§");
        }
    }

}
