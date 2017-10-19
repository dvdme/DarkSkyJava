package com.github.dvdme.DarkSkyJava;


import com.eclipsesource.json.JsonObject;

public class CurrentlyWeatherDataPoint extends DarkSkyWeatherDataPoint {

	private static final String currentlyReplyKey = "currently";

	public CurrentlyWeatherDataPoint(DarkSky darkSky){
			super(darkSky.getJsonResponse().get(currentlyReplyKey));
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Currently Data\n");
		String [] f  = getFields();
		for(int i = 0; i<f.length;i++) {
			sb.append(String.format("%s: %s\n", f[i], getByKey(f[i])));
		}
		return sb.toString();
	}

}
