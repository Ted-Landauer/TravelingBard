package com.ted.discordbot.commands.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//class to manage config data
public class ConfigManager {

    //get the key for the config file
    public static String getKeys(String key) {

        Properties properties = new Properties();

        try {

            InputStream inputStream = new FileInputStream("config.yml");
            properties.load(inputStream);

        } catch(IOException exception) {

            exception.printStackTrace();
        }

        return properties.getProperty(key);
    }
}
