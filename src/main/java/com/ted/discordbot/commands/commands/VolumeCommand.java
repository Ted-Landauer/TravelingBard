package com.ted.discordbot.commands.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.ted.discordbot.Main;
import com.ted.discordbot.commands.types.ServerCommand;
import com.ted.discordbot.music.MusicController;
import net.dv8tion.jda.api.entities.*;

public class VolumeCommand implements ServerCommand {
    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        if(arguments.length == 2) { // .volume <INT>

            GuildVoiceState voiceState;

            if ((voiceState = member.getVoiceState()) != null) {

                VoiceChannel voiceChannel;

                if ((voiceChannel = voiceState.getChannel()) != null) {

                    MusicController musicController = Main.getAudioManager().getMusicController(voiceChannel.getGuild().getIdLong());
                    AudioPlayer player = musicController.getAudioPlayer();

                    int currentVolume = player.getVolume();
                    int newVolume = Integer.parseInt(arguments[1]);

                    if(newVolume > currentVolume) {
                        textChannel.sendMessage("I hear you loud and clear. Turning up the tunes to " + arguments[1] + "!").queue();
                    }
                    else if(newVolume < currentVolume) {
                        textChannel.sendMessage("Understood. I'll try to keep it a little quieter. How about around "  + arguments[1] + "?").queue();
                    } else {
                        textChannel.sendMessage("Um...I'm already doing that").queue();
                    }

                    player.setVolume(Integer.parseInt(arguments[1]));


                } else {
                    textChannel.sendMessage("Oh! I didn't know you wanted me to play something. You should ask first you know").queue();
                }
            } else {
                textChannel.sendMessage("Oh! I didn't know you wanted me to play something. You should ask first you know").queue();
            }
        }


    }
}
