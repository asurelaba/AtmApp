package com.solvd.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static final Properties properties;

    static {
        properties = new Properties();
        String configFileApp = "/appconfig.properties";
        try (InputStream inputStream = AppConfig.class.getResourceAsStream(configFileApp)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String ENVIRONMENT = properties.getProperty("ENVIRONMENT");
    public static final String DB = properties.getProperty("DB");

}
