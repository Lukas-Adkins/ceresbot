package goobot;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class CommandController {
    public SpellLibrary spellLibrary;

    public CommandController(){
        spellLibrary = new SpellLibrary();
    }

    public String Help(String args){
        String str = "**Command List:**\n" +
            "`!roll <number of dice>d<dice>` - Simulates a dice roll for the specified dice values.\n" +
            "`!spell <spell>` - Provides information on the given 5e D&D spell.\n" +
            "`!spellscroll <spell>` - Calculates the price of a spell scroll for the given 5e D&D spell, and what stats you need to use it. ";
        return str;
    }

    public String Ping(String args){
        return "Pong!";
    }

    public String Pat(String args){
        return "<:ceresblush:875653225385168898>";
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
        try{
            int dIndex = args.indexOf("d");
            System.out.println(dIndex);
            totalDice = Integer.parseInt(args.substring(0,dIndex));
            System.out.println(totalDice);
            dice = Integer.parseInt(args.substring(dIndex + 1));
            System.out.println(dice);
        }
        catch(Exception e){
            return "Could not parse provided dice. Please try in format:\n`!roll <number of dice>d<dice>`";
        }

        for(int i = 0; i < totalDice; i++){
            int num = diceRoller.nextInt(dice) + 1;
            total += num;
            rolledValues.add(num);
        }
        if(totalDice == 1) // If there's only one die, return without showing individually rolled dice
            return "**" + total + "**";
        return "**" + total + "**\n" + rolledValues.toString();
    }

    public String Spell(String args){
        Spell spell = spellLibrary.getSpell(args.replace("-", " "));
        if(spell != null){
            return spell.toString();
        }
        else
            return "Spell '" + args + "' not found. Keep in mind, only PHB, XGtE, and TCoE spells are supported currently.";
    }

    public String SpellScroll(String args){
        Spell spell = spellLibrary.getSpell(args.replace("-", " "));
        if(spell != null){
            return spell.getPrice();
        }
        else
            return "Spell '" + args + "' not found. Keep in mind, only PHB, XGtE, and TCoE spells are supported currently.";
    }
}
