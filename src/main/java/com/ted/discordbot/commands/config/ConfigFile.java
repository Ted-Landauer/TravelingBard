package com.ted.discordbot.commands.config;

import com.ted.discordbot.Main;
import java.io.*;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class ConfigFile {

    public static void loadConfig() {
        String filename = "config.yml";

        ClassLoader classLoader = Main.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(filename)){

            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            File file = new File(filename);

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
