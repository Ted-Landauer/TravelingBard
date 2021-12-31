package com.ted.discordbot.commands.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.ted.discordbot.Main;
import com.ted.discordbot.commands.types.ServerCommand;
import com.ted.discordbot.music.MusicController;
import net.dv8tion.jda.api.entities.*;

//class handle the command for setting the volume of the bot
public class VolumeCommand implements ServerCommand {

    //implemented perform command method
    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        //if we have the required number of arguments sent then do stuff otherwise let the user know
        if(arguments.length == 2) { // .volume <INT>

            //set up for an instance of the voice state
            GuildVoiceState voiceState;

            //if the voice state for the member sending the command isn't null
            if ((voiceState = member.getVoiceState()) != null) {

                //set up for an instance of the voice channel
                VoiceChannel voiceChannel;

                //if the voice channel for the voice state isn't null
                if ((voiceChannel = voiceState.getChannel()) != null) {

                    //set the value of a new music controller to the guild id of the currently running one
                    MusicController musicController = Main.getAudioManager().getMusicController(voiceChannel.getGuild().getIdLong());

                    //get the audio player instance
                    AudioPlayer player = musicController.getAudioPlayer();

                    //grab volume info
                    int currentVolume = player.getVolume();
                    int newVolume = Integer.parseInt(arguments[1]);

                    //if else checks to see if we are increasing/decreasing the volume or keeping it the same
                    if(newVolume > currentVolume) {

                        textChannel.sendMessage("I hear you loud and clear. Turning up the tunes to " + arguments[1] + "!").queue();
                    }
                    else if(newVolume < currentVolume) {

                        textChannel.sendMessage("Understood. I'll try to keep it a little quieter. How about around "  + arguments[1] + "?").queue();
                    } else {

                        textChannel.sendMessage("Um...I'm already doing that").queue();
                    }

                    //set the volume to the requested level
                    player.setVolume(Integer.parseInt(arguments[1]));

                } else {

                    textChannel.sendMessage("Oh! I didn't know you wanted me to play something. You should ask first you know").queue();
                }
            } else {

                textChannel.sendMessage("Oh! I didn't know you wanted me to play something. You should ask first you know").queue();
            }
        } else {

            textChannel.sendMessage("Sorry, I seem to have missed part of that. How loud did you want it?").queue();
        }
    }
}
