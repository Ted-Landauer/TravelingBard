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

    private static JDA jda;
    private static JDABuilder jdaBuilder;
    private static AudioPlayerManager audioPlayerManager;
    private static AudioManager audioManager;

    public static void main(String[] args) {

        //LiteSQL.openConnection();

        ConfigFile.loadConfig();
        ConfigValues.loadValues();

        //leaving line 25 empty so that the bot's SSO token isn't exposed in source control
        jdaBuilder = JDABuilder.createDefault(ConfigValues.BOT_TOKEN);
        jdaBuilder.setStatus(OnlineStatus.IDLE);
        //jdaBuilder.setActivity(Activity.playing("Hello my friend. I am a discord bot"));


        audioPlayerManager = new DefaultAudioPlayerManager();
        audioManager = new AudioManager();


        try {

            jda = jdaBuilder.build();

        } catch (LoginException exception) {

            exception.printStackTrace();
            System.out.println("[SYSTEM] There was an error while logging in with the bot");

        }

        registerCommands();

        AudioSourceManagers.registerRemoteSources(audioPlayerManager);


    }


    public static JDA getJda() {

        return jda;

    }

    public static AudioManager getAudioManager() {

        if(audioManager != null) {

            return audioManager;

        }

        return null;
    }



    private static void registerCommands() {

        CommandManager commandManager = new CommandManager();
        jda.addEventListener(commandManager);

    }



    public static AudioPlayerManager getAudioPlayerManager() {

        if(audioPlayerManager != null) {

            return audioPlayerManager;

        }

        return null;
    }
}
