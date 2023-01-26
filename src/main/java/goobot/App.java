/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.channel.ChannelType; 
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import java.io.FileNotFoundException;
import javax.annotation.Nonnull;

import goobot.controllers.CommandController;

public class App extends ListenerAdapter {
    private static CommandController commandController;

    public static void main(String[] args) {
        String discordToken = getDiscordToken();
        initializeDiscordBot(discordToken);
        commandController = new CommandController();
    }

    /**
     * Loads environment variables.
     * @return Discord API token to be used for this bot.
     */
    private static String getDiscordToken(){
        String functionName = "[getDiscordToken()] ", discordToken = null;
        try {
            Dotenv dotenv = Dotenv.load(); //Load .env variables
            discordToken = dotenv.get(Constants.DISCORD_TOKEN_STRING);
            if(discordToken == null)
                throw new FileNotFoundException(Constants.DISCORD_TOKEN_NOT_FOUND_ERROR);
        }
        catch(Exception e){
            System.err.println(functionName + e);
            System.exit(1);
        }
        return discordToken;
    }

    /**
     * Connects bot to Discord.
     * @param discordToken Discord API key
     */
    private static void initializeDiscordBot(String discordToken){
        String functionName = "[initializeDiscordBot()] ";
        JDA jda = null;

        try{
            jda = JDABuilder.createDefault(discordToken)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
                .build();
        }
        catch(Exception e){
            System.err.println(functionName + e);
            System.exit(1);
        }
        
        if(jda != null){
            jda.addEventListener(new App());
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
            else {
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
        if(content.substring(0, 1).equalsIgnoreCase(Constants.BOT_PREFIX))
            parseCommand(content.substring(1), event); // Strips message of command prefix and sends to parse
    }

    /**
     * Processes the command sent to the bot.
     * @param fullCommand Command text without the prefix ('!')
     */
    public void parseCommand(String fullCommand, @Nonnull MessageReceivedEvent event){
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
            default:
                if(Constants.LOG_MESSAGES)
                    System.out.println("Unknown command: " + command + " with arguments: " + args);
        }
    }

    /**
     * Posts a message to Discord.
     * @param message Text body of the message.
     * @param channel Channel the message will be posted to.
     */
    public void post(String message, MessageChannel channel){
        String functionName = "[post()] ";
        if(message.length() > 2000) // Discord 2000 character msg limit
            message = message.substring(0, 1997) + "...";
        try{
            channel.sendMessage(message).queue();
        }
        catch(Exception e){
            System.err.println(functionName + e);
        }
    }
}
