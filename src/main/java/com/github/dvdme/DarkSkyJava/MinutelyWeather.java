package com.github.dvdme.DarkSkyJava;

public class MinutelyWeather {

	DarkSkyWeatherDataBlock minutely;

	public MinutelyWeather(DarkSky fio){

		init(fio);

	}

	private void init(DarkSky darkSky){

		if(darkSky.hasMinutely()){
			this.minutely = new DarkSkyWeatherDataBlock(darkSky.getMinutely());
			this.minutely.setTimezone(darkSky.getTimezone());
		}
		else {
			this.minutely = null;
		}
	}

	/**
	 * Returns the data point for the given minute in the minutely report
	 * @param minute the minute to get
	 * @return return the data point with the report
	 */
	public DarkSkyWeatherDataPoint getMinute(int minute){

		//return this.minutely == null ? null : this.minutely.datapoint(minute);
		return null;
	}

	/**
	 * Returns the minutes in the minutely report
	 * @return integer with the number of minute. Returns -1 if there is not data in the report.
	 */
	public int minutes(){
		return this.minutely == null ? -1 : this.minutely.datablockSize();
	}
	
	/**
	 * Returns forecast summary
	 * @return forecast summary or null
	 */
	public String getSummary() {
		return minutely == null ? null : minutely.summary();
	}
	
	/**
	 * Returns forecast icon
	 * @return forecast icon or null
	 */
	public String getIcon() {
		return minutely == null ? null : minutely.icon();
	}

}
