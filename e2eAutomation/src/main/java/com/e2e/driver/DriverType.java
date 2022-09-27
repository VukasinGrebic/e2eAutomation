package com.e2e.driver;

import java.util.Locale;

public enum DriverType {

    FIREFOX("Firefox"),
    CHROME("Chrome"),
    EDGE("Edge"),
    IE("IE");

    DriverType(final String type) {
        this.driverType = type;
    }

    private String driverType;
    public String getDriverType() {
        return this.driverType;
    }

    public static DriverType getDriverByName(String browserName){
        return DriverType.valueOf(browserName.toUpperCase(Locale.ROOT));
    }
}
