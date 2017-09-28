package com.github.dvdme.DarkSkyJava;


public class HourlyWeatherDataPoint extends DarkSkyWeatherDataBlock {

	private static final String hourlyReplyKey = "hourly";

	public HourlyWeatherDataPoint(DarkSky darkSky){
		super(darkSky.getJsonResponse().get(hourlyReplyKey).asObject());
	}


}

