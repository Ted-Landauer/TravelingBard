package com.ted.discordbot.commands.config;

//class to get the config values
public class ConfigValues {

    //set up for grabbing the bot token
    public static String BOT_TOKEN;

    //grab and assign the bot token and any other values that may be configured (to be added later)
    public static void loadValues() {

        BOT_TOKEN = ConfigManager.getKeys("token");

    }
}
