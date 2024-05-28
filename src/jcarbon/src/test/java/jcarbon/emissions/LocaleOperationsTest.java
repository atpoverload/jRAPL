package jcarbon.emissions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import jcarbon.emissions.CarbonLocale;
import java.util.Locale;

@RunWith(JUnit4.class)
public class LocaleOperationsTest{
    private static final double USA_LOC = 396.875216;
    private static final double TWN_LOC = 532.763179;
    private static final double FRA_LOC = 55.000000;

    @Test
    public void computeLocale1(){
        CarbonLocale result = CarbonLocale.getDefault();
        assertEquals("USA", result.getCountryName());
        assertEquals(USA_LOC, result.getLocaleIntensity(), 0.0);
    }

    @Test
    public void computeLocale2(){
        Locale fr = Locale.FRANCE;
        CarbonLocale result = CarbonLocale.fromLocale(fr);
        assertEquals("France", result.getCountryName());
        assertEquals(FRA_LOC, result.getLocaleIntensity(), 0.0);
    }

    @Test
    public void computeLocale3(){
        Locale fr = Locale.TAIWAN;
        CarbonLocale result = CarbonLocale.fromLocale(fr);
        assertEquals("Taiwan", result.getCountryName());
        assertEquals(TWN_LOC, result.getLocaleIntensity(), 0.0);
    }
}