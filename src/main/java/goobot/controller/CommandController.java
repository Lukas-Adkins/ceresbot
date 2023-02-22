/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.controller;

import java.util.Random;

import goobot.Constants;
import goobot.model.Spell;
import java.util.Arrays;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandController {
    public SpellController spellLibrary;
    public CharacterController characterLibrary;

    /**
     * Initilizes controller
     * @param spellsFilename Filename of spells json
     */
    public CommandController(String spellsFilepath, List<String> characterFilepaths){
        try{
            this.spellLibrary = new SpellController(spellsFilepath);
            this.characterLibrary = new CharacterController(characterFilepaths);
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
     * Looks up character info.
     * @param args Arguments, contains name of character
     * @return Tuple representing character information, and the character image URL
     */
    public List<String> CharacterInfo(String args){
        String characterName = args.replace("-", " ");
        List<String> dndChar = this.characterLibrary.getCharacter(characterName);
        if(dndChar != null)
            return dndChar;
        return Arrays.asList(Constants.CHARACTER_NOT_FOUND_MSG, "");
    }

    /**
     * Simulates a tabletop dice roll.
     * @param args Dice and number of dice in <dice>d<dice> format
     * @return Formatted string with roll total and individually rolled dice
     */
    public String Roll(String args){
        Pattern dicePattern = Pattern.compile("([\\+\\-]?\\d+)?(d[\\+\\-]?\\d+)(k[\\+\\-]?\\d+)?([\\+\\-]\\d+)?");
        String sanitized = args.replace(" ", "");
        Matcher diceMatcher = dicePattern.matcher(sanitized);
        int numKeep = -1, numDice = 1, numSides = 20, modifier = 0, total = 0;

        if(diceMatcher.find()){
			if(diceMatcher.group(1)!=null && !diceMatcher.group(1).isEmpty())
                numDice  = Integer.parseInt(diceMatcher.group(1));
			if(diceMatcher.group(2)!=null && !diceMatcher.group(2).isEmpty())
                numSides = Integer.parseInt(diceMatcher.group(2).substring(1));
			if(diceMatcher.group(3)!=null && !diceMatcher.group(3).isEmpty())
                numKeep  = Integer.parseInt(diceMatcher.group(3).substring(1));
			else 
                numKeep=numDice;
            if(diceMatcher.group(4)!=null && !diceMatcher.group(4).isEmpty())
                modifier = Integer.parseInt(diceMatcher.group(4));
            System.out.println(numDice + " " + numKeep + " " + numSides + " " + modifier);
        }

        try { 
            // Sanity check
            if(numDice <= 0 || numSides <= 0 || Math.abs(numKeep)>numDice || !args.matches(".*\\d+.*"))
                throw new Exception();
            
            ArrayList<Integer> rolledDice = rollDice(numDice, numSides);
            for(Integer die : rolledDice){
                total += die;
            }
            total += modifier; // Add math modifier to roll
            if(numDice == 1 && modifier == 0) // If there's only one die, return without showing individually rolled die
                return "**" + total + "**";
            
            String returnMessage = rolledDice.toString();
            if(modifier < 0)
                returnMessage += " - " + Math.abs(modifier) + " = ";
            else
                returnMessage += " + " + modifier + " = ";
            return returnMessage + "**" + total + "**";

        }
        catch(Exception e){
            return Constants.DICE_NOT_PARSED_MSG;
        }
    }

    private ArrayList<Integer> rollDice(int numDice, int numSides){
        ArrayList<Integer> rolledValues = new ArrayList<>();
        Random diceRoller = new Random();
        
        for(int i = 0; i < numDice; i++){ // Add up dice totals
            int num = diceRoller.nextInt(numSides) + 1;
            rolledValues.add(num);
        }
        return rolledValues;
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
