package com.github.dvdme.DarkSkyJava;

import java.util.HashMap;
import java.util.Iterator;

import com.eclipsesource.json.JsonArray;

public class WeatherFlags {

	private HashMap<String, String[]> flags;
	private String units;

	public WeatherFlags(DarkSky darkSky){
		flags = new HashMap<String, String[]>();
		units = "";
		init(darkSky);

	}

	private void init(DarkSky darkSky){

		if(darkSky.hasFlags()){
			if(darkSky.getFlags().names().contains("darksky-unavailable"))
				this.flags.put("darksky-unavailable", toStringArray(darkSky.getFlags().get("darksky-unavailable").asArray()));

			if(darkSky.getFlags().names().contains("darksky-stations"))
				this.flags.put("darksky-stations", toStringArray(darkSky.getFlags().get("darksky-stations").asArray()));

			if(darkSky.getFlags().names().contains("datapoint-stations"))
				this.flags.put("datapoint-stations", toStringArray(darkSky.getFlags().get("datapoint-stations").asArray()));

			if(darkSky.getFlags().names().contains("isd-stations"))
				this.flags.put("isd-stations", toStringArray(darkSky.getFlags().get("isd-stations").asArray()));

			if(darkSky.getFlags().names().contains("lamp-stations"))
				this.flags.put("lamp-stations", toStringArray(darkSky.getFlags().get("lamp-stations").asArray()));

			if(darkSky.getFlags().names().contains("metar-stations"))
				this.flags.put("metar-stations", toStringArray(darkSky.getFlags().get("metar-stations").asArray()));

			//TODO metno-licenses
			if(darkSky.getFlags().names().contains("metno-licenses"))
				this.flags.put("metar-stations", toStringArray(darkSky.getFlags().get("metno-licenses").asArray()));

			if(darkSky.getFlags().names().contains("sources"))
				this.flags.put("sources", toStringArray(darkSky.getFlags().get("sources").asArray()));

			try {
				this.units = darkSky.getFlags().get("units").asString();
			}
			catch (NullPointerException npe) {
				this.units = "no data";
			}
		}
	}

	private String [] toStringArray(JsonArray jsonarray){
		String [] out = new String[jsonarray.size()];
		for(int i=0; i<jsonarray.size(); i++)
			out[i] = jsonarray.get(i).asString();
		return out;
	}

	/**
	 * Returns an array with the available flags in the report.
	 * If the array's lenght is 0, there are none.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A String array with the available flags.
	 */
	public String [] availableFlags(){
		Iterator<String> it = flags.keySet().iterator();
		String [] out = new String[(flags.keySet().size())];
		int i = 0;
		while(it.hasNext()){
			out[i] = it.next();
			i++;
		}
		return out;
	}

	/**
	 * Returns an array with the unavailable darksky stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] darkskyUnavailable(){
		if(flags.containsKey("unavailable"))
			return flags.get("darksky-unavailable");
		else
			return new String[]{"no data"};
	}

	/**
	 * Returns an array with the available darksky stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] darkskyStations(){
		if(flags.containsKey("darksky-stations"))
			return flags.get("darksky-stations");
		else 
			return new String[]{"no data"};
	}

	/**
	 * Returns an array with the data point stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] datapointStations(){
		if(flags.containsKey("datapoint-stations"))
				return flags.get("datapoint-stations");
		else
			return new String[]{"no data"};
	}

	/**
	 * Returns an array with the isd stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] isdStations(){
		if(flags.containsKey("isd-stations"))
			return flags.get("isd-stations");
		else
			return new String[]{"no data"};
	}

	/**
	 * Returns an array with the lamp stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] lampStations(){
		if(flags.containsKey("lamp-stations"))
			return flags.get("lamp-stations");
		else
			return new String[]{"no data"};
	}

	/**
	 * Returns an array with the metar stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] metarStations(){
		if(flags.containsKey("metar-stations"))
			return flags.get("metar-stations");
		else
			return new String[]{"no data"};
	}

	//TODO metno-licenses
	/**
	 * Returns an array with the metno licenses
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] metnoLicenses(){
		if(flags.containsKey("metno-licenses"))
			return flags.get("metno-licenses");
		else
			return new String[]{"no data"};
	}

	/**
	 * Returns an array with the sources
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the sources
	 */
	public String [] sources(){
		if(flags.containsKey("sources"))
			return flags.get("sources");
		else
			return new String[]{"no data"};
	}

	/**
	 * Returns a String with the units in use
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return String with the units
	 */
	public String units(){
		return units;
	}

}
