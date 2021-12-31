package com.ted.discordbot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.ted.discordbot.commands.CommandManager;
import com.ted.discordbot.commands.config.ConfigFile;
import com.ted.discordbot.commands.config.ConfigValues;
import com.ted.discordbot.data.LiteSQL;
import com.ted.discordbot.music.AudioManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {

    //initialize values for bot and music player
    private static JDA jda;
    private static JDABuilder jdaBuilder;
    private static AudioPlayerManager audioPlayerManager;
    private static AudioManager audioManager;

    public static void main(String[] args) {

        //For database connection. Currently not used
        //LiteSQL.openConnection();

        //Grab config info
        ConfigFile.loadConfig();
        ConfigValues.loadValues();

        //create a new JDA Builder
        jdaBuilder = JDABuilder.createDefault(ConfigValues.BOT_TOKEN);

        //set the online status for the bot (TO-DO: automate the status so it isn't always idle)
        jdaBuilder.setStatus(OnlineStatus.IDLE);

        //set the activity that the bot displays (TO-DO: automate this so that it changes when the bot is playing music)
        //jdaBuilder.setActivity(Activity.playing("Hello my friend. I am a discord bot"));

        //Grab new instances of the audio player and manager
        audioPlayerManager = new DefaultAudioPlayerManager();
        audioManager = new AudioManager();



        try {
            //try to build the JDA
            jda = jdaBuilder.build();

        } catch (LoginException exception) {

            exception.printStackTrace();
            System.out.println("[SYSTEM] There was an error while logging in with the bot");

        }

        //run the register commands method
        registerCommands();

        //registers remote audio sources to this player manager
        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
    }

    //getter for the JDA
    public static JDA getJda() {

        return jda;
    }

    //getter for the audio manager if it's not null
    public static AudioManager getAudioManager() {

        if(audioManager != null) {

            return audioManager;
        }

        return null;
    }

    //Method to deal with running commands
    private static void registerCommands() {

        CommandManager commandManager = new CommandManager();
        jda.addEventListener(commandManager);
    }

    //getter for Audio Player Manager if it isn't null
    public static AudioPlayerManager getAudioPlayerManager() {

        if(audioPlayerManager != null) {

            return audioPlayerManager;
        }

        return null;
    }
}
