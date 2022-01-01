package com.ted.discordbot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import org.jetbrains.annotations.Nullable;
import java.nio.ByteBuffer;

//class to handle the sending of audio
public class AudioPlayerSendHandler implements AudioSendHandler {

    //initialize audio player and frame
    private final AudioPlayer audioPlayer;
    private AudioFrame audioFrame;

    //constructor for the audio player send handler
    public AudioPlayerSendHandler(AudioPlayer audioPlayer) {

        this.audioPlayer = audioPlayer;
    }

    //checks if the audio player can provide an audio frame and returns true/false accordingly
    @Override
    public boolean canProvide() {

        audioFrame = audioPlayer.provide();
        return audioFrame != null;
    }

    //standard Java NIO byte buffer
    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {

        return ByteBuffer.wrap(audioFrame.getData());
    }

    //set to true so that JDA tries to encode the audio as Opus format as that's what discord requires audio to be in(defaults to false)
    @Override
    public boolean isOpus() {

        return true;
    }
}
