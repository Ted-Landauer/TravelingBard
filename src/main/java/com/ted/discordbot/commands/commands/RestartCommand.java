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

    //implemented perform command method
    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {

        //check if user sending command has administrator permissions before restarting the bot
        if(member.hasPermission(Permission.ADMINISTRATOR)) {

            //let people know that the bot is restarting
            textChannel.sendMessage("The Bard needs a short rest. Let's take a quick break").queue();

            //set the bot to offline status, start the shutdown process, and disconnect any DB connection
            jda.getPresence().setStatus(OnlineStatus.OFFLINE);
            jda.shutdown();
            LiteSQL.disconnect();

            //call an instance of main to restart things
            Main.main(null);

        } else {

            textChannel.sendMessage("Sorry, you are missing some permissions").queue();
        }
    }
}
