package com.github.dvdme.DarkSkyJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.dvdme.DarkSkyJava.Utils.DarkSkyUrl;
import com.github.dvdme.DarkSkyJava.Utils.DarkSkyUrlOptions;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


public class DarkSky {

/*
	private static final String ForecastIOURL = "https://api.darksky.net/forecast/";
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	private String ForecastIOApiKey = "";
	private String unitsURL;
	private String timeURL;
	private String excludeURL;
	private String langURL;
	private boolean extend;*/
    private int connectTimeout = 30000;
    private int readTimeout = 30000;

	private String Cache_Control;
	private String Expires;
	private String X_Forecast_API_Calls;
	private String X_Response_Time;
	
	private String rawResponse;
	
	private Proxy proxy_to_use;
	
	private JsonObject forecast;

	private JsonObject currently;
	private JsonObject minutely;
	private JsonObject hourly;
	private JsonObject daily;
	private JsonObject flags;
	private JsonArray alerts;

	private DarkSkyWeatherResponse weatherResponse;
	private DarkSkyUrl darkSkyUrl;



    private JsonObject jsonResponse;

    public DarkSky(DarkSkyUrl darkSkyUrl){
	    this.darkSkyUrl = darkSkyUrl;
    }

    public DarkSky(String apiKey, String latitude, String longitude){
        this.darkSkyUrl = new DarkSkyUrl(new DarkSkyUrlOptions(apiKey, latitude, longitude));
    }

/*	public DarkSky(String API_KEY){

		if (API_KEY.length()==32) {
			//this.ForecastIOApiKey = API_KEY;
			this.forecast = new JsonObject();
			this.currently = new JsonObject();
			this.minutely = new JsonObject();
			this.hourly = new JsonObject();
			this.daily = new JsonObject();
			this.flags = new JsonObject();
			this.alerts = new JsonArray();
			//this.timeURL = null;
			//this.excludeURL = null;
			//this.extend = false;
			//this.unitsURL = DarkSkyUnits.AUTO.toString();
			//this.langURL = DarkSkyLanguages.ENGLISH.toString();
			this.proxy_to_use = null;
			
		}
		else {
			System.err.println("The API Key doesn't seam to be valid.");
		}
	}//construtor - end

	public DarkSky(String LATITUDE, String LONGITUDE, String API_KEY){

		if (API_KEY.length()==32) {
			//this.ForecastIOApiKey = API_KEY;
			this.forecast = new JsonObject();
			this.currently = new JsonObject();
			this.minutely = new JsonObject();
			this.hourly = new JsonObject();
			this.daily = new JsonObject();
			this.flags = new JsonObject();
			this.alerts = new JsonArray();
			//this.timeURL = null;
			//this.excludeURL = null;
			//this.extend = false;
			//this.unitsURL = DarkSkyUnits.AUTO.toString();
			//this.langURL = DarkSkyLanguages.ENGLISH.toString();
			this.proxy_to_use = null;

			getForecast(LATITUDE, LONGITUDE);
		}
		else {
			System.err.println("The API Key doesn't seam to be valid.");
		}

	}//construtor - end

	public DarkSky(String LATITUDE, String LONGITUDE, String PROXYNAME, int PROXYPORT, String API_KEY){

		if (API_KEY.length()==32) {
			//this.ForecastIOApiKey = API_KEY;
			this.forecast = new JsonObject();
			this.currently = new JsonObject();
			this.minutely = new JsonObject();
			this.hourly = new JsonObject();
			this.daily = new JsonObject();
			this.flags = new JsonObject();
			this.alerts = new JsonArray();
			//this.timeURL = null;
			//this.excludeURL = null;
			//this.extend = false;
			//this.unitsURL = DarkSkyUnits.AUTO.toString();
			//this.langURL = DarkSkyLanguages.ENGLISH.toString();
			this.proxy_to_use = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXYNAME, PROXYPORT));

			getForecast(LATITUDE, LONGITUDE);
		}
		else {
			System.err.println("The API Key doesn't seam to be valid.");
		}

	}//construtor - end
	
	public DarkSky(String LATITUDE, String LONGITUDE, String UNITS, String LANG, String API_KEY){

		if (API_KEY.length()==32) {
			//this.ForecastIOApiKey = API_KEY;
			this.forecast = new JsonObject();
			this.currently = new JsonObject();
			this.minutely = new JsonObject();
			this.hourly = new JsonObject();
			this.daily = new JsonObject();
			this.flags = new JsonObject();
			this.alerts = new JsonArray();
			//this.timeURL = null;
			//this.excludeURL = null;
			//this.extend = false;
			this.proxy_to_use = null;
			this.setUnits(UNITS);
			this.setLang(LANG);
			getForecast(LATITUDE, LONGITUDE);
		} else {
			System.err.println("The API Key doesn't seam to be valid.");
		}

	}//construtor - end
	
	public DarkSky(String LATITUDE, String LONGITUDE, String UNITS, String LANG, String PROXYNAME, int PROXYPORT, String API_KEY){

		if (API_KEY.length()==32) {
			//this.ForecastIOApiKey = API_KEY;
			this.forecast = new JsonObject();
			this.currently = new JsonObject();
			this.minutely = new JsonObject();
			this.hourly = new JsonObject();
			this.daily = new JsonObject();
			this.flags = new JsonObject();
			this.alerts = new JsonArray();
			//this.timeURL = null;
			//this.excludeURL = null;
			//this.extend = false;
			this.proxy_to_use = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXYNAME, PROXYPORT));
			this.setUnits(UNITS);
			this.setLang(LANG);
			getForecast(LATITUDE, LONGITUDE);
		} else {
			System.err.println("The API Key doesn't seam to be valid.");
		}

	}//construtor - end*/

    public DarkSkyWeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void fetch() {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(darkSkyUrl.getDarkSkyUrl());

            request.addHeader("Accept-Encoding", "gzip, deflate");
            HttpResponse response = client.execute(request);

            //System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            fetch(result.toString());
        }
        catch (Exception ex){
        	System.err.println(ex);
		}
    }

	public void fetch(String jsonResponse) {
		try {

			this.rawResponse = jsonResponse;
			this.jsonResponse = Json.parse(this.rawResponse).asObject();

			this.weatherResponse = new DarkSkyWeatherResponse(this);
		}
		catch (Exception ex){
			System.err.println(ex);
		}
	}


	/**
	 * Returns the latitude that is setted for the request.
	 * @return A Double number with the latitude.
	 */
	public Double getLatitude(){
		return this.forecast.get("latitude").asDouble();
	}

	/**
	 * Returns the longitude that is setted for the request.
	 * @return A Double number with the longitude.
	 */
	public Double getLongitude(){
		return this.forecast.get("longitude").asDouble();
	}

	/**
	 * Returns the timezone that is setted.
	 * @return A String with the timezone.
	 */
	public String getTimezone(){
		return this.forecast.get("timezone").asString();
	}




	
	/**
	 * Sets the http-proxy to use.
	 * @param PROXYNAME hostname or ip of the proxy to use (e.g. "127.0.0.1"). If proxyname equals null, no proxy will be used.
	 * @param PROXYPORT port of the proxy to use (e.g. 8080)
	 */
	public void setHTTPProxy(String PROXYNAME, int PROXYPORT) {
		if (PROXYNAME == null) {
			this.proxy_to_use = null;
		}
		else {
			this.proxy_to_use = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXYNAME, PROXYPORT));
		}
	}


    public JsonObject getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(JsonObject jsonResponse) {
        this.jsonResponse = jsonResponse;
    }


    /**
     * @return the connection timeout in milliseconds.
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Sets the connection timeout in milliseconds.
     * @param connectTimeout timeout in milliseonds.
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * @return the read timeout in milliseconds.
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Sets the read timeout in milliseconds.
     * @param readTimeout timeout in milliseonds.
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    /**
	 * Returns the timezone offset in an double
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return double with the offset
	 */
	public double offsetValue(){
		return this.forecast.get("offset").asDouble();
	}

	/**
	 * Returns the offset as a String with '+' or '-' sign. 
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return String with the offset
	 */
	public String offset(){
		if(this.forecast.get("offset").asDouble()<0)
			return ""+this.forecast.get("offset").asDouble();
		else if(this.forecast.get("offset").asDouble()>0)
			return ""+"+"+this.forecast.get("offset").asDouble();
		else
			return "";
	}




	/**
	 * Returns the currently data point
	 * @return JsonObject with the data point
	 */
	public JsonObject getCurrently(){
		return this.currently;
	}

	/**
	 * Returns the minutely data block
	 * @return JsonObject with the data block
	 */
	public JsonObject getMinutely(){
		return this.minutely;
	}

	/**
	 * Returns the hourly data block
	 * @return JsonObject with the data block
	 */
	public JsonObject getHourly(){
		return this.hourly;
	}

	/**
	 * Returns the flags data 
	 * @return JsonObject with the data
	 */
	public JsonObject getFlags(){
		return this.flags;
	}

	/**
	 * Returns the alerts data 
	 * @return JsonObject with the data
	 */
	public JsonArray getAlerts(){
		return this.alerts;
	}

	/**
	 * Returns the daily data block
	 * @return JsonObject with the data block
	 */
	public JsonObject getDaily(){
		return this.daily;
	}

	/**
	 * Checks if there is any currently data available
	 * @return true or false
	 */
	public boolean hasCurrently(){
		if(this.currently == null)
			return false;
		else
			return true;
	}

	/**
	 * Checks if there is any minutely data available
	 * @return true or false
	 */
	public boolean hasMinutely(){
		if(this.minutely == null)
			return false;
		else
			return true;
	}

	/**
	 * Checks if there is any hourly data available
	 * @return true or false
	 */
	public boolean hasHourly(){
		if(this.hourly == null)
			return false;
		else
			return true;
	}

	/**
	 * Checks if there is any daily data available
	 * @return true or false
	 */
	public boolean hasDaily(){
		if(this.daily == null)
			return false;
		else
			return true;
	}

	/**
	 * Checks if there is any flags data available
	 * @return true or false
	 */
	public boolean hasFlags(){
		if(this.flags == null)
			return false;
		else
			return true;
	}

	/**
	 * Checks if there is any flags data available
	 * @return true or false
	 */
	public boolean hasAlerts(){
		if(this.alerts == null)
			return false;
		else
			return true;
	}



	/**
	 * Does another query to the API and updates the data
	 * This only updates the data in DarkSky class
	 * @return True if successful
	 */
	public boolean update(){
		boolean b = getForecast(String.valueOf(getLatitude()), String.valueOf(getLongitude()));
		return b;
	}

	/**
	 * Gets the forecast reports for the given coordinates with the set options
	 * @param LATITUDE the geographical latitude
	 * @param LONGITUDE the geographical longitude
	 * @return True if successful 
	 */
	public boolean getForecast(String LATITUDE, String LONGITUDE) {


		try {
			String reply = "";//httpGET( urlBuilder(LATITUDE, LONGITUDE) );
			if(reply == null)
				return false;
			this.forecast = Json.parse(reply).asObject();
			//this.forecast = JsonObject.readFrom(reply);
		} catch (NullPointerException e) {
			System.err.println("Unable to connect to the API: "+e.getMessage());
			return false;
		}

		return getForecast(this.forecast);


	}//getForecast - end

	/*
	 * This change was suggested and made by github user brobzilla to add
	 * the ability to use an external http library. I found this to be a
	 * nice suggestion and improvement. However, because http libraries 
	 * usually return the raw string response, I find that it would be
	 * useful to add a getForecast method that receives the response
	 * String as parameter.   
	 */

	/**
	 * Parses the forecast reports for the given coordinates with the set options
	 * Useful to use with an external http library
	 * @param http_response String
	 * @return boolean
	 */

	public boolean getForecast(String http_response) {
		this.forecast = Json.parse(http_response).asObject();
		//this.forecast = JsonObject.readFrom(http_response);
		return getForecast(this.forecast);
	}

	/**
	 * Parses the forecast reports for the given coordinates with the setted options
	 * Useful to use with an external http library
	 * Hint: The getForecast(String http_response) could be more useful since it receives 
	 *       the raw response String instead of the JsonObect. 
	 * @param forecast JsonObject
	 * @return true if successful
	 */

	public boolean getForecast(JsonObject forecast) {
		this.forecast = forecast;
		try {
			this.currently = forecast.get("currently").asObject();
		} catch (NullPointerException e) {
			this.currently = null;
		}
		try {
			this.minutely = forecast.get("minutely").asObject();
		} catch (NullPointerException e) {
			this.minutely = null;
		}
		try {
			this.hourly = forecast.get("hourly").asObject();
		} catch (NullPointerException e) {
			this.hourly = null;
		}
		try {
			this.daily = forecast.get("daily").asObject();
		} catch (NullPointerException e) {
			this.daily = null;
		}
		try {
			this.flags = forecast.get("flags").asObject();
		} catch (NullPointerException e) {
			this.flags = null;
		}
		try {
			this.alerts = forecast.get("alerts").asArray();
		} catch (NullPointerException e) {
			this.alerts = null;
		}

		return true;
	}//getForecast - end

	/**
	 * Returns the Cache-Control response header value
	 * @return the string with the header value
	 */
	public String getHeaderCache_Control() {
		return Cache_Control;
	}

	/**
	 * Returns the Expires response header value
	 * @return the string with the header value
	 */
	public String getHeaderExpires() {
		return Expires;
	}

	/**
	 * Returns the X-Forecast-API-Calls response header value<br>
	 * This is the number os API calls made today from one given API Key.
	 * @return the string with the header value
	 */
	public String getHeaderX_Forecast_API_Calls() {
		return X_Forecast_API_Calls;
	}

	/**
	 * Returns the X-Response-Time response header value
	 * @return the string with the header value
	 */
	public String getHeaderX_Response_Time() {
		return X_Response_Time;
	}
	
	/**
	 * Returns the raw JSON response
	 * @return the string with the JSON response
	 */
	public String getRawResponse() {
		return rawResponse;
	}

	private String httpGET(String requestURL) {

		//Variables
		URL request = null;
		HttpURLConnection connection = null;
		//Scanner scanner = null;
		BufferedReader reader = null;
		String s = "";
		String response = "";

		try {
			request = new URL(requestURL);
			// check, if a proxy was defined, if so, use it for the connection
			if (this.proxy_to_use != null) {
				connection = (HttpURLConnection) request.openConnection(this.proxy_to_use);
			}
			else {
				connection = (HttpURLConnection) request.openConnection();
			}
			
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
			connection.connect();

			Cache_Control = connection.getHeaderField("Cache-Control");
			Expires = connection.getHeaderField("Expires");
			X_Forecast_API_Calls = connection.getHeaderField("X-Forecast-API-Calls");
			X_Response_Time = connection.getHeaderField("X-Response-Time");

			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                //obtain the encoding returned by the server
                String encoding = connection.getContentEncoding();

				try {
                    //create the appropriate stream wrapper based on the encoding type
					//use UTF-8 when parsing the JSON responses

                    if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
						reader = new BufferedReader(new InputStreamReader( new GZIPInputStream( connection.getInputStream() ), "UTF-8"));
					} else if (encoding != null && encoding.equalsIgnoreCase("deflate")) {
                        reader = new BufferedReader(new InputStreamReader( new InflaterInputStream( connection.getInputStream(), new Inflater(true) ), "UTF-8"));
                    } else {
						reader = new BufferedReader(new InputStreamReader( connection.getInputStream(),"UTF-8" ));
					}

                    while( (s = reader.readLine()) != null )
                        response = s;

				} catch (IOException e){
					System.err.println("Error: "+e.getMessage());
				} finally {
					if (reader != null) {
						try {
							reader.close();
							reader = null;
						} catch (IOException e) {
							System.err.println("Error: "+e.getMessage());
						}
					}
				}

			} //if HTTP_OK - End
			// else if HttpURLConnection Not Ok
			else {

				try {
					reader = new BufferedReader(new InputStreamReader( connection.getErrorStream() ));
					while( (s = reader.readLine()) != null )
						response = s;
				} catch (IOException e){
					System.err.println("Error: "+e.getMessage());
				} finally {
					if (reader != null) {
						try {
							reader.close();
							reader = null;
						} catch (IOException e) {
							System.err.println("Error: "+e.getMessage());
						}
					}
				}
				//If response is not ok print error and return null
				System.err.println("Bad Response: " + response + "\n");
				return null;

			} //else if HttpURLConnection Not Ok - End
		} catch (IOException e) {
			System.err.println("Error: "+e.getMessage());		
			response = null;
		} finally {		
			connection.disconnect();
		}
		
		rawResponse = response;
		return response;
	}//httpGET - end

}//public class - end

