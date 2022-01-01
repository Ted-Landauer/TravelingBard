package com.ted.discordbot.queue;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//WIP
public class TrackQueue extends AudioEventAdapter {

    private final long guildId;
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    private boolean isRepeat = false;

    public TrackQueue(AudioPlayer player, long guildId) {

        this.player = player;
        this.guildId = guildId;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void queue(AudioTrack track) {

        if(!player.startTrack(track, true)) {

            queue.offer(track);
        }
    }

    public void nextTrack() {

        if(isRepeat) {

            queue(player.getPlayingTrack());
        }

        player.startTrack(queue.poll(), false);
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {

        if(endReason.mayStartNext) {

            nextTrack();
        }
    }

    public boolean getRepeat() {

        return isRepeat;
    }

    public void setRepeat(boolean repeat) {

        isRepeat = repeat;
    }




}
