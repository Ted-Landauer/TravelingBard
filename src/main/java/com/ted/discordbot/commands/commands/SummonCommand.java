package com.ted.discordbot.commands.commands;

import com.ted.discordbot.Main;
import com.ted.discordbot.commands.types.ServerCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;

//class to handle getting the bot to join the voice channel
public class SummonCommand implements ServerCommand {

    //private final JDA jda = Main.getJda();

    //implemented perform command method
    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        //internal bits are similar to the volume, play, and disconnect commands
        //check the VolumeCommand class for full documentation
        GuildVoiceState voiceState;

        if ((voiceState = member.getVoiceState()) != null) {

            VoiceChannel voiceChannel;

            if ((voiceChannel = voiceState.getChannel()) != null) {

                net.dv8tion.jda.api.managers.AudioManager audioManager = voiceState.getGuild().getAudioManager();

                //connect to the voice channel
                audioManager.openAudioConnection(voiceChannel);

                //deafen the bot when it joins
                audioManager.setSelfDeafened(true);

            } else {

                textChannel.sendMessage("You need to be in a voice channel to execute this command!").queue();
            }

        } else {

            textChannel.sendMessage("You need to be in a voice channel to execute this command!").queue();
        }
    }
}
