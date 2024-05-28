package jcarbon.emissions;

import static java.lang.Double.parseDouble;
import static jcarbon.util.LoggerUtil.getLogger;


import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import jcarbon.util.NativeUtils;

/** A class that generates a CarbonLocale enum reading in a CSV */
public final class CarbonLocaleGenerator{
    private static final String DEFAULT_INTENSITY_FILE = "/emissions/WorldIntensity.csv";
    private static final Logger logger = getLogger();
    private static final ArrayList<String> ENUMS_LIST = getEnumsList();
    private static final ArrayList<String> CARBON_LOCALE_CLASS = generateEnumStructure();
    private static final String CLASS_HEADER = "package jcarbon.emissions; import java.util.Locale; public enum CarbonLocale {";
    private static final String CONSTRUCTOR_BODY = "CarbonLocale(String locale, double intensity){ this.locale = locale; this.intensity = intensity; }";
    private static final String ATTRIBUTES = "private final String locale; private final double intensity;";
    private static final String FROM_LOCALE_HELPER = "public static CarbonLocale fromLocale(Locale localeCountry) { return CarbonLocale.valueOf(localeCountry.getISO3Country()); } ";
    private static final String GET_DEFAULT_HELPER = "public static CarbonLocale getDefault() { return CarbonLocale.fromLocale(Locale.getDefault()); }";
    private static final String GET_NAME_HELPER = " public String getCountryName(){ return new String(locale); }";
    private static final String GET_INTENSITY_HELPER = "public double getLocaleIntensity(){ return Double.valueOf(intensity); }";

    private static ArrayList<String> getEnumsList(){
        String filePath = System.getProperty("jcarbon.emissions.intensity");
        if (filePath == null) {
            return getDefaultEnums();
        }

        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            logger.info(String.format("locale carbon intensity file %s could not be found", filePath));
            return getDefaultEnums();
        }

        try {
            return parseCsv(Files.readAllLines(path));
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Unable to read %s", filePath), e);
        }
    }

    private static ArrayList<String> getDefaultEnums(){
        logger.info("generating carbon intensity enums from defaults");
        try {
            return parseCsv(NativeUtils.readFileContentsFromJar(DEFAULT_INTENSITY_FILE));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read the default intensity file.", e);
        }
    }

    private static ArrayList<String> generateEnumStructure(){
        ArrayList<String> builder = new ArrayList();
        builder.add(CLASS_HEADER);
        builder.addAll(ENUMS_LIST);
        builder.add(ATTRIBUTES);
        builder.add(CONSTRUCTOR_BODY);
        builder.add(GET_NAME_HELPER);
        builder.add(GET_INTENSITY_HELPER);
        builder.add(FROM_LOCALE_HELPER);
        builder.add(GET_DEFAULT_HELPER);
        builder.add("}");
        return builder;
    }
    /** Parses a csv file with a header like "locale,name,intensity". */
    private static ArrayList<String> parseCsv(List<String> lines) {
        ArrayList<String> enums = new ArrayList<>();
        lines.stream().skip(1).map(s -> s.split(",")).forEach(token -> {
                enums.add(String.format("%s(\"%s\", %f),",token[0], token[1], Double.parseDouble(token[2])));
        });
        String last =  enums.get(enums.size() - 1).substring(0, enums.get(enums.size() - 1).length() - 1) + ";";
        enums.set(enums.size() - 1, last);
        return enums;
    }
    
    private static void generateCarbonLocales(){
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("src/jcarbon/src/main/java/jcarbon/emissions/CarbonLocale.java")));
            for(String e : CARBON_LOCALE_CLASS){
                pw.write(String.format("%s\n", e));
            }
            pw.close();
            logger.info("written CarbonLocale enum successfully");
        }
        catch (IOException e) {
            throw new IllegalStateException("Unable to generate a default CarbonLocale enum.", e);
        }
    }

    public static void main(String[] args){
        generateCarbonLocales();
    }
}