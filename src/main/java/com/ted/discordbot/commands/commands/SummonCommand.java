package com.ted.discordbot.commands.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.ted.discordbot.Main;
import com.ted.discordbot.commands.types.ServerCommand;
import com.ted.discordbot.music.MusicController;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;

public class SummonCommand implements ServerCommand {

    private final JDA jda = Main.getJda();

    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        GuildVoiceState voiceState;

        if ((voiceState = member.getVoiceState()) != null) {

            VoiceChannel voiceChannel;

            if ((voiceChannel = voiceState.getChannel()) != null) {

                //MusicController musicController = Main.getAudioManager().getMusicController(voiceChannel.getGuild().getIdLong());

                net.dv8tion.jda.api.managers.AudioManager audioManager = voiceState.getGuild().getAudioManager();
                audioManager.openAudioConnection(voiceChannel);

                audioManager.setSelfDeafened(true);


            } else {

                textChannel.sendMessage("You need to be in a voice channel to execute this command!").queue();
            }

        } else {

            textChannel.sendMessage("You need to be in a voice channel to execute this command!").queue();
        }
    }
}
