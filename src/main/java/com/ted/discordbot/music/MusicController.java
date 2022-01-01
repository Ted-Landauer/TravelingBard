package com.ted.discordbot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.ted.discordbot.Main;
import com.ted.discordbot.queue.TrackQueue;
import net.dv8tion.jda.api.entities.Guild;

public class MusicController {

    //initalize the guild and audio player
    private Guild guild;
    private AudioPlayer audioPlayer;
    private TrackQueue trackQueue;

    //constructor for music controller
    public MusicController(Guild guild) {

        //grab this instance of the guild and audio player,
        this.guild = guild;
        this.audioPlayer = Main.getAudioPlayerManager().createPlayer();

        //WIP
        this.trackQueue = new TrackQueue(this.audioPlayer, this.guild.getIdLong());


        //set the Audio Send Handler that the manager will use to provide an audio connection
        this.guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(audioPlayer));

        //sets the initial player volume
        this.audioPlayer.setVolume(15);
    }

    //getter for the guild if it isn't null
    public Guild getGuild() {

        if(this.guild != null) {

            return this.guild;
        }

        return null;
    }

    //getter for the audio player if it isn't null
    public AudioPlayer getAudioPlayer() {

        if(this.audioPlayer != null) {

            return this.audioPlayer;
        }

        return null;
    }
}
