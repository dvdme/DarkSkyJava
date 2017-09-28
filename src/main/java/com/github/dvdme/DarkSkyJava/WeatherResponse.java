package com.github.dvdme.DarkSkyJava;

public interface WeatherResponse {
    Double getLatitude();

    Double getLongitude();

    Double getTimezone();

    WeatherDataPoint getCurrently();

    WeatherDataBlock getMinuty();

    WeatherDataBlock getHourly();

    WeatherDataBlock getDaily();

    WeatherAlerts getAlerts();

    WeatherFlags getFlags();
}
