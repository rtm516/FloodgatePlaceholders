package com.rtm516.FloodgatePlaceholders;

public class ExpansionConfig implements Config {
    private Placeholder expansion;

    public ExpansionConfig(Placeholder expansion) {
        this.expansion = expansion;
    }

    @Override
    public boolean isSpecificDeviceDescriptors() {
        return (Boolean) expansion.get("specific-device-descriptors", true);
    }

    @Override
    public DevicePlaceholders getDevice() {
        return new ExpansionDevicePlaceholders(this, "device");
    }

    @Override
    public GenericPlaceholders getLocale() {
        return new ExpansionGenericPlaceholders(this, "locale");
    }

    @Override
    public GenericPlaceholders getVersion() {
        return new ExpansionGenericPlaceholders(this, "version");
    }

    @Override
    public GenericPlaceholders getXboxUsername() {
        return new ExpansionGenericPlaceholders(this, "xbox-username");
    }

    @Override
    public GenericPlaceholders getXboxXuid() {
        return new ExpansionGenericPlaceholders(this, "xbox-xuid");
    }

    private class ExpansionDevicePlaceholders implements DevicePlaceholders {

        private final ExpansionConfig config;
        private final String parent;

        public ExpansionDevicePlaceholders(ExpansionConfig config, String parent) {
            this.config = config;
            this.parent = parent;
        }

        @Override
        public String getJava() {
            return config.expansion.getString(parent + ".java", "");
        }

        @Override
        public String getGeneric() {
            return config.expansion.getString(parent + ".generic", "");
        }

        @Override
        public String getUnknown() {
            return config.expansion.getString(parent + ".unknown", "");
        }

        @Override
        public String getGoogle() {
            return config.expansion.getString(parent + ".google", "");
        }

        @Override
        public String getIOS() {
            return config.expansion.getString(parent + ".ios", "");
        }

        @Override
        public String getOSX() {
            return config.expansion.getString(parent + ".osx", "");
        }

        @Override
        public String getAmazon() {
            return config.expansion.getString(parent + ".fireos", "");
        }

        @Override
        public String getGearVR() {
            return config.expansion.getString(parent + ".gearvr", "");
        }

        @Override
        public String getHololens() {
            return config.expansion.getString(parent + ".hololens", "");
        }

        @Override
        public String getUwp() {
            return config.expansion.getString(parent + ".uwp", "");
        }

        @Override
        public String getWin32() {
            return config.expansion.getString(parent + ".win32", "");
        }

        @Override
        public String getDedicated() {
            return config.expansion.getString(parent + ".dedicated", "");
        }

        @Override
        public String getPs4() {
            return config.expansion.getString(parent + ".ps4", "");
        }

        @Override
        public String getNX() {
            return config.expansion.getString(parent + ".nx", "");
        }

        @Override
        public String getXbox() {
            return config.expansion.getString(parent + ".xbox", "");
        }
    }

    private static class ExpansionGenericPlaceholders implements GenericPlaceholders {

        private final ExpansionConfig config;
        private final String parent;

        public ExpansionGenericPlaceholders(ExpansionConfig config, String parent) {
            this.config = config;
            this.parent = parent;
        }

        @Override
        public String getFound() {
            return config.expansion.getString(parent + ".found", "");
        }

        @Override
        public String getNone() {
            return config.expansion.getString(parent + ".none", "");
        }
    }
}
