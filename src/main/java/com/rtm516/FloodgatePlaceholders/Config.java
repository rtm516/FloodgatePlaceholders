package com.rtm516.FloodgatePlaceholders;

public interface Config {
    boolean isSpecificDeviceDescriptors();

    DevicePlaceholders getDevice();

    GenericPlaceholders getLocale();

    GenericPlaceholders getVersion();

    GenericPlaceholders getXboxUsername();

    GenericPlaceholders getXboxXuid();

    interface DevicePlaceholders {
        String getJava();

        String getGeneric();

        String getUnknown();

        String getGoogle();

        String getIOS();

        String getOSX();

        String getAmazon();

        String getGearVR();

        String getHololens();

        String getUwp();

        String getWin32();

        String getDedicated();

        String getPs4();

        String getNX();

        String getXbox();
    }

    interface GenericPlaceholders {
        String getFound();

        String getNone();
    }
}
