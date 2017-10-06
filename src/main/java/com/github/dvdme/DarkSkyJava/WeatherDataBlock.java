package com.github.dvdme.DarkSkyJava;

import java.util.List;

public interface WeatherDataBlock {

    boolean exists();

    List<DarkSkyWeatherDataPoint> data();

    String summary();

    String icon();
}
