package com.ted.discordbot.commands.types;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

//interface needed for running the individual commands
public interface ServerCommand {

    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message);

}
