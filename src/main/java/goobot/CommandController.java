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

    // Error messages
    private static final String DICE_PARSE_ERROR_MSG = "Could not parse provided dice. Please try in format:\n`!roll <number of dice>d<dice>`";
    private static final String SPELL_NOT_FOUND_ERROR_MSG = "Spell not found. Keep in mind, only PHB, XGtE, and TCoE spells are supported currently.";

    // String message constants
    private static final String CERES_BLUSH_MSG = "<:ceresblush:875653225385168898>";
    private static final String PONG_MSG = "Pong!";
    private static final String HELP_MSG = "**Command List:**\n" +
    "`!roll <number of dice>d<dice>` - Simulates a dice roll for the specified dice values.\n" +
    "`!spell <spell>` - Provides information on the given 5e D&D spell.\n" +
    "`!spellscroll <spell>` - Calculates the price of a spell scroll for the given 5e D&D spell, and what stats you need to use it. ";

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
        return HELP_MSG;
    }

    /**
     * Posts a response to a user's ping request
     * @param args Arguments, unused for this command
     * @return String response to a user's ping request
     */
    public String Ping(String args){
        return PONG_MSG;
    }

    /**
     * Posts a response to a user's pat request
     * @param args Arguments, unused for this command
     * @return String representing custom emoji
     */
    public String Pat(String args){
        return CERES_BLUSH_MSG;
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
            System.out.println(dIndex);
            totalDice = Integer.parseInt(args.substring(0,dIndex));
            dice = Integer.parseInt(args.substring(dIndex + 1));
        }
        catch(Exception e){
            return DICE_PARSE_ERROR_MSG;
        }
        
        for(int i = 0; i < totalDice; i++){ // Add up dice totals
            int num = diceRoller.nextInt(dice) + 1;
            total += num;
            rolledValues.add(num);
        }
        if(totalDice == 1) // If there's only one die, return without showing individually rolled dice
            return "**" + total + "**";
        return "**" + total + "**\n" + rolledValues.toString();
    }

    /**
     * Gets information on a particular D&D spell from spells.json
     * @param args Name of the spell
     * @return Spell information
     */
    public String Spell(String args){
        Spell spell = spellLibrary.getSpell(args.replace("-", " "));
        if(spell != null){
            return spell.toString();
        }
        else
            return SPELL_NOT_FOUND_ERROR_MSG;
    }

    /**
     * Gets information on a spell scroll from a particular D&D spell from spells.json
     * @param args Name of the spell
     * @return Spell scroll information
     */
    public String SpellScroll(String args){
        Spell spell = spellLibrary.getSpell(args.replace("-", " "));
        if(spell != null){
            return spell.getPrice();
        }
        else
            return SPELL_NOT_FOUND_ERROR_MSG;
    }
}
