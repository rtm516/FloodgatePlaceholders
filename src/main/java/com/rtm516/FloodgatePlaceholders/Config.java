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
public class Config {
    @JsonProperty(value = "specific-device-descriptors")
    private boolean specificDeviceDescriptors;

    @JsonProperty(value = "device")
    private DevicePlaceholders device;

    @JsonProperty(value = "locale")
    private GenericPlaceholders locale;

    @JsonProperty(value = "version")
    private GenericPlaceholders version;

    @JsonProperty(value = "xbox-username")
    private GenericPlaceholders xboxUsername;

    @JsonProperty(value = "xbox-xuid")
    private GenericPlaceholders xboxXuid;

    @Getter
    public static class DevicePlaceholders {
        @JsonProperty("java")
        private String java;

        @JsonProperty("generic")
        private String generic;

        @JsonProperty("unknown")
        private String unknown;

        @JsonProperty("android")
        private String android;

        @JsonProperty("ios")
        private String iOS;

        @JsonProperty("osx")
        private String OSX;

        @JsonProperty("fireos")
        private String fireos;

        @JsonProperty("gearvr")
        private String gearVR;

        @JsonProperty("hololens")
        private String hololens;

        @JsonProperty("win10")
        private String win10;

        @JsonProperty("win32")
        private String win32;

        @JsonProperty("dedicated")
        private String dedicated;

        @JsonProperty("orbis")
        private String orbis;

        @JsonProperty("nx")
        private String NX;

        @JsonProperty("switch")
        private String nintendoSwitch;

        @JsonProperty("xboxOne")
        private String xboxOne;
    }

    @Getter
    public static class GenericPlaceholders {
        @JsonProperty("found")
        private String found;

        @JsonProperty("none")
        private String none;
    }

    public static Config load(Logger logger, Path configPath) {
        Config config = null;
        try {
            try {
                if (!configPath.toFile().exists())
                    Files.copy(Config.class.getClassLoader().getResourceAsStream("config.yml"), configPath);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error while creating config.yml", e);
            }

            config = new ObjectMapper(new YAMLFactory()).readValue(Files.readAllBytes(configPath), Config.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while loading config.yml", e);
        }

        if (config == null) {
            throw new RuntimeException("Failed to load config file! Try to delete the data folder of FloodgatePlaceholders");
        }

        return config;
    }
}
