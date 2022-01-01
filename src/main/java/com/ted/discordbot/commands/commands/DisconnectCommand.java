package com.ted.discordbot.commands.commands;

import com.ted.discordbot.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.*;

public class DisconnectCommand implements ServerCommand {

    //implemented perform command method
    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        //internal bits are similar to the volume, play, and disconnect commands
        //check the VolumeCommand class for full documentation
        GuildVoiceState voiceState;

        if ((voiceState = member.getVoiceState()) != null) {

            net.dv8tion.jda.api.managers.AudioManager audioManager = voiceState.getGuild().getAudioManager();

            //close the bot's connection to the voice channel
            audioManager.closeAudioConnection();
            textChannel.sendMessage("It's been a pleasure playing for you and I look forward journeying with you later").queue();

        } else {

            textChannel.sendMessage("Looks like the Bard already decided to duck out.").queue();
        }
    }
}
