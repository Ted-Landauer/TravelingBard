package com.ted.discordbot.music;

import com.ted.discordbot.Main;
import java.util.HashMap;

//class to handle the management of audio
public class AudioManager {

    //initialize hashmap
    public HashMap<Long, MusicController> controllerHashMap;

    //constructor for the audio manager
    public AudioManager() {

        //populate the hashmap upon creating a new instance of the audio manager
        this.controllerHashMap = new HashMap<Long, MusicController>();
    }

    //get for the music controller while also forcing it to only have one guild per music controller
    public MusicController getMusicController(long guild) {

        //initialize the music controller and set it to null so we can get a new one every time we call this
        MusicController musicController = null;

        //check if the previous hashmap contain a guild
        if(this.controllerHashMap.containsKey(guild)) {

            //set the music controller to the guild contained in the hashmap
            musicController = this.controllerHashMap.get(guild);

        } else {

            //get the guild for the current JDA instance and set the music controller to that
            musicController = new MusicController(Main.getJda().getGuildById(guild));

            //add the guild and music controller to the hashmap
            this.controllerHashMap.put(guild, musicController);
        }

        return musicController;
    }
}
