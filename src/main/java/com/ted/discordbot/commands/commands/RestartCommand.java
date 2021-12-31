package com.ted.discordbot.commands.commands;

import com.ted.discordbot.Main;
import com.ted.discordbot.commands.types.ServerCommand;
import com.ted.discordbot.data.LiteSQL;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class RestartCommand implements ServerCommand {

    private final JDA jda = Main.getJda();

    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        if(member.hasPermission(Permission.ADMINISTRATOR)) {

            textChannel.sendMessage("The Bard needs a short rest. Let's take a quick break").queue();

            jda.getPresence().setStatus(OnlineStatus.OFFLINE);
            jda.shutdown();
            LiteSQL.disconnect();

            Main.main(null);

        } else {
            textChannel.sendMessage("Sorry, you are missing some permissions").queue();
        }
    }
}
