package com.ted.discordbot.commands.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.ted.discordbot.Main;
import com.ted.discordbot.commands.types.ServerCommand;
import com.ted.discordbot.music.AudioManager;
import com.ted.discordbot.music.MusicController;
import net.dv8tion.jda.api.entities.*;

public class PlayCommand implements ServerCommand {


    //implemented perform command method
    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        if(arguments.length == 2) { // .play <url>

            //internal bits are similar to the volume, play, and disconnect commands
            //check the VolumeCommand class for full documentation
            GuildVoiceState voiceState;

            if((voiceState = member.getVoiceState()) != null) {

                VoiceChannel voiceChannel;

                if((voiceChannel = voiceState.getChannel()) != null) {

                    MusicController musicController = Main.getAudioManager().getMusicController(voiceChannel.getGuild().getIdLong());
                    AudioPlayer player = musicController.getAudioPlayer();
                    AudioPlayerManager audioPlayerManager = Main.getAudioPlayerManager();
                    net.dv8tion.jda.api.managers.AudioManager audioManager = voiceState.getGuild().getAudioManager();

                    audioManager.openAudioConnection(voiceChannel);
                    audioManager.setSelfDeafened(true);

                    //create a string builder
                    StringBuilder builder = new StringBuilder();

                    //loop over the number of arguments provided and append them to the builder with a space between
                    for(int i = 1; i < arguments.length; i++) {

                        builder.append(arguments[i] + " ");

                    }

                    //convert the builder to a proper string and trim and leading or trailing strings
                    String rawLink = builder.toString().trim();

                    //check for the correct starting url value
                    if(!rawLink.startsWith("http")) {

                        //prepend the youtube search identifier to the beginning of the url
                        rawLink = "ytsearch: " + rawLink;
                    }

                    //store the rawlink as the URL
                    final String url = rawLink;

                    //give the audio player manager what it needs to load the song url
                    audioPlayerManager.loadItem(url, new AudioLoadResultHandler() {

                        //load a single track
                        @Override
                        public void trackLoaded(AudioTrack audioTrack) {

                            //play the track
                            musicController.getAudioPlayer().playTrack(audioTrack);
                        }

                        @Override
                        public void playlistLoaded(AudioPlaylist audioPlaylist) {

                        }

                        @Override
                        public void noMatches() {

                        }

                        @Override
                        public void loadFailed(FriendlyException e) {

                        }
                    });

                } else {

                    textChannel.sendMessage("You need to be in a voice channel to execute this command!").queue();
                }
            } else {

                textChannel.sendMessage("You need to be in a voice channel to execute this command!").queue();
            }

        //restart the track if it has been paused
        } else { // .play

            GuildVoiceState voiceState;

            if((voiceState = member.getVoiceState()) != null) {

                VoiceChannel voiceChannel;

                if ((voiceChannel = voiceState.getChannel()) != null) {

                    MusicController musicController = Main.getAudioManager().getMusicController(voiceChannel.getGuild().getIdLong());
                    AudioPlayer player = musicController.getAudioPlayer();

                    player.setPaused(false);

                } else {

                    textChannel.sendMessage("You need to be in a voice channel to execute this command!").queue();
                }

            } else {

                textChannel.sendMessage("You need to be in a voice channel to execute this command!").queue();
            }
        }
    }
}
