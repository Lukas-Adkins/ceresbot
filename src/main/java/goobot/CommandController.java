package goobot;

public class CommandController {
    public SpellLibrary spellLibrary;

    public CommandController(){
        spellLibrary = new SpellLibrary("spells.json");
    }

    public String Help(String args){
        String str = "Command List:\n" +
            "!spell <spell> - Provides information on the given 5e D&D spell.\n" +
            "!spellscroll <spell> - Calculates the price of a spell scroll for the given 5e D&D spell, and what stats you need to use it. ";
        return str;
    }

    public String Ping(String args){
        return "Pong!";
    }

    public String Pat(String args){
        return "<:ceresblush:875653225385168898>";
    }

    public String Spell(String args){
        Spell spell = spellLibrary.getSpell(args.replace("-", " "));
        if(spell != null){
            return spell.toString();
        }
        else
            return "Spell '" + args + "' not found. Keep in mind, only PHB and TCoE spells are supported currently.";
    }

    public String SpellScroll(String args){
        Spell spell = spellLibrary.getSpell(args.replace("-", " "));
        if(spell != null){
            return spell.getPrice();
        }
        else
            return "Spell '" + args + "' not found. Keep in mind, only PHB and TCoE spells are supported currently.";
    }
}
