package jcarbon.emissions;

import static java.lang.Double.parseDouble;
import static jcarbon.util.LoggerUtil.getLogger;


import java.io.IOException;
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
    private static final ArrayList<String> CARBON_LOCALE_CLASS = buildClass();
    private static final String CLASS_HEADER = "package jcarbon.emissions; public enum CarbonLocale {";

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
            return toCsv(Files.readAllLines(path));
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Unable to read %s", filePath), e);
        }
    }

    private static ArrayList<String> getDefaultEnums(){
        logger.info("generating carbon intensity enums from defaults");
        try {
            return toCsv(NativeUtils.readFileContentsFromJar(DEFAULT_INTENSITY_FILE));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read the default intensity file.", e);
        }
    }

    private static ArrayList<String> buildClass(){
        ArrayList<String> builder = new ArrayList();
        builder.add(CLASS_HEADER);
        builder.addAll(ENUMS_LIST);
        return builder;
    }
    /** Parses a csv file with a header like "locale,name,intensity". */
    private static ArrayList<String> toCsv(List<String> lines) {
        ArrayList<String> enums = new ArrayList<>();
        lines.stream().skip(1).map(s -> s.split(",")).forEach(token -> {
                enums.add(String.format("%s(\"%s\", %f),",token[0], token[1], Double.parseDouble(token[2])));
        });
        return enums;
    }
    
    public static void main(String[] args){
        for (String e : CARBON_LOCALE_CLASS){
            System.out.println(e);
        }
    }
}