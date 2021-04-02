package com.rtm516.FloodgatePlaceholders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class PluginConfig implements Config {
    @JsonProperty(value = "specific-device-descriptors")
    private boolean specificDeviceDescriptors;

    @JsonProperty(value = "device")
    private PluginDevicePlaceholders device;

    @JsonProperty(value = "locale")
    private PluginGenericPlaceholders locale;

    @JsonProperty(value = "version")
    private PluginGenericPlaceholders version;

    @JsonProperty(value = "xbox-username")
    private PluginGenericPlaceholders xboxUsername;

    @JsonProperty(value = "xbox-xuid")
    private PluginGenericPlaceholders xboxXuid;

    @Getter
    public static class PluginDevicePlaceholders implements DevicePlaceholders {
        @JsonProperty("java")
        private String java;

        @JsonProperty("generic")
        private String generic;

        @JsonProperty("unknown")
        private String unknown;

        @JsonProperty("google")
        private String google;

        @JsonProperty("ios")
        private String iOS;

        @JsonProperty("osx")
        private String OSX;

        @JsonProperty("amazon")
        private String amazon;

        @JsonProperty("gearvr")
        private String gearVR;

        @JsonProperty("hololens")
        private String hololens;

        @JsonProperty("uwp")
        private String uwp;

        @JsonProperty("win32")
        private String win32;

        @JsonProperty("dedicated")
        private String dedicated;

        @JsonProperty("ps4")
        private String ps4;

        @JsonProperty("nx")
        private String NX;

        @JsonProperty("xbox")
        private String xbox;
    }

    @Getter
    public static class PluginGenericPlaceholders implements GenericPlaceholders {
        @JsonProperty("found")
        private String found;

        @JsonProperty("none")
        private String none;
    }

    public static PluginConfig load(Logger logger, Path configPath) {
        PluginConfig pluginConfig = null;
        try {
            try {
                if (!configPath.toFile().exists())
                    Files.copy(PluginConfig.class.getClassLoader().getResourceAsStream("config.yml"), configPath);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error while creating config.yml", e);
            }

            pluginConfig = new ObjectMapper(new YAMLFactory()).readValue(Files.readAllBytes(configPath), PluginConfig.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while loading config.yml", e);
        }

        if (pluginConfig == null) {
            throw new RuntimeException("Failed to load config file! Try to delete the data folder of FloodgatePlaceholders");
        }

        return pluginConfig;
    }
}
