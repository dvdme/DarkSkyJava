package com.github.dvdme.DarkSkyJava;

public interface WeatherDataPoint {
    String time();

    long timestamp();

    String summary();

    String icon();

    String sunriseTime();

    String sunsetTime();

    Double precipIntensity();

    Double precipIntensityMax();

    String precipIntensityMaxTime();

    Double precipProbability();

    String precipType();

    Double precipAccumulation();

    Double temperature();

    Double temperatureError();

    Double temperatureMin();

    Double temperatureMinError();

    String temperatureMinTime();

    Double temperatureMax();

    Double temperatureMaxError();

    String temperatureMaxTime();

    Double apparentTemperature();

    Double apparentTemperatureMin();

    String apparentTemperatureMinTime();

    Double apparentTemperatureMax();

    String apparentTemperatureMaxTime();

    Double dewPoint();

    Double dewPointError();

    Double windSpeed();

    Double windSpeedError();

    Double windBearing();

    Double windBearingError();

    Double cloudCover();

    Double cloudCoverError();

    Double humidity();

    Double humidityError();

    Double pressure();

    Double pressureError();

    Double visibility();

    Double visibilityError();

    Double ozone();

    Double nearestStormBearing();

    Double nearestStormDistance();

    Double moonPhase();
}
