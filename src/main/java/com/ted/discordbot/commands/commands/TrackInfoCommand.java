package com.ted.discordbot.commands.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.ted.discordbot.Main;
import com.ted.discordbot.commands.types.ServerCommand;
import com.ted.discordbot.music.MusicController;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

public class TrackInfoCommand implements ServerCommand {
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
                //AudioPlayerManager audioPlayerManager = Main.getAudioPlayerManager();
                //net.dv8tion.jda.api.managers.AudioManager audioManager = voiceState.getGuild().getAudioManager();


                //create a new embed pane
                EmbedBuilder embedBuilder = new EmbedBuilder();

                //set the values for the embed
                embedBuilder.setColor(0x47DE8C);
                embedBuilder.setTitle("Song Info");

                //build out the meat of the embed so that users can know what the bot can do
                embedBuilder.setDescription("**Title** - " + player.getPlayingTrack().getInfo().title + "\n" +
                                            "**Author** - " + player.getPlayingTrack().getInfo().author + "\n" +
                                            "**Length** - " + (((player.getPlayingTrack().getInfo().length)/1000)) + " Seconds");

                //send the embed to the text channel
                textChannel.sendMessage(embedBuilder.build()).queue();

            }
        }
    }
}
