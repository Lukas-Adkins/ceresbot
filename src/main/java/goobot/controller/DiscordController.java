/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.controller;
    
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.channel.ChannelType; 
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import javax.annotation.Nonnull;
import java.util.List;

import goobot.Constants;
import goobot.model.starlight.item.Mech;

public class DiscordController extends ListenerAdapter {
    private final CommandController commandController;
    
    public DiscordController(String discordToken){
        this.commandController = new CommandController();
        initializeDiscordBot(discordToken);
    }

    /**
     * Connects bot to Discord.
     * @param discordToken Discord API key
     */
    private void initializeDiscordBot(String discordToken){
        String functionName = "[initializeDiscordBot()] ";
        JDA jda = null;

        try{
            jda = JDABuilder.createDefault(discordToken)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
                .build();
        }
        catch(Exception e){
            System.err.println(functionName + e);
            System.exit(Constants.FATAL_FAILURE);
        }
        
        if(jda != null){
            jda.addEventListener(this);
        }
    }
    
    /**
     * Reads user messages and looks for the command prefix.
     */
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if(Constants.LOG_MESSAGES) {  // If logging is on, log all recieved messages to console
            if (event.isFromType(ChannelType.PRIVATE)) {
                System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                                        event.getMessage().getContentDisplay());
            }
            else if(event != null){
                System.out.printf("[%s] %s: %s\n", event.getGuild().getName(),
                            event.getMember().getEffectiveName(),
                            event.getMessage().getContentDisplay());
            }
        }
        // Don't respond to other bot accounts, including ourself
        if(event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        // Sees if message contains the command prefix
        if(content.length() > 0 && content.substring(0, 1).equalsIgnoreCase(Constants.BOT_PREFIX)){
            parseCommand(content.substring(1), event); // Strips message of command prefix and sends to parse
            event.getMessage().delete().queue(); // Delete command message sent by user
        }
    }

    /**
     * Processes the command sent to the bot.
     * @param fullCommand Command text without the prefix ('!')
     */
    private void parseCommand(String fullCommand, @Nonnull MessageReceivedEvent event){
        String command, args;
        MessageChannel channel = event.getChannel();
        int spaceIndex = fullCommand.indexOf(' ');

        if(spaceIndex > -1) { // If there is more than one word, split into first word and remaining words
            command = fullCommand.substring(0, spaceIndex).trim().toLowerCase(); // Extract first word
            args = fullCommand.substring(spaceIndex).trim().toLowerCase(); // Extract remaining words
        }
        else{ // There is only one word, use it as the inputted command
            command = fullCommand.toLowerCase();
            args = "";
        }
        try{
            switch(command){ // Check command, and call command controller if it exists
                case "help":
                    post(commandController.Help(args), channel);
                    break;
                case "pat":
                    post(commandController.Pat(args), channel);
                    break;
                case "ping":
                    post(commandController.Ping(args), channel);
                    break;
                case "spell":
                    post(commandController.Spell(args), channel);
                    break; 
                case "spellscroll":
                    post(commandController.SpellScroll(args), channel);
                    break;
                case "roll":
                    post(commandController.Roll(args), channel);
                    break;
                case "item":
                    post(commandController.Item(args).get(0), channel);
                    if(!commandController.Item(args).get(1).isEmpty())
                        post(commandController.Item(args).get(1), channel);
                    break;
                case "shop":
                    post(commandController.Shop(args), channel);
                    break;
                case "loot":
                    post(commandController.Loot(args), channel);
                    break;
                case "character":
                    List<String> response = commandController.CharacterInfo(args);
                    // If the character has an image, post its link
                    if(!response.get(1).isEmpty())
                        post(response.get(1), channel);
                    post(response.get(0), channel);
                    break;
                default:
                    if(Constants.LOG_MESSAGES)
                        System.out.println("Unknown command: " + command + " with arguments: " + args);
            }
        }
        catch(Exception e){
            System.err.println("Error in parsing command: " + command + " " + args);
            e.printStackTrace();
        }
    }

    /**
     * Posts a message to Discord.
     * @param message Text body of the message.
     * @param channel Channel the message will be posted to.
     */
    public void post(String message, MessageChannel channel){
        String functionName = "[post()] ";
        // Respect Discord 2000 character msg limit
        if(message.length() > Constants.DISCORD_MSG_CAP)
            message = message.substring(0, Constants.DISCORD_MSG_CAP - 3) + "...";
        try{
            channel.sendMessage(message).queue();
        }
        catch(Exception e){
            System.err.println(functionName + e);
        }
    }
}
