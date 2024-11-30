package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties properties;

    //Static block to load properties once
    static {
        try(FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE_PATH)){
            properties=new Properties();
            properties.load(fis);

        } catch (IOException ioException){
            ioException.printStackTrace();
            throw new RuntimeException("Failed to load configuration file");
        }

    }
    //Fetch property value by key
    public static String read(String key) {
        return properties.getProperty(key);
    }
}
