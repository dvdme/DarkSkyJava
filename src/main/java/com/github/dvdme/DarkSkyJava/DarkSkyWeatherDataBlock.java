package com.github.dvdme.DarkSkyJava;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class DarkSkyWeatherDataBlock implements WeatherDataBlock {

	private String summary;
	private String icon;
	private List<DarkSkyWeatherDataPoint> data;
	private String timezone;

	public DarkSkyWeatherDataBlock(){
		this.summary = "";
		this.icon = "";
		this.data = null;
		this.timezone = "GMT";
	}

	public DarkSkyWeatherDataBlock(JsonObject db){
		this.summary = "";
		this.icon = "";
		this.data = null;
		this.timezone = "GMT";
		update(db);
	}

	private void update(JsonObject db){

		try {
			this.summary = db.get("summary").asString();
		} catch (NullPointerException npe) {
			this.summary = "no data";
		}

		try {
			this.icon = db.get("icon").asString();
		} catch (NullPointerException npl) {
			this.icon = "no data";
		}

		try {
			JsonArray arr;
			if(db.get("data").isArray()){
				arr = db.get("data").asArray();
				this.data = new ArrayList<DarkSkyWeatherDataPoint>();
				for(int i = 0; i < arr.size(); i++){
					this.data.add(new DarkSkyWeatherDataPoint(arr.get(i).asObject()));
				}
			}
			else {
				System.err.println("Not an array. Maybe you're trying to feed \"currently\" to a datablock.");
			}
		} catch (NullPointerException mpe) {
			this.data = null;
		}
	}

	/**
	 * Returns the summary for the given data block
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return String with the summary
	 */

	@Override
    public List<DarkSkyWeatherDataPoint> data(){
		return this.data;
	}

	@Override
    public String summary(){
		return this.summary;
	}

	/**
	 * Returns the icon for the given data block
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return String with the icon
	 */
	@Override
    public String icon(){
		return this.icon;
	}

	/**
	 * Returns a data point from the this data block
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param index the index to get
	 * @return A WeatherDataPoint
	 */
	public WeatherDataPoint datapoint(int index){
		return this.data.get(index);
	}

	/**
	 * Returns the size of this data block which corresponds to the number of data points present
	 * @return integer with the size
	 */
	public int datablockSize(){
		return this.data.size();
	}

	/**
	 * Allows to set the timezone.
	 * If none is set, default is GMT.
	 * @param tz String with the timezone such as "GMT"
	 */
	public void setTimezone(String tz){
		this.timezone = tz;
	}

	/**
	 * Returns the timezone that is setted.
	 * @return A String with the timezone.
	 */
	public String getTimezone(){
		return this.timezone;
	}

}
