package com.github.dvdme.DarkSkyJava;


public class CurrentlyWeather {

	private DarkSkyWeatherDataPoint currently;

	public CurrentlyWeather(DarkSky darkSky){

		this.currently = null;
		init(darkSky);

	}

	private void init(DarkSky darkSky){

		if(darkSky.hasCurrently()){
			this.currently = new DarkSkyWeatherDataPoint(darkSky.getCurrently());
			this.currently.setTimezone(darkSky.getTimezone());
		}
	}

	/**
	 * Returns the data point from the currently report
	 * @return A WeatherDataPoint containing the currently report
	 */
	public DarkSkyWeatherDataPoint get(){
		return this.currently;
	}

}
