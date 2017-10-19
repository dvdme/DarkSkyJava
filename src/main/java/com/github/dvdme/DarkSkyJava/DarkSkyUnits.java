package com.github.dvdme.DarkSkyJava;
// TODO: move to utils
public enum DarkSkyUnits {
    US("us"),
    SI("si"),
    CA("ca"),
    UK("uk"),
    AUTO("auto");

    private final String unit;

    private DarkSkyUnits(final String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit;
    }
}