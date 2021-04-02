package com.rtm516.FloodgatePlaceholders;

import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

import java.util.HashMap;
import java.util.Map;

public class Placeholder extends PlaceholderExpansion implements Configurable {

    private Config config;

    public Placeholder() {
        config = new ExpansionConfig(this);
    }

    public Placeholder(Config config){
        this.config = config;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return Bukkit.getPluginManager().isPluginEnabled(getRequiredPlugin());
    }

    @Override
    public String getRequiredPlugin() {
        return "floodgate";
    }

    @Override
    public String getAuthor(){
        return "rtm516";
    }

    @Override
    public String getIdentifier(){
        return "floodgate";
    }

    @Override
    public String getVersion(){
        return Main.VERSION;
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if(player == null) {
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
                    return config.getLocale().getNone();
                }

            case "version":
                if (floodgatePlayer != null) {
                    return config.getVersion().getFound().replace("%version%", floodgatePlayer.getVersion());
                } else {
                    return config.getVersion().getNone();
                }

            case "username":
                if (floodgatePlayer != null) {
                    return config.getXboxUsername().getFound().replace("%username%", floodgatePlayer.getUsername());
                } else {
                    return config.getXboxUsername().getNone();
                }

            case "xuid":
                if (floodgatePlayer != null) {
                    return config.getXboxXuid().getFound().replace("%xuid%", floodgatePlayer.getXuid());
                } else {
                    return config.getXboxXuid().getNone();
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
            }else{
                return config.getDevice().getGeneric().replace("&", "§");
            }
        } else {
            return config.getDevice().getJava().replace("&", "§");
        }
    }

    @Override
    public Map<String, Object> getDefaults() {
        if (!(config instanceof ExpansionConfig)) {
            return null;
        }

        final Map<String, Object> defaults = new HashMap<>();
        defaults.put("device.java", "&8[&aJava&8]");
        defaults.put("device.generic", "&8[&7Bedrock&8]");
        defaults.put("device.unknown", "&8[&aUnknown&8]");
        defaults.put("device.google", "&8[&aAndroid&8]");
        defaults.put("device.ios", "&8[&bi&fOS&8]");
        defaults.put("device.osx", "&8[&fOS&bX&8]");
        defaults.put("device.amazon", "&8[&6Amazon&8]");
        defaults.put("device.gearvr", "&8[&7Gear&fVR&8]");
        defaults.put("device.hololens", "&8[&7Holo&fLens&8]");
        defaults.put("device.uwp", "&8[&3UWP&8]");
        defaults.put("device.win32", "&8[&3Win&f32&8]");
        defaults.put("device.dedicated", "&8[&aDED&8]");
        defaults.put("device.ps4", "&8[&3PS4&8]");
        defaults.put("device.nx", "&8[&eSwitch&8]");
        defaults.put("device.xbox", "&8[&2Xbox&8]");
        defaults.put("locale.found", "&8[&6%locale%&8]");
        defaults.put("locale.none", "&8[&6N/A&8]");
        defaults.put("version.found", "&8[&6%version%&8]");
        defaults.put("version.none", "&8[&6N/A&8]");
        defaults.put("xbox-username.found", "&8[&6%username%&8]");
        defaults.put("xbox-username.none", "&8[&6N/A&8]");
        defaults.put("xbox-xuid.found", "&8[&6%xuid%&8]");
        defaults.put("xbox-xuid.none", "&8[&6N/A&8]");
        return defaults;
    }
}
