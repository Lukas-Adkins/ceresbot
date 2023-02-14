/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.controller;

import java.util.Random;

import goobot.Constants;
import goobot.model.Spell;

import java.util.ArrayList;
import java.util.List;

public class CommandController {
    public SpellController spellLibrary;
    public CharacterController characterLibrary;

    /**
     * Initilizes controller
     * @param spellsFilename Filename of spells json
     */
    public CommandController(String spellsFilename){
        try{
            this.spellLibrary = new SpellController(spellsFilename);
            this.characterLibrary = new CharacterController();
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
    public String Help(String args){
        return Constants.HELP_MSG;
    }

    /**
     * Posts a response to a user's ping request
     * @param args Arguments, unused for this command
     * @return String response to a user's ping request
     */
    public String Ping(String args){
        return Constants.PONG_MSG;
    }

    /**
     * Posts a response to a user's pat request
     * @param args Arguments, unused for this command
     * @return String representing custom emoji
     */
    public String Pat(String args){
        return Constants.PAT_MSG;
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
            return Constants.DICE_NOT_PARSED_MSG;
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
            return Constants.SPELL_NOT_FOUND_MSG;
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
            return Constants.SPELL_NOT_FOUND_MSG;
    }
}
