package com.github.dvdme.DarkSkyJava;


import com.eclipsesource.json.JsonObject;

public class CurrentlyWeatherDataPoint extends DarkSkyWeatherDataPoint {

	private static final String currentlyReplyKey = "currently";

	public CurrentlyWeatherDataPoint(DarkSky darkSky){
		super(darkSky.getJsonResponse().get(currentlyReplyKey).asObject());
	}


}
