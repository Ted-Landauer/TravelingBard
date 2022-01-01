package com.ted.discordbot.commands.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.ted.discordbot.Main;
import com.ted.discordbot.commands.types.ServerCommand;
import com.ted.discordbot.music.MusicController;
import net.dv8tion.jda.api.entities.*;

public class PauseCommand implements ServerCommand {

    //implemented perform command method
    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        //internal bits are similar to the volume, play, and disconnect commands
        //check the VolumeCommand class for full documentation
        GuildVoiceState voiceState;

        if((voiceState = member.getVoiceState()) != null) {

            VoiceChannel voiceChannel;

            if ((voiceChannel = voiceState.getChannel()) != null) {

                MusicController musicController = Main.getAudioManager().getMusicController(voiceChannel.getGuild().getIdLong());
                AudioPlayer player = musicController.getAudioPlayer();

                //check if the player is paused and pause it if it isn't
                if(!player.isPaused()) {

                    //pause the track
                    player.setPaused(true);

                } else {

                    textChannel.sendMessage("I can't stop playing any more than I already am").queue();
                }
            } else {

                textChannel.sendMessage("you need to be in a voice channel to execute this command").queue();
            }
        } else {

            textChannel.sendMessage("You need to be in a voice channel to execute this command").queue();
        }
    }
}
