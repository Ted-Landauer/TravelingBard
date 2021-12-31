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
        embedBuilder.setColor(0xf1e7fe);
        embedBuilder.setTitle("Useful Commands");

        //build out the meat of the embed so that users can know what the bot can do
        embedBuilder.setDescription("**.help** - *prints this embed*\n" +
                                    "**.play <url>** - *starts the Bard playing some music or restarts a paused song*\n" +
                                    "**.restart** - *restarts the Bard*\n" +
                                    "**.summon** - *summons the Bard*\n" +
                                    "**.disconnect** - *disconnects the Bard*\n" +
                                    "**.volume <integer>** - *sets the volume to the level requested*\n" +
                                    "**.stop** - *stops the current song*\n" +
                                    "**.pause** - *pauses the current song*");

        //send the embed to the text channel
        textChannel.sendMessage(embedBuilder.build()).queue();
    }
}
