package com.ted.discordbot.commands;

import com.ted.discordbot.commands.commands.HelpCommand;
import com.ted.discordbot.commands.commands.PlayCommand;
import com.ted.discordbot.commands.commands.RestartCommand;
import com.ted.discordbot.commands.commands.SummonCommand;
import com.ted.discordbot.commands.commands.DisconnectCommand;
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
    private final RestartCommand restartCommand;
    private final SummonCommand summonCommand;
    //private final LoopCommand loopCommand;
    //private final LoopQueueCommand loopQueueCommand;
    //private final ShuffleCommand shuffleCommand;
    private final DisconnectCommand disconnectCommand;
    //private final PauseCommand pauseCommand;
    //private final StopCommand stopCommand;
    //private final SkipCommand skipCommand;
    //private final VolumeCommand volumeCommand;
    //private final SeekCommand seekCommand;



    public CommandManager() {
        this.helpCommand = new HelpCommand();
        this.playCommand = new PlayCommand();
        this.restartCommand = new RestartCommand();
        this.summonCommand = new SummonCommand();
        //this.loopCommand = new LoopCommand();
        //this.loopQueueCommand = new LoopQueueCommand();
        //this.shuffleCommand = new ShuffleCommand();
        this.disconnectCommand = new DisconnectCommand();
        //this.pauseCommand = new PauseCommand();
        //this.stopCommand = new StopCommand();
        //this.skipCommand = new SkipCommand();
        //this.volumeCommand = new VolumeCommand();
        //this.seekCommand = new SeekCommand();

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

                case ".restart":
                    restartCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case ".summon":
                    this.summonCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case ".loop":
                    break;

                case ".loop queue":
                    break;

                case ".shuffle":
                    break;

                case ".disconnect":
                    this.disconnectCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;

                case ".pause":
                    break;

                case ".stop":
                    break;

                case ".skip":
                    break;

                case ".volume":
                    break;


            }
        }

    }



}
