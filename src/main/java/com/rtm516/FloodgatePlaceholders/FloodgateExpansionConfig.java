package com.rtm516.FloodgatePlaceholders;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class FloodgateExpansionConfig {
    private final FloodgateExpansion expansion;

    public FloodgateExpansionConfig(FloodgateExpansion expansion) {
        this.expansion = expansion;
    }

    public boolean isSpecificDeviceDescriptors() {
        return (Boolean) expansion.get("specific-device-descriptors", true);
    }

    public DevicePlaceholders getDevice() {
        return new DevicePlaceholders(this, "device");
    }

    public GenericPlaceholders getLocale() {
        return new GenericPlaceholders(this, "locale");
    }

    public GenericPlaceholders getVersion() {
        return new GenericPlaceholders(this, "version");
    }

    public GenericPlaceholders getXboxUsername() {
        return new GenericPlaceholders(this, "xbox-username");
    }

    public GenericPlaceholders getXboxXuid() {
        return new GenericPlaceholders(this, "xbox-xuid");
    }

    public static class DevicePlaceholders {

        private final FloodgateExpansionConfig config;
        private final String parent;

        public DevicePlaceholders(FloodgateExpansionConfig config, String parent) {
            this.config = config;
            this.parent = parent;
        }

        public String getJava() {
            return config.expansion.getString(parent + ".java", "");
        }

        public String getGeneric() {
            return config.expansion.getString(parent + ".generic", "");
        }

        public String getUnknown() {
            return config.expansion.getString(parent + ".unknown", "");
        }

        public String getGoogle() {
            return config.expansion.getString(parent + ".google", "");
        }

        public String getIOS() {
            return config.expansion.getString(parent + ".ios", "");
        }

        public String getOSX() {
            return config.expansion.getString(parent + ".osx", "");
        }

        public String getAmazon() {
            return config.expansion.getString(parent + ".fireos", "");
        }

        public String getGearVR() {
            return config.expansion.getString(parent + ".gearvr", "");
        }

        public String getHololens() {
            return config.expansion.getString(parent + ".hololens", "");
        }

        public String getUwp() {
            return config.expansion.getString(parent + ".uwp", "");
        }

        public String getWin32() {
            return config.expansion.getString(parent + ".win32", "");
        }

        public String getDedicated() {
            return config.expansion.getString(parent + ".dedicated", "");
        }

        public String getPs4() {
            return config.expansion.getString(parent + ".ps4", "");
        }

        public String getNX() {
            return config.expansion.getString(parent + ".nx", "");
        }

        public String getXbox() {
            return config.expansion.getString(parent + ".xbox", "");
        }
    }

    public static class GenericPlaceholders {

        private final FloodgateExpansionConfig config;
        private final String parent;

        public GenericPlaceholders(FloodgateExpansionConfig config, String parent) {
            this.config = config;
            this.parent = parent;
        }

        public String getFound() {
            return config.expansion.getString(parent + ".found", "");
        }

        public String getNone(Player player) {
            return PlaceholderAPI.setPlaceholders(player, config.expansion.getString(parent + ".none", ""));
        }
    }
}
