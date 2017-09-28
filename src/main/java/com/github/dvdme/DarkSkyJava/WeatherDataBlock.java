package com.github.dvdme.DarkSkyJava;

import java.util.List;

public interface WeatherDataBlock {

    List<DarkSkyWeatherDataPoint> data();

    String summary();

    String icon();
}
