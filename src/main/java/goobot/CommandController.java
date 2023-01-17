package goobot;

public class CommandController {
    public SpellLibrary spellLibrary;

    public CommandController(){
        spellLibrary = new SpellLibrary("spells.json");
    }

    public String Help(String args){
        String str = "Command List:\n" +
            "!spell <spell> - Provides information on the given spell.\n" +
            "!spellscroll <spell> - Calculates the price of a spell scroll for the given spell. ";
        return str;
    }
    
    public String Ping(String args){
        return "Pong!";
    }

    public String Spell(String args){
        Spell spell = spellLibrary.getSpell(args);
        if(spell != null){
            return spell.toString();
        }
        else
            return "Spell '" + args + "' not found.";
    }

    public String SpellScroll(String args){
        Spell spell = spellLibrary.getSpell(args);
        if(spell != null){
            return spell.getPrice();
        }
        else
            return "Spell '" + args + "' not found.";
    }
}
