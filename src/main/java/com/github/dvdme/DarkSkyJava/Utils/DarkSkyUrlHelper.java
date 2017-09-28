package com.github.dvdme.DarkSkyJava.Utils;

import java.net.URL;

public class DarkSkyUrlHelper {

    private DarkSkyUrlOptions urlOptions;
    private String darkSkyUrl;

    public DarkSkyUrlHelper(DarkSkyUrlOptions urlOptions) throws IllegalArgumentException {
        this.urlOptions = urlOptions;
    }

    public String getDarkSkyUrl() {
        StringBuilder url = new StringBuilder();
        url.append(urlOptions.getBaseUrlURL());
        url.append(urlOptions.getDarkSkyApiKey() + "/");
        url.append(urlOptions.getLatitude().trim() + "," + urlOptions.getLongitude().trim());

        if (urlOptions.getTimeURL() != null) {
            url.append("," + urlOptions.getTimeURL().trim());
        }

        url.append("?units=" + urlOptions.getUnitsURL().trim());
        url.append("&lang=" + urlOptions.getLangURL().trim());

        if (urlOptions.getExcludeURL() != null) {
            url.append("&exclude=" + urlOptions.getExcludeURL().trim());
        }

        if (urlOptions.isExtend()) {
            url.append("&extend=hourly");
        }

        return url.toString();
    }




}
