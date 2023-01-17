/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package goobot;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.events.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class App extends ListenerAdapter {

    public static void main(String[] args) throws InterruptedException {
        JDA jda = null;
        try {
            String discordToken = System.getenv("DISCORD_API_KEY");
            jda = JDABuilder.createDefault(discordToken)
            .enableIntents(GatewayIntent.GUILD_MESSAGES) // enables explicit access to message.getContentDisplay()
            .build();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        
        if(jda != null){
             //You can also add event listeners to the already built JDA instance
            // Note that some events may not be received if the listener is added after calling build()
            // This includes events such as the ReadyEvent
            jda.addEventListener(new App());
        }
        
     }
    
     
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                                    event.getMessage().getContentDisplay());
        }
        else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                        event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                        event.getMessage().getContentDisplay());
        }
    }
    
}
