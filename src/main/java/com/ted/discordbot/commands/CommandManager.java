package com.ted.discordbot.commands;

import com.ted.discordbot.Main;
import com.ted.discordbot.commands.commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandManager extends ListenerAdapter {

    //initialize the individual command instances
    private final HelpCommand helpCommand;
    private final PlayCommand playCommand;
    private final RestartCommand restartCommand;
    private final SummonCommand summonCommand;
    private final LoopCommand loopCommand;
    //private final LoopQueueCommand loopQueueCommand;
    //private final ShuffleCommand shuffleCommand;
    private final DisconnectCommand disconnectCommand;
    private final PauseCommand pauseCommand;
    private final StopCommand stopCommand;
    //private final SkipCommand skipCommand;
    private final VolumeCommand volumeCommand;
    private final SeekCommand seekCommand;
    private final TrackInfoCommand trackInfoCommand;

    //constructor for the command manager
    public CommandManager() {
        this.helpCommand = new HelpCommand();
        this.playCommand = new PlayCommand();
        this.restartCommand = new RestartCommand();
        this.summonCommand = new SummonCommand();
        this.loopCommand = new LoopCommand();
        //this.loopQueueCommand = new LoopQueueCommand();
        //this.shuffleCommand = new ShuffleCommand();
        this.disconnectCommand = new DisconnectCommand();
        this.pauseCommand = new PauseCommand();
        this.stopCommand = new StopCommand();
        //this.skipCommand = new SkipCommand();
        this.volumeCommand = new VolumeCommand();
        this.seekCommand = new SeekCommand();
        this.trackInfoCommand = new TrackInfoCommand();

    }

    //method to handle when a command is sent to the bot
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        //check if the user is not a bot
        if(!event.getMember().getUser().isBot()) {

            //grab the string of commands
            String[] arguments = event.getMessage().getContentRaw().split(" ");

            //grab required info for the later perform command method parameters
            Guild guild = event.getGuild();
            Member member = event.getMember();
            TextChannel textChannel = event.getChannel();
            Message message = event.getMessage();

            //switch case for each command trigger
            switch (arguments[0]) {
                case "?help":

                    //call the specific class for the command and run its perform command method
                    this.helpCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case "?test":
                    break;

                case "?play":

                    playCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case "?restart":

                    restartCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case "?summon":

                    this.summonCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case "?loop":

                    this.loopCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case "?loop queue":
                    break;

                case "?shuffle":
                    break;

                case "?disconnect":

                    this.disconnectCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case "?pause":

                    this.pauseCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case "?stop":

                    this.stopCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case "?skip":
                    break;

                case "?volume":

                    this.volumeCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case "?seek":

                    this.seekCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case "?info":

                    this.trackInfoCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

            }
        }
    }
}
