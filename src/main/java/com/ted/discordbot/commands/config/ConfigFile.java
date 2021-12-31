package com.ted.discordbot.commands.config;

import com.ted.discordbot.Main;
import java.io.*;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

//class for handling the config file itself
public class ConfigFile {

    //load up the config
    public static void loadConfig() {

        //set the name of the config file
        String filename = "config.yml";

        //load instance of the main class
        ClassLoader classLoader = Main.class.getClassLoader();

        //try to load in the config file
        try (InputStream inputStream = classLoader.getResourceAsStream(filename)){

            //grab the contents of the config file
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            File file = new File(filename);

            //if the config file doesn't exist then create it
            if(!file.exists()){

                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter((new FileWriter(filename)));
                bufferedWriter.write(result);
                bufferedWriter.close();
            }

        } catch (IOException exception) {

            exception.printStackTrace();
        }
    }
}
