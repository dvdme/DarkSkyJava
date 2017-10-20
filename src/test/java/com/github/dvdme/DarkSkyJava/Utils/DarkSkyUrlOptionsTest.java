package com.github.dvdme.DarkSkyJava.Utils;

import com.github.dvdme.DarkSkyJava.DarkSkyLanguages;
import com.github.dvdme.DarkSkyJava.DarkSkyUnits;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DarkSkyUrlOptionsTest {

    private static final String OSENV = "00000000000000000000000000000042";
    private static final String  APIKEY_OSENV = "DARKSKY_APIKEY";
    private static final String baseUrlURL = "https://api.darksky.net/forecast/";

    private String lat = "38.1";
    private String lon = "-9.0";
    private String time = "2013-05-06T12:00:00-0400";
    private String units = DarkSkyUnits.SI.toString();
    private String lang = DarkSkyLanguages.PIG_LATIN.toString();
    private DarkSkyUrlOptions options;

    @Before
    public void setUp() throws Exception {
        options = new DarkSkyUrlOptions(OSENV, lat, lon)
                .setLangURL(lang)
                .setUnitsURL(units)
                .setTimeURL(time);
    }

    @After
    public void tearDown() throws Exception {
        // Do Nothing
    }

    @Test
    public void getBaseUrlURL() throws Exception {
        assertEquals(baseUrlURL, options.getBaseUrlURL());
    }

    @Test
    public void getDarkSkyApiKey() throws Exception {
        assertEquals(OSENV, options.getDarkSkyApiKey());
    }

    @Test
    public void getLatitude() throws Exception {
        assertEquals(lat, options.getLatitude());
    }

    @Test
    public void setLatitude() throws Exception {
        String c = "28.9";
        options.setLatitude(c);
        assertEquals(c, options.getLatitude());
    }

    @Test
    public void getLongitude() throws Exception {
        assertEquals(lon, options.getLongitude());
    }

    @Test
    public void setLongitude() throws Exception {
        String c = "-8.5";
        options.setLongitude(c);
        assertEquals(c, options.getLongitude());
    }

    @Test
    public void getUnitsURL() throws Exception {
        assertEquals(units, options.getUnitsURL());
    }

    @Test
    public void setUnitsURL() throws Exception {
        String u = DarkSkyUnits.SI.toString();
        options.setUnitsURL(u);
        assertEquals(u, options.getUnitsURL());
    }

    @Test
    public void getTimeURL() throws Exception {
        assertEquals(time, options.getTimeURL());
    }

    @Test
    public void setTimeURL() throws Exception {
        String time = "2017-07-06T19:20:02-0000";
        options.setTimeURL(time);
        assertEquals(time, options.getTimeURL());
    }

    @Test
    public void clearExclude() throws Exception {
        options.setExcludeAlerts().setExcludeCurrently();
        options.clearExclude();
        assertEquals(null, options.getExcludeURL());
    }

    @Test
    public void setExcludeCurrently() throws Exception {
        options.setExcludeCurrently();
        assertEquals("[currently]", options.getExcludeURL());
    }

    @Test
    public void setExcludeMinutely() throws Exception {
        options.setExcludeMinutely();
        assertEquals("[minutely]", options.getExcludeURL());
    }

    @Test
    public void setExcludeHourly() throws Exception {
        options.setExcludeHourly();
        assertEquals("[hourly]", options.getExcludeURL());
    }

    @Test
    public void setExcludeDaily() throws Exception {
        options.setExcludeDaily();
        assertEquals("[daily]", options.getExcludeURL());
    }

    @Test
    public void setExcludeAlerts() throws Exception {
        options.setExcludeAlerts();
        assertEquals("[alerts]", options.getExcludeURL());
    }

    @Test
    public void setExcludeFlags() throws Exception {
        options.setExcludeFlags();
        assertEquals("[flags]", options.getExcludeURL());
    }

    @Test
    public void setExcludeMultiple() throws Exception {
        options.setExcludeFlags().setExcludeCurrently().setExcludeHourly();
        String excluded = options.getExcludeURL();
        assertTrue(excluded.contains("flags"));
        assertTrue(excluded.contains("currently"));
        assertTrue(excluded.contains("hourly"));
        assertTrue(excluded.contains(","));
        assertFalse(excluded.endsWith(",]"));
    }

    @Test
    public void getLangURL() throws Exception {
        assertEquals(lang, options.getLangURL());
    }

    @Test
    public void setLangURL() throws Exception {
        String lang = DarkSkyLanguages.PORTUGUESE.toString();
        options.setLangURL(lang);
        assertEquals(lang, options.getLangURL());
    }

    @Test
    public void isExtend() throws Exception {
        assertFalse(options.isExtend());
    }

    @Test
    public void setExtend() throws Exception {
        options.setExtend(true);
        assertTrue(options.isExtend());
    }

}