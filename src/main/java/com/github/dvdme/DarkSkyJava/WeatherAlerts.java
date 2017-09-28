package com.github.dvdme.DarkSkyJava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.eclipsesource.json.JsonValue;
import com.github.dvdme.DarkSkyJava.DarkSky;

public class WeatherAlerts {
	
	private Alert [] alert;
	private String timezone;
	private int numberofalerts;

	public WeatherAlerts(DarkSky darkSky) {
		
		if(darkSky.hasAlerts()){
			alert = new Alert[darkSky.getAlerts().size()];
			timezone = darkSky.getTimezone();
			numberofalerts = darkSky.getAlerts().size();
			init(darkSky);
		}
		
		
	}
	
	private void init(DarkSky darkSky){

		JsonValue jsonvalue;
		
		if(darkSky.hasAlerts()){
			
			for(int i=0; i < NumberOfAlerts(); i++ ){
				alert[i] = new Alert();
				jsonvalue = darkSky.getAlerts().get(i);
				alert[i].setTitle( jsonvalue.asObject().get("title").asString() );
				alert[i].setTime( jsonvalue.asObject().get("time").asLong() );
				alert[i].setExpire( jsonvalue.asObject().get("expires").asLong() );
				alert[i].setDescription( jsonvalue.asObject().get("description").asString() );
				alert[i].setUri( jsonvalue.asObject().get("uri").asString() );
			}
		
		}
	}
	
	
	/**
	 * Returns the number os alerts in the reply.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return int which is the number os alerts
	 */
	public int NumberOfAlerts() {
		return numberofalerts;
	}
	
	/**
	 * Returns the title for the alert. Returns null if index doesn't exist.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param index index to get
	 * @return the title for the alert. Null if index doesn't exist.
	 */
	public String getAlertTitle(int index){
		if(index < 0 || index >= alert.length )
			return null;
		else
			return alert[index].getTitle();
	}
	
	/**
	 * Returns the time for the alert. Returns null if index doesn't exist.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param index index to get
	 * @return the time for the alert. Null if index doesn't exist.
	 */
	public String getAlertTime(int index){
		if(index < 0 || index >= alert.length )
			return null;
		else {
			String out = "";
			DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			dfm.setTimeZone(TimeZone.getTimeZone(timezone));
			out = dfm.format( Long.parseLong(String.valueOf( alert[index].getTime() * 1000 )));
			return out;
		}
	}
	
	/**
	 * Returns the expiration time for the alert. Returns null if index doesn't exist.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param index index to get
	 * @return the expiration time for the alert. Null if index doesn't exist.
	 */
	public String getAlertExpireTime(int index){
		if(index < 0 || index >= alert.length )
			return null;
		else {
			String out = "";
			DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			dfm.setTimeZone(TimeZone.getTimeZone(timezone));
			out = dfm.format( Long.parseLong(String.valueOf( alert[index].getExpire() * 1000 )));
			return out;
		}
	}
	
	/**
	 * Returns the description for the alert. Returns null if index doesn't exist.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param index index to get
	 * @return the description for the alert. Null if index doesn't exist.
	 */
	public String getAlertDescription(int index){
		if(index < 0 || index >= alert.length )
			return null;
		else
			return alert[index].getDescription();
	}
	
	/**
	 * Returns the uri for the alert. Returns null if index doesn't exist.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param index index to get
	 * @return the uri for the alert. Null if index doesn't exist.
	 */
	public String getAlertURI(int index){
		if(index < 0 || index >= alert.length )
			return null;
		else
			return alert[index].getUri();
	}
	
	/**
	 * Returns a string with all the alert information. Returns null if index doesn't exist.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param index index to get
	 * @return string with all the alert information. Null if index doesn't exist.
	 */
	public String getAlert(int index){
		
		StringBuilder sb = new StringBuilder("");
		
		sb.append("Title: " + getAlertTitle(index));
		sb.append("\n");
		sb.append("Time: " + getAlertTime(index));
		sb.append("\n");
		sb.append("Expires: " + getAlertExpireTime(index));
		sb.append("\n");
		sb.append("Description: " + getAlertDescription(index));
		sb.append("\n");
		sb.append("URI: " + getAlertURI(index));
		sb.append("\n");
		
		return sb.toString();
		
	}

}//public class - end


