package com.github.dvdme.DarkSkyJava;

public class DarkSkyWeatherResponse implements WeatherResponse {

    private Double latitude;

    public DarkSkyWeatherResponse(DarkSky darkSky) {

    }

    @Override
    public Double getLatitude() {
        return this.latitude;
    }

    @Override
    public Double getLongitude() {
        return null;
    }

    @Override
    public Double getTimezone() {
        return null;
    }

    @Override
    public WeatherDataPoint getCurrently() {
        return null;
    }

    @Override
    public WeatherDataBlock getMinuty() {
        return null;
    }

    @Override
    public WeatherDataBlock getHourly() {
        return null;
    }

    @Override
    public WeatherDataBlock getDaily() {
        return null;
    }

    @Override
    public WeatherAlerts getAlerts() {
        return null;
    }

    @Override
    public WeatherFlags getFlags() {
        return null;
    }
}
