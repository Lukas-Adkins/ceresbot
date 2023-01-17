package goobot;

public class CommandController {
    public SpellLibrary spellLibrary;

    public CommandController(){
        spellLibrary = new SpellLibrary("spells.json");
    }

    public String SpellScrollLookup(String name){
        System.out.println("Recieved spell scroll lookup for: " + name);
        Spell spell = spellLibrary.getSpell(name);
        if(spell != null){
            return spell.description;
        }
        else
            return "Spell '" + name + "' not found.";
    }
}
