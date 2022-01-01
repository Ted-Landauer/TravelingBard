package com.ted.discordbot.commands.commands;

import com.ted.discordbot.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class HelpCommand implements ServerCommand {

    //implemented perform command method
    @Override
    public void performCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {

        //create a new embed pane
        EmbedBuilder embedBuilder = new EmbedBuilder();

        //set the values for the embed
        embedBuilder.setColor(0x4791DE);
        embedBuilder.setTitle("Useful Commands");

        //build out the meat of the embed so that users can know what the bot can do
        embedBuilder.addField("__Bot Commands__",
                "**?help** - *prints a list of commands*\n" +
                        "**?restart** - *restarts the Bard*\n" +
                        "**?summon** - *summons the Bard*\n" +
                        "**?disconnect** - *disconnects the Bard*",
                true);

        embedBuilder.addField("__Audio Player Commands__",
                "**?play <url>** - *starts the Bard playing some music or restarts a paused song*\n" +
                        "**?stop** - *stops the current song*\n" +
                        "**?pause** - *pauses the current song*\n" +
                        "**?volume <integer>** - *sets the volume to the level requested*\n" +
                        "**?seek <time_in_seconds>** - *skips the track forward by the number of seconds requested*",
                false);

        //send the embed to the text channel
        textChannel.sendMessage(embedBuilder.build()).queue();
    }
}
