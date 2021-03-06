/**
 * This class represents a darksky.net Data Point.
 * It contains all the available fields in a forecast.
 * 
 * @author David Ervideira

 */

package com.github.dvdme.DarkSkyJava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.TimeZone;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class DarkSkyWeatherDataPoint implements WeatherDataPoint {

	private boolean exists;
	private HashMap<String, Object> datapoint;

	public DarkSkyWeatherDataPoint(){
		datapoint = new HashMap<String, Object>();
	}

	public DarkSkyWeatherDataPoint(JsonValue dp){
		if(dp != null) {
			datapoint = new HashMap<String, Object>();
			populateMap(dp.asObject());
			exists = true;
		} else {
			exists = false;
		}
	}

	/**
	 * Updates the data point data
	 * @param dp JsonObect with the data
	 */
	private void populateMap(JsonObject dp){
		for(int i = 0; i < dp.names().size(); i++){
			datapoint.put(dp.names().get(i), dp.get(dp.names().get(i)));
		}
	}

	/**
	 * Returns a String array with all the darksky.net fields available
	 * in this data point. It can be usefull to iterate over all
	 * available fields in a data point.
	 * 
	 * @return the String array with the field's names.
	 */
	public String [] getFields(){
		Object [] obj = datapoint.keySet().toArray();
		String [] out = new String[obj.length];
		for(int i=0; i<obj.length; i++)
			out[i] = String.valueOf(obj[i]);
		return out;
	}

	/* Gets  */
	/**
	 * Return the data point field with the corresponding key
	 * 
	 * @param key name of the field in the data point
	 * @return the field value
	 */
	public String getByKey(String key){
		return String.valueOf( datapoint.get(key) );
	}

	@Override
	public boolean exists() {
		return this.exists;
	}

    /**
     * Returns the time at which this data point occurs.
     * For more information refer to the API Docs:
     * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
     * @return An human-readable time string formated as [dd-MM-yyyy HH:mm:ss].  Returns null if the field is not defined.
     */
    public String time(){
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Long t = Long.parseLong( String.valueOf(this.datapoint.get("time")) );
        String time = dfm.format( t * 1000 );
        return time;
    }
	
	/**
	 * Returns the UNIX timestamp at which this data point occurs (seconds since UNIX epoch).
	 * e.g. 1433361600 = Wed, 03 Jun 2015 20:00:00 GMT
	 * @return A machine-readable UNIX timestamp as a long. 
	 */
	@Override
	public long timestamp() {
		return Long.parseLong( String.valueOf(this.datapoint.get("time")) );
	}

	/**
	 * Returns a human-readable text summary of the data point.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return String with the summary text. Returns null if the field is not defined.
	 */
	@Override
	public String summary(){
		if(this.datapoint.containsKey("summary"))
			return asString(this.datapoint.get("summary"));
		else
			return null;
	}

	/**
	 * Returns a machine-readable text summary of the data point, 
	 * suitable for selecting an icon for display.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return String with the icon text.  Returns null if the field is not defined.
	 */
	@Override
	public String icon(){
		if(this.datapoint.containsKey("icon"))
			return asString(this.datapoint.get("icon"));
		else
			return null;
	}

	/**
	 * Returns the sunset time for the given day.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return An human-readable time string formated as [HH:mm:ss].  Returns null if the field is not defined.
	 */
	@Override
	public String sunriseTime(){
		if(this.datapoint.containsKey("sunriseTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("sunriseTime"))) * 1000 );
			return time;
		}
		else
			return null;
	}

	/**
	 * Returns the sunrise time for the given day.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return An human-readable time string formated as [HH:mm:ss]. Returns null if the field is not defined.
	 */
	@Override
	public String sunsetTime(){
		if(this.datapoint.containsKey("sunsetTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("sunsetTime"))) * 1000 );
			return time;
		}
		else
			return null;
	}

	/**
	 * A numerical value representing the average expected intensity.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the precipitation intensity. Returns -1 if the field is not defined.
	 */
	@Override
	public Double precipIntensity(){
		if(this.datapoint.containsKey("precipIntensity"))
			return asDouble(this.datapoint.get("precipIntensity"));
		else
			return null;
	}

	/**
	 * A numerical values representing the maximum 
	 * expected intensity of precipitation.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the maximum precipitation intensity.  Returns -1 if the field is not defined.
	 */
	@Override
	public Double precipIntensityMax(){
		if(this.datapoint.containsKey("precipIntensityMax"))
			return asDouble(this.datapoint.get("precipIntensityMax"));
		else
			return null;
	}
	/**
	 * A numerical values representing the time at which the maximum 
	 * expected intensity of precipitation occurs.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return An human-readable time string formated as [HH:mm:ss]  Returns null if the field is not defined.
	 */
	@Override
	public String precipIntensityMaxTime(){
		if(this.datapoint.containsKey("precipIntensityMaxTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("precipIntensityMaxTime"))) * 1000 );
			return time;
		}
		else
			return null;
	}

	/**
	 * A numerical value between 0 and 1 (inclusive) representing 
	 * the probability of precipitation occuring at the given time.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the probabily. Returns -1 if the field is not defined.
	 */
	@Override
	public Double precipProbability(){
		if(this.datapoint.containsKey("precipProbability"))
			return asDouble(this.datapoint.get("precipProbability"));
		else
			return null;
	}

	/**
	 * A string representing the type of precipitation occurring at the given time. 
	 * If defined, this property will have one of the following values: rain, snow, 
	 * sleet or hail.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A string with the type of precipitation Returns null if the field is not defined.
	 */
	@Override
	public String precipType(){
		if(this.datapoint.containsKey("precipType"))
			return asString(this.datapoint.get("precipType"));
		else
			return null;
	} 

	/**
	 * The amount of snow fall accumulation expected to occur on the given day.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the expected value. Returns -1 if the field is not defined.
	 */
	@Override
	public Double precipAccumulation(){
		if(this.datapoint.containsKey("precipAccumulation"))
			return asDouble(this.datapoint.get("precipAccumulation"));
		else
			return null;
	}

	/**
	 * The temperature at the given time using the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the temperature. Returns null if the field is not defined.
	 */
	@Override
	public Double temperature(){
		if(this.datapoint.containsKey("temperature"))
			return asDouble(this.datapoint.get("temperature"));
		else
			return null;
	}
	
	/**
	 * The temperature error at the given time using the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the temperature. Returns null if the field is not defined.
	 */
	@Override
	public Double temperatureError(){
		if(this.datapoint.containsKey("temperatureError"))
			return asDouble(this.datapoint.get("temperatureError"));
		else
			return null;
	}

	/**
	 * The minimum temperature expected for a given day the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the minimum temperature. Returns null if the field is not defined.
	 */
	@Override
	public Double temperatureMin(){
		if(this.datapoint.containsKey("temperatureMin"))
			return asDouble(this.datapoint.get("temperatureMin"));
		else
			return null;
	}
	
	/**
	 * The minimum temperature error expected for a given day the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the minimum temperature. Returns null if the field is not defined.
	 */
	@Override
	public Double temperatureMinError(){
		if(this.datapoint.containsKey("temperatureMinError"))
			return asDouble(this.datapoint.get("temperatureMinError"));
		else
			return null;
	}

	/**
	 * The time at with the minimum temperature is expected to occur.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return An human-readable time string formated as [HH:mm:ss]  Returns null if the field is not defined.
	 */
	@Override
	public String temperatureMinTime(){
		if(this.datapoint.containsKey("temperatureMinTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("temperatureMinTime"))) * 1000 );
			return time;
		}
		else
			return null;
	}

	/**
	 * The maximum temperature expected for a given day the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the maximum temperature. Returns null if the field is not defined.
	 */
	@Override
	public Double temperatureMax(){
		if(this.datapoint.containsKey("temperatureMax"))
			return asDouble(this.datapoint.get("temperatureMax"));
		else
			return null;
	}
	
	/**
	 * The maximum temperature error expected for a given day the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the maximum temperature. Returns null if the field is not defined.
	 */
	@Override
	public Double temperatureMaxError(){
		if(this.datapoint.containsKey("temperatureMaxError"))
			return asDouble(this.datapoint.get("temperatureMaxError"));
		else
			return null;
	}

	/**
	 * The time at which the maximum temperature is expected to occur.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return An human-readable time string formated as [HH:mm:ss]  Returns null if the field is not defined.
	 */
	@Override
	public String temperatureMaxTime(){
		if(this.datapoint.containsKey("temperatureMaxTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("temperatureMaxTime"))) * 1000 );
			return time;
		}
		else
			return null;
	}
	
	/**
	 * The apparent temperature at the given time using the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the apparentTemperature. Returns null if the field is not defined.
	 */
	@Override
	public Double apparentTemperature(){
		if(this.datapoint.containsKey("apparentTemperature"))
			return asDouble(this.datapoint.get("apparentTemperature"));
		else
			return null;
	}

	/**
	 * The minimum apparent temperature expected for a given day the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the minimum apparent temperature. Returns null if the field is not defined.
	 */
	@Override
	public Double apparentTemperatureMin(){
		if(this.datapoint.containsKey("apparentTemperatureMin"))
			return asDouble(this.datapoint.get("apparentTemperatureMin"));
		else
			return null;
	}

	/**
	 * The time at with the minimum apparent temperature is expected to occur.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return An human-readable time string formated as [HH:mm:ss]  Returns null if the field is not defined.
	 */
	@Override
	public String apparentTemperatureMinTime(){
		if(this.datapoint.containsKey("temperatureMinTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("apparentTemperatureMinTime"))) * 1000 );
			return time;
		}
		else
			return null;
	}

	/**
	 * The maximum apparent temperature expected for a given day the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the apparent maximum temperature. Returns null if the field is not defined.
	 */
	@Override
	public Double apparentTemperatureMax(){
		if(this.datapoint.containsKey("apparentTemperatureMax"))
			return asDouble(this.datapoint.get("apparentTemperatureMax"));
		else
			return null;
	}

	/**
	 * The time at which the apparent maximum temperature is expected to occur.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return An human-readable time string formated as [HH:mm:ss]  Returns null if the field is not defined.
	 */
	@Override
	public String apparentTemperatureMaxTime(){
		if(this.datapoint.containsKey("apparentTemperatureMaxTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("apparentTemperatureMaxTime"))) * 1000 );
			return time;
		}
		else
			return null;
	}

	/**
	 * The dew point for a given time with the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the dew point. Returns -1 if the field is not defined.
	 */
	@Override
	public Double dewPoint(){
		if(this.datapoint.containsKey("dewPoint"))
			return asDouble(this.datapoint.get("dewPoint"));
		else
			return null;
	}
	
	/**
	 * The dew point error for a given time with the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the dew point. Returns -1 if the field is not defined.
	 */
	@Override
	public Double dewPointError(){
		if(this.datapoint.containsKey("dewPointError"))
			return asDouble(this.datapoint.get("dewPointError"));
		else
			return null;
	}

	/**
	 * The wind speed for a given time with the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the wind speed. Returns -1 if the field is not defined.
	 */
	@Override
	public Double windSpeed(){
		if(this.datapoint.containsKey("windSpeed"))
			return asDouble(this.datapoint.get("windSpeed"));
		else
			return null;
	}
	
	/**
	 * The wind speed error for a given time with the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the wind speed. Returns -1 if the field is not defined.
	 */
	@Override
	public Double windSpeedError(){
		if(this.datapoint.containsKey("windSpeedError"))
			return asDouble(this.datapoint.get("windSpeedError"));
		else
			return null;
	}

	/**
	 * The wind direction for a given time in degrees.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the wind direction. Returns -1 if the field is not defined.
	 */
	@Override
	public Double windBearing(){
		if(this.datapoint.containsKey("windBearing"))
			return asDouble(this.datapoint.get("windBearing"));
		else
			return null;
	}
	
	/**
	 * The wind direction error for a given time in degrees.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the wind direction. Returns -1 if the field is not defined.
	 */
	@Override
	public Double windBearingError(){
		if(this.datapoint.containsKey("windBearingError"))
			return asDouble(this.datapoint.get("windBearingError"));
		else
			return null;
	}

	/**
	 * A numerical value between 0 and 1 (inclusive) representing 
	 * the percentage of sky occluded by clouds. 
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the cloud cover. Returns -1 if the field is not defined.
	 */
	@Override
	public Double cloudCover(){
		if(this.datapoint.containsKey("cloudCover"))
			return asDouble(this.datapoint.get("cloudCover"));
		else
			return null;
	}
	
	/**
	 * A numerical the percentage of sky occluded by clouds error. 
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the cloud cover. Returns -1 if the field is not defined.
	 */
	@Override
	public Double cloudCoverError(){
		if(this.datapoint.containsKey("cloudCoverError"))
			return asDouble(this.datapoint.get("cloudCoverError"));
		else
			return null;
	}

	/**
	 * A numerical value representing the relative humidity error.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the humidity. Returns -1 if the field is not defined.
	 */
	@Override
	public Double humidity(){
		if(this.datapoint.containsKey("humidity"))
			return asDouble(this.datapoint.get("humidity"));
		else
			return null;
	}
	
	/**
	 * A numerical value between 0 and 1 (inclusive) 
	 * representing the relative humidity.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the humidity. Returns -1 if the field is not defined.
	 */
	@Override
	public Double humidityError(){
		if(this.datapoint.containsKey("humidityError"))
			return asDouble(this.datapoint.get("humidityError"));
		else
			return null;
	}

	/**
	 * A numerical value with the sea level pressure with the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the pressure. Returns -1 if the field is not defined.
	 */
	@Override
	public Double pressure(){
		if(this.datapoint.containsKey("pressure"))
			return asDouble(this.datapoint.get("pressure"));
		else
			return null;
	}
	
	/**
	 * A numerical value with the sea level pressure error with the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the pressure. Returns -1 if the field is not defined.
	 */
	@Override
	public Double pressureError(){
		if(this.datapoint.containsKey("pressureError"))
			return asDouble(this.datapoint.get("pressureError"));
		else
			return null;
	}

	/**
	 * A numerical value with the average visibility with the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the visibility. Returns null if the field is not defined.
	 */
	@Override
	public Double visibility(){
		if(this.datapoint.containsKey("visibility"))
			return asDouble(this.datapoint.get("visibility"));
		else
			return null;
	}
	
	/**
	 * A numerical value with the visibility error with the units defined
	 * in the class DarkSky.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the visibility. Returns null if the field is not defined.
	 */
	@Override
	public Double visibilityError(){
		if(this.datapoint.containsKey("visibilityError"))
			return asDouble(this.datapoint.get("visibilityError"));
		else
			return null;
	}

	/**
	 * A numerical value  representing the columnar density of total atmospheric
	 * ozone at the given time in Dobson units.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the ozone. Returns -1 if the field is not defined.
	 */
	@Override
	public Double ozone(){

		if(this.datapoint.containsKey("ozone"))
			return asDouble(this.datapoint.get("ozone"));
		else
			return null;
	}
	
	/**
	 * Nesrest Storm Bearing
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the ozone. Returns -1 if the field is not defined.
	 */
	@Override
	public Double nearestStormBearing(){

		if(this.datapoint.containsKey("nearestStormBearing"))
			return asDouble(this.datapoint.get("nearestStormBearing"));
		else
			return null;
	}
	
	/**
	 * Nesrest Storm Distance
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number with the ozone. Returns -1 if the field is not defined.
	 */
	@Override
	public Double nearestStormDistance(){

		if(this.datapoint.containsKey("nearestStormDistance"))
			return asDouble(this.datapoint.get("nearestStormDistance"));
		else
			return null;
	}

	/**
	 * Percentage complete of the current lunar month
	 * For more information refer to the API Docs:
	 * <a href="https://developer.darksky.net">https://developer.darksky.net</a>
	 * @return A Double number representing the fractional part of the lunation number of the given day.
	 * Returns null if the field is not defined.
	 */
	@Override
	public Double moonPhase() {
		if (this.datapoint.containsKey("moonPhase"))
			return asDouble(this.datapoint.get("moonPhase"));
		else
			return null;
	}

	private Double asDouble(Object obj){
		return Double.parseDouble( String.valueOf(obj) );
	}

	private String asString(Object obj){
		return String.valueOf(obj);
	}

}//public class - end

