package jcarbon.emissions; import java.util.Locale; public enum CarbonLocale {
ABW("Aruba", 542.819553),
AFG("Afghanistan", 108.658296),
AGO("Angola", 175.608942);
private final String locale; private final double intensity;
CarbonLocale(String locale, double intensity){ this.locale = locale; this.intensity = intensity; }
 public String getCountryName(){ return new String(locale); }
public double getLocaleIntensity(){ return Double.valueOf(intensity); }
public static CarbonLocale fromLocale(Locale localeCountry) { return CarbonLocale.valueOf(localeCountry.getISO3Country()); } 
public static CarbonLocale getDefault() { return CarbonLocale.fromLocale(Locale.getDefault()); }
}
