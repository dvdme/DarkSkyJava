package com.github.dvdme.DarkSkyJava;

public class DarkSkyWeatherResponse implements WeatherResponse {

    private Double latitude;
    private  Double longitude;
    private WeatherDataPoint currently;
    private WeatherDataBlock minutely;
    private WeatherDataBlock hourly;
    private WeatherDataBlock daily;

    public DarkSkyWeatherResponse(DarkSky darkSky) {
        this.currently = new CurrentlyWeatherDataPoint(darkSky);
        this.hourly = new HourlyWeatherDataPoint(darkSky);
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
        return this.currently;
    }

    @Override
    public WeatherDataBlock getMinuty() {
        return null;
    }

    @Override
    public WeatherDataBlock getHourly() {
        return this.hourly;
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
