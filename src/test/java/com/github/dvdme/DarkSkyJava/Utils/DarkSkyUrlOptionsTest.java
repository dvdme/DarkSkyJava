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
    private DarkSkyUrlOptions options;

    @Before
    public void setUp() throws Exception {
        options = new DarkSkyUrlOptions(OSENV, lat, lon)
                .setLangURL(DarkSkyLanguages.PIG_LATIN.toString())
                .setUnitsURL(DarkSkyUnits.SI.toString());
    }

    @After
    public void tearDown() throws Exception {
        // Do Nothing
    }

    @Test
    public void getBaseUrlURL() throws Exception {
        assertEquals(baseUrlURL, "");//options.getBaseUrlURL());
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
        String units = DarkSkyUnits.SI.toString();
        assertEquals(units, options.getUnitsURL());
    }

    @Test
    public void setUnitsURL() throws Exception {
        String units = DarkSkyUnits.AUTO.toString();
        options.setUnitsURL(units);
        assertEquals(units, options.getUnitsURL());
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
        String lang = DarkSkyLanguages.PIG_LATIN.toString();
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
    }

    @Test
    public void setExtend() throws Exception {
    }

}