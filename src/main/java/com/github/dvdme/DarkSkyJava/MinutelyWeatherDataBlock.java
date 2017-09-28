package com.github.dvdme.DarkSkyJava;


public class MinutelyWeatherDataBlock extends DarkSkyWeatherDataBlock {

    private static final String minutelyReplyKey = "minutely";

    public MinutelyWeatherDataBlock(DarkSky darkSky) {
        super(darkSky.getJsonResponse().get(minutelyReplyKey).asObject());
    }
}