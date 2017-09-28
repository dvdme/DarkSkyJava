package com.github.dvdme.DarkSkyJava.Utils;

import com.github.dvdme.DarkSkyJava.DarkSkyLanguages;
import com.github.dvdme.DarkSkyJava.DarkSkyUnits;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DarkSkyUrlOptions {

    private static final String baseUrlURL = "https://api.darksky.net/forecast/";
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    private String latitude;
    private String longitude;
    private String darkSkyApiKey;
    private String unitsURL;
    private String timeURL;
    private String excludeURL;
    private String langURL;
    private boolean extend;

    public DarkSkyUrlOptions(String apiKey, String latitude, String longitude) throws IllegalArgumentException {

        if (apiKey.length()==32) {
            this.darkSkyApiKey = apiKey;
            this.latitude = latitude;
            this.longitude = longitude;
            this.timeURL = null;
            this.excludeURL = null;
            this.extend = false;
            this.unitsURL = DarkSkyUnits.AUTO.toString();
            this.langURL = DarkSkyLanguages.ENGLISH.toString();
        }
        else {
            throw new IllegalArgumentException("The API Key doesn't seam to be valid because it does not have 32 characters.");
        }

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

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUnitsURL() {
        return unitsURL;
    }

    public void setUnitsURL(String unitsURL) {
        this.unitsURL = unitsURL;
    }

    public String getTimeURL() {
        return timeURL;
    }

    public void setTimeURL(String timeURL) {
        this.timeURL = timeURL;
    }

    public String getExcludeURL() {
        return excludeURL;
    }

    public void setExcludeURL(String excludeURL) {
        this.excludeURL = excludeURL;
    }

    public String getLangURL() {
        return langURL;
    }

    public void setLangURL(String langURL) {
        this.langURL = langURL;
    }

    public boolean isExtend() {
        return extend;
    }

    public void setExtend(boolean extend) {
        this.extend = extend;
    }
}
