package com.github.dvdme.DarkSkyJava;


public class HourlyWeatherDataBlock extends DarkSkyWeatherDataBlock {

	private static final String hourlyReplyKey = "hourly";

	public HourlyWeatherDataBlock(DarkSky darkSky){
		super(darkSky.getJsonResponse().get(hourlyReplyKey).asObject());
	}

}
