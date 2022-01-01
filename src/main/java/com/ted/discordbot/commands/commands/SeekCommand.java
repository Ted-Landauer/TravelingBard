package com.ted.discordbot.commands.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.ted.discordbot.Main;
import com.ted.discordbot.commands.types.ServerCommand;
import com.ted.discordbot.music.MusicController;
import net.dv8tion.jda.api.entities.*;

public class SeekCommand implements ServerCommand {

    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        long desiredTime = 0;

        if(arguments.length == 2) { // .seek <time> in seconds

            //internal bits are similar to the volume, play, and disconnect commands
            //check the VolumeCommand class for full documentation
            GuildVoiceState voiceState;

            if ((voiceState = member.getVoiceState()) != null) {

                VoiceChannel voiceChannel;

                if ((voiceChannel = voiceState.getChannel()) != null) {

                    MusicController musicController = Main.getAudioManager().getMusicController(voiceChannel.getGuild().getIdLong());
                    AudioPlayer player = musicController.getAudioPlayer();

                    //calculate the desired amount of time to skip forward in milliseconds
                    desiredTime = Long.parseLong(arguments[1]) * 1000;

                    //check that we aren't going to be jumping past the length of the song
                    if(desiredTime < player.getPlayingTrack().getDuration()){

                        player.getPlayingTrack().setPosition(desiredTime);
                    }
                    else {

                        textChannel.sendMessage("Woah there. I can't play this song for that long").queue();
                    }
                } else {

                    textChannel.sendMessage("You need to be in a voice channel to execute this command!").queue();
                }
            } else {

                textChannel.sendMessage("You need to be in a voice channel to execute this command!").queue();
            }
        }
    }
}
