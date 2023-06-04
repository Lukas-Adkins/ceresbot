/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.controller;

import goobot.Constants;
import goobot.model.CommandInterface;
import goobot.model.starlight.item.Mech;
import goobot.service.CommandService;

import java.util.List;

public class CommandController implements CommandInterface {
    public CommandService commandService;

    /**
     * Initilizes controller
     * @param spellsFilename Filename of spells json
     */
    public CommandController(){
        try{
            this.commandService = new CommandService();
        }
        catch(Exception e){
            System.err.println(Constants.BOT_START_ERROR);
            System.exit(Constants.FATAL_FAILURE);
        }
    }

    /**
     * Posts a list of bot commands at a user's request.
     * @param args Arguments, unused for this command
     * @return String list of bot commands
     */
    public String Help(String args) throws Exception {
        return Constants.HELP_MSG;
    }

    /**
     * Posts a response to a user's ping request
     * @param args Arguments, unused for this command
     * @return String response to a user's ping request
     */
    public String Ping(String args) throws Exception {
        return Constants.PONG_MSG;
    }

    /**
     * Posts a response to a user's pat request
     * @param args Arguments, unused for this command
     * @return String representing custom emoji
     */
    public String Pat(String args) throws Exception {
        return Constants.PAT_MSG;
    }

    @Override
    public List<String> CharacterInfo(String args) throws Exception {
        return commandService.CharacterInfo(args);
    }

    @Override
    public Mech MechInfo(String args) throws Exception {
        return commandService.MechInfo(args);
    }

    @Override
    public String Roll(String args) throws Exception {
        return commandService.Roll(args);
    }

    @Override
    public String Spell(String args) throws Exception {
        return commandService.Spell(args);
    }

    @Override
    public String SpellScroll(String args) throws Exception {
        return commandService.SpellScroll(args);
    }

    @Override
    public String Item(String args) throws Exception {
        return commandService.Item(args);
    }

    @Override
    public String Shop(String args) throws Exception {
        return commandService.Shop(args);
    }

    @Override
    public String Loot(String args) throws Exception {
        return commandService.Loot(args);
    }
}
