package com.ted.discordbot.commands.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
//import com.sedmelluq.discord.lavaplayer.demo.jda;
import com.ted.discordbot.Main;
import com.ted.discordbot.commands.types.ServerCommand;
import com.ted.discordbot.music.MusicController;
import com.ted.discordbot.queue.TrackQueue;
import net.dv8tion.jda.api.entities.*;

public class LoopCommand implements ServerCommand {

    private boolean isRepeat = false;
    //private final TrackQueue trackQueue = new TrackQueue();

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
                AudioPlayerManager audioPlayerManager = Main.getAudioPlayerManager();
                net.dv8tion.jda.api.managers.AudioManager audioManager = voiceState.getGuild().getAudioManager();

                //WIP
                TrackQueue trackQueue = new TrackQueue(player, guild.getIdLong());

                if(!isRepeat) {

                    isRepeat = true;
                    trackQueue.setRepeat(true);

                } else {

                    isRepeat = false;
                    trackQueue.setRepeat(false);
                }

                textChannel.sendMessage("Sorry, I'm not that good of a Bard yet.").queue();

            }
        }
    }
}
