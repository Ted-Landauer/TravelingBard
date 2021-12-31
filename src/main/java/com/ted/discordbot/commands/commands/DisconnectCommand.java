package com.ted.discordbot.commands.commands;

import com.ted.discordbot.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.*;

public class DisconnectCommand implements ServerCommand {
    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        GuildVoiceState voiceState;

        if ((voiceState = member.getVoiceState()) != null) {

            net.dv8tion.jda.api.managers.AudioManager audioManager = voiceState.getGuild().getAudioManager();
            audioManager.closeAudioConnection();

        } else {

            textChannel.sendMessage("Looks like the Bard already decided to duck out.").queue();
        }
    }
}
