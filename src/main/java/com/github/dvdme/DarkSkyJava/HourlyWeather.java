package com.github.dvdme.DarkSkyJava;

public class HourlyWeather {

	DarkSkyWeatherDataBlock hourly;

	public HourlyWeather(DarkSky darkSky){

		init(darkSky);

	}

	private void init(DarkSky darkSky){

		if(darkSky.hasHourly()){
			this.hourly = new DarkSkyWeatherDataBlock(darkSky.getHourly());
			this.hourly.setTimezone(darkSky.getTimezone());
		}
		else {
			this.hourly = null;
		}
	}

	/**
	 * Returns the data point for the given day in the hourly report
	 * @param hour the hour to get
	 * @return return the data point with the report
	 */
	public DarkSkyWeatherDataPoint getHour(int hour){

		//return this.hourly == null ? null : this.hourly.datapoint(hour);
		return null;
	}

	/**
	 * Returns the hours in the hourly report
	 * @return integer with the number of hours. Returns -1 if there is not data in the report.
	 */
	public int hours(){
		return this.hourly == null ? -1 : this.hourly.datablockSize();
	}

	/**
	 * Returns forecast summary
	 * @return forecast summary or null
	 */
	public String getSummary() {
		return hourly == null ? null : hourly.summary();
	}

	/**
	 * Returns forecast icon
	 * @return forecast icon or null
	 */
	public String getIcon() {
		return hourly == null ? null : hourly.icon();
	}

}
