/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.FileNotFoundException;

import goobot.controller.DiscordController;

public class CeresBot {
    private static CeresBot instance;
    private final DiscordController discord;

    public static void main(String[] args) {
        try {
            new CeresBot();
        }
        catch(Exception e){
            System.out.println(Constants.BOT_START_ERROR);
            System.out.println(e);
            e.printStackTrace();
            System.exit(Constants.FATAL_FAILURE);
        }
    }

    private CeresBot() throws Exception {
        instance = this;
        this.discord = new DiscordController(getDiscordToken());
    }

    /**
     * Loads Discord token environment variable.
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
            System.exit(Constants.FATAL_FAILURE);
        }
        return discordToken;
    }
}
