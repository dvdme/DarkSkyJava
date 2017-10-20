package com.github.dvdme.DarkSkyJava.Utils;

import com.github.dvdme.DarkSkyJava.DarkSkyLanguages;
import com.github.dvdme.DarkSkyJava.DarkSkyUnits;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class DarkSkyUrlOptions {

    private static final String  APIKEY_OSENV = "DARKSKY_APIKEY";
    private static final String baseUrlURL = "https://api.darksky.net/forecast/";
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    private String latitude;
    private String longitude;
    private String darkSkyApiKey;
    private String unitsURL;
    private String timeURL;
    private Set<String> excludeURL;
    private String langURL;
    private boolean extend;

    private String currentlyName = "currently";
    private String minutelyName = "minutely";
    private String hourlyName = "hourly";
    private String dailyName = "daily";
    private String alertsName = "alerts";
    private String flagsName = "flags";

    public DarkSkyUrlOptions(String latitude, String longitude) throws IllegalArgumentException {
        this(getApiKeyFromOsEnv(), latitude, longitude);
    }

    public DarkSkyUrlOptions(String apiKey, String latitude, String longitude) throws IllegalArgumentException {

        if (apiKey.length()==32) {
            this.darkSkyApiKey = apiKey;
            this.latitude = latitude;
            this.longitude = longitude;
            this.timeURL = null;
            this.excludeURL = new HashSet<>();
            this.extend = false;
            this.unitsURL = DarkSkyUnits.AUTO.toString();
            this.langURL = DarkSkyLanguages.ENGLISH.toString();
        }
        else {
            throw new IllegalArgumentException("The API Key doesn't seam to be valid because it does not have 32 characters.");
        }
    }

    private static String getApiKeyFromOsEnv(){
        String apiKey = System.getenv(APIKEY_OSENV);
        if(apiKey == null){
            String message = String.format("Invalid API Key. Failed to read environment variable %s", APIKEY_OSENV);
            throw new IllegalArgumentException(message);
        }
        return apiKey;
    }

    public static String getBaseUrlURL() {
        return baseUrlURL;
    }

    public String getDarkSkyApiKey() {
        return darkSkyApiKey;
    }

    public String getLatitude() {
        return latitude;
    }

    public DarkSkyUrlOptions setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public DarkSkyUrlOptions setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getUnitsURL() {
        return unitsURL;
    }

    public DarkSkyUrlOptions setUnitsURL(String unitsURL) {
        this.unitsURL = unitsURL;
        return this;
    }

    public String getTimeURL() {
        return timeURL;
    }

    public DarkSkyUrlOptions setTimeURL(String timeURL) {
        this.timeURL = timeURL;
        return this;
    }

    public String getExcludeURL() {
        if(excludeURL.isEmpty()){
            return null;
        }else {

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (String excluded : excludeURL) {
                sb.append(String.format("%s,", excluded));
            }
            sb.deleteCharAt(sb.length() - 1).append("]");
            return sb.toString();
        }
    }

    public DarkSkyUrlOptions clearExclude() {
        this.excludeURL.clear();
        return this;
    }

    public DarkSkyUrlOptions setExcludeCurrently() {
        this.excludeURL.add(currentlyName);
        return this;
    }

    public DarkSkyUrlOptions setExcludeMinutely() {
        this.excludeURL.add(minutelyName);
        return this;
    }

    public DarkSkyUrlOptions setExcludeHourly() {
        this.excludeURL.add(hourlyName);
        return this;
    }

    public DarkSkyUrlOptions setExcludeDaily() {
        this.excludeURL.add(dailyName);
        return this;
    }

    public DarkSkyUrlOptions setExcludeAlerts() {
        this.excludeURL.add(alertsName);
        return this;
    }

    public DarkSkyUrlOptions setExcludeFlags() {
        this.excludeURL.add(flagsName);
        return this;
    }

    public String getLangURL() {
        return langURL;
    }

    public DarkSkyUrlOptions setLangURL(String langURL) {
        this.langURL = langURL;
        return this;
    }

    public boolean isExtend() {
        return extend;
    }

    public DarkSkyUrlOptions setExtend(boolean extend) {
        this.extend = extend;
        return this;
    }
}
