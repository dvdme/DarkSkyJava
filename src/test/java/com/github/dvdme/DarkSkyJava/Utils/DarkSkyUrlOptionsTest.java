package com.github.dvdme.DarkSkyJava.Utils;

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
    private DarkSkyUrlOptions options;

    @Before
    public void setUp() throws Exception {
        options = new DarkSkyUrlOptions(OSENV, lat, lon);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getBaseUrlURL() throws Exception {
        assertEquals(baseUrlURL, options.getBaseUrlURL());
    }

    @Test
    public void getDarkSkyApiKey() throws Exception {
    }

    @Test
    public void getLatitude() throws Exception {
    }

    @Test
    public void setLatitude() throws Exception {
    }

    @Test
    public void getLongitude() throws Exception {
    }

    @Test
    public void setLongitude() throws Exception {
    }

    @Test
    public void getUnitsURL() throws Exception {
    }

    @Test
    public void setUnitsURL() throws Exception {
    }

    @Test
    public void getTimeURL() throws Exception {
    }

    @Test
    public void setTimeURL() throws Exception {
    }

    @Test
    public void getExcludeURL() throws Exception {
    }

    @Test
    public void setExcludeURL() throws Exception {
    }

    @Test
    public void getLangURL() throws Exception {
    }

    @Test
    public void setLangURL() throws Exception {
    }

    @Test
    public void isExtend() throws Exception {
    }

    @Test
    public void setExtend() throws Exception {
    }

}