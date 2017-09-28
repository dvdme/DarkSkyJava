package com.github.dvdme.DarkSkyJava;




public class DailyWeatherDataBlock extends  DarkSkyWeatherDataBlock {

	private static final String dailyReplyKey = "daily";

	public DailyWeatherDataBlock(DarkSky darkSky) {
		super(darkSky.getJsonResponse().get(dailyReplyKey).asObject());
	}

}