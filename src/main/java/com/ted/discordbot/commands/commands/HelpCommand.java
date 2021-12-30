package com.ted.discordbot.commands.commands;

import com.ted.discordbot.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class HelpCommand implements ServerCommand {


    @Override
    public void performCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(0xf1e7fe);
        embedBuilder.setTitle("Useful Commands");
        embedBuilder.setDescription("**.help** - *prints this embed*\n**.play <url>** - *plays some music*");

        textChannel.sendMessage(embedBuilder.build()).queue();
    }
}
