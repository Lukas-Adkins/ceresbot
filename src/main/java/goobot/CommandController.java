/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class CommandController {
    private SpellLibrary spellLibrary;
    public static final String    
    DICE_NOT_PARSED_MSG_STRING = "DICE_NOT_PARSED_MSG",
    SPELL_NOT_FOUND_MSG_STRING = "SPELL_NOT_FOUND_MSG",
    PAT_MSG_STRING = "PAT_MSG",
    PONG_MSG_STRING = "PONG_MSG",
    HELP_MSG_STRING = "HELP_MSG";

    /**
     * Initilizes controller
     */
    public CommandController(){
        spellLibrary = new SpellLibrary();
    }

    /**
     * Posts a list of bot commands at a user's request.
     * @param args Arguments, unused for this command
     * @return String list of bot commands
     */
    public String Help(String args){
        return App.properties.getProperty(HELP_MSG_STRING);
    }

    /**
     * Posts a response to a user's ping request
     * @param args Arguments, unused for this command
     * @return String response to a user's ping request
     */
    public String Ping(String args){
        return App.properties.getProperty(PONG_MSG_STRING);
    }

    /**
     * Posts a response to a user's pat request
     * @param args Arguments, unused for this command
     * @return String representing custom emoji
     */
    public String Pat(String args){
        return App.properties.getProperty(PAT_MSG_STRING);
    }

    /**
     * Simulates a tabletop dice roll.
     * @param args Dice and number of dice in <dice>d<dice> format
     * @return Formatted string with roll total and individually rolled dice
     */
    public String Roll(String args){
        List<Integer> rolledValues = new ArrayList<>();
        Integer totalDice, dice, total = 0;
        Random diceRoller = new Random();

        try{  // Try to parse dice
            int dIndex = args.indexOf("d");
            totalDice = Integer.parseInt(args.substring(0,dIndex));
            dice = Integer.parseInt(args.substring(dIndex + 1));
        }
        catch(Exception e){
            return App.properties.getProperty(DICE_NOT_PARSED_MSG_STRING);
        }
        
        for(int i = 0; i < totalDice; i++){ // Add up dice totals
            int num = diceRoller.nextInt(dice) + 1;
            total += num;
            rolledValues.add(num);
        }
        if(totalDice == 1) // If there's only one die, return without showing individually rolled die
            return "**" + total + "**";
        return "**" + total + "**\n" + rolledValues.toString();
    }

    /**
     * Gets information on a particular D&D spell from spells.json
     * @param args Name of the spell
     * @return Spell information
     */
    public String Spell(String args){
        String spellName = args.replace("-", " ");
        Spell spell = spellLibrary.getSpell(spellName);
        if(spell != null){
            return spell.toString();
        }
        else
            return App.properties.getProperty(SPELL_NOT_FOUND_MSG_STRING);
    }

    /**
     * Gets information on a spell scroll from a particular D&D spell from spells.json
     * @param args Name of the spell
     * @return Spell scroll information
     */
    public String SpellScroll(String args){
        String spellName = args.replace("-", " ");
        Spell spell = spellLibrary.getSpell(spellName);
        if(spell != null){
            return spell.getPrice();
        }
        else
            return App.properties.getProperty(SPELL_NOT_FOUND_MSG_STRING);
    }
}
