package Core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KeyValue {

    public static String getGeneralProperty(String key) throws IOException {
        String generalFileName = "general.properties";
        return getGeneralProperty(generalFileName, key);
    }

    public static String getGeneralProperty(String propFileName, String key) throws IOException {
        String result = "";
        try {
            Properties prop = new Properties();

            InputStream inputStream = KeyValue.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            // get the property value and print it out
            result = prop.getProperty(key);

            System.out.println("\nValue for " +key+ " is " +result);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return result;
    }
}

