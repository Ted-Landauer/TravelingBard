package com.ted.discordbot.commands;

import com.ted.discordbot.commands.commands.HelpCommand;
import com.ted.discordbot.commands.commands.PlayCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandManager extends ListenerAdapter {

    private final HelpCommand helpCommand;
    private final PlayCommand playCommand;

    public CommandManager() {
        this.helpCommand = new HelpCommand();
        this.playCommand = new PlayCommand();

    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        if(!event.getMember().getUser().isBot()) {
            String[] arguments = event.getMessage().getContentRaw().split(" ");

            Guild guild = event.getGuild();
            Member member = event.getMember();
            TextChannel textChannel = event.getChannel();
            Message message = event.getMessage();

            switch (arguments[0]) {
                case ".help":

                    this.helpCommand.performCommand(arguments, guild, member, textChannel, message);

                    break;

                case ".test":
                    break;

                case ".play":

                    playCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;


            }
        }

    }



}
