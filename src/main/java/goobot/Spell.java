package goobot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Collections;

public class Spell {
    String casting_time;
    String[] classes;
    SpellComponent components;
    String description;
    String duration;
    String level;
    String name;
    String range;
    Boolean ritual;
    String school;

    public Spell() {}

    public Spell(String casting_time, String[] classes, SpellComponent components, String description, String duration, String level, String name, String range, Boolean ritual, String school) {
        this.casting_time = casting_time;
        this.classes = classes;
        this.components = components;
        this.description = description;
        this.duration = duration;
        this.level = level;
        this.name = name;
        this.range = range;
        this.ritual = ritual;
        this.school = school;
    }

    public String getCasting_time() {
        return this.casting_time;
    }

    public void setCasting_time(String casting_time) {
        this.casting_time = casting_time;
    }

    public String[] getClasses() {
        return this.classes;
    }

    public void setClasses(String[] classes) {
        this.classes = classes;
    }

    public SpellComponent getComponents() {
        return this.components;
    }

    public void setComponents(SpellComponent components) {
        this.components = components;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRange() {
        return this.range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Boolean isRitual() {
        return this.ritual;
    }

    public Boolean getRitual() {
        return this.ritual;
    }

    public void setRitual(Boolean ritual) {
        this.ritual = ritual;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Spell casting_time(String casting_time) {
        setCasting_time(casting_time);
        return this;
    }

    public Spell classes(String[] classes) {
        setClasses(classes);
        return this;
    }

    public Spell components(SpellComponent components) {
        setComponents(components);
        return this;
    }

    public Spell description(String description) {
        setDescription(description);
        return this;
    }

    public Spell duration(String duration) {
        setDuration(duration);
        return this;
    }

    public Spell level(String level) {
        setLevel(level);
        return this;
    }

    public Spell name(String name) {
        setName(name);
        return this;
    }

    public Spell range(String range) {
        setRange(range);
        return this;
    }

    public Spell ritual(Boolean ritual) {
        setRitual(ritual);
        return this;
    }

    public Spell school(String school) {
        setSchool(school);
        return this;
    }


    public String getPrice(){
        // Normalize all classes to lowercase
        for(int i = 0; i < classes.length; i++){
            classes[i] = classes[i].toLowerCase();
        }
        ArrayList<String> spellList = new ArrayList<>(Arrays.asList(classes));

        if(level.contains("cantrip"))
            return "As a " + spellList.toString() + " cantrip spell scroll, " + name + " would cost **15** gp.\n" +
            "As a cantrip, you can always cast this spell scroll without possibility of error.";       
        int level = Integer.parseInt(this.level);
        Double price = 10 * Math.pow(2.4, level);

        // If it's a warlock/cleric/paladin spell, increase price by 150%
        List<String> normalPriceClasses = Arrays.asList("bard", "druid", "ranger", "sorcerer", "wizard");
        if(Collections.disjoint(spellList, normalPriceClasses))
            price = price * 1.5;

        int roundedPrice = (int) Math.round(price);
        String levelMods = "";

        // Charisma casters
        if(!Collections.disjoint(spellList, Arrays.asList("bard", "sorcerer", "warlock", "paladin"))){
            int modToCast = level;
            if(Collections.disjoint(spellList, Arrays.asList("bard", "sorcerer", "warlock"))) // Paladin only spell
            modToCast = (int) Math.ceil((double) modToCast / 2);
            levelMods = levelMods + "CHA mod +" + String.valueOf(modToCast) + " ";
        }
        // Wisdom casters
        if(!Collections.disjoint(spellList, Arrays.asList("cleric", "druid", "ranger"))){
            int modToCast = level;
            if(Collections.disjoint(spellList, Arrays.asList("cleric", "druid"))) // Ranger only spell
                modToCast = (int) Math.ceil((double) modToCast / 2);
            if(levelMods.length() > 1)
                levelMods = levelMods + ", or WIS mod +" + String.valueOf(modToCast) + " ";
            else
                levelMods = levelMods + "WIS mod +" + String.valueOf(modToCast) + " ";
        }
        // Intelligence casters
        if(!Collections.disjoint(spellList, Arrays.asList("wizard"))){
            if(levelMods.length() > 1)
                levelMods = levelMods + ", or INT mod +" + String.valueOf(level) + " ";
            else
                levelMods = levelMods + "INT mod +" + String.valueOf(level) + " ";
        }

        return 
        "As a level " + level + " " + spellList.toString() + " spell scroll, " + name + " would cost **" + roundedPrice + "** gp.\n" +
        "If you cannot normally cast this spell, you need " + levelMods + "to cast this spell scroll without possibility of error.";       
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Spell)) {
            return false;
        }
        Spell spell = (Spell) o;
        return Objects.equals(casting_time, spell.casting_time) && Objects.equals(classes, spell.classes) && Objects.equals(components, spell.components) && Objects.equals(description, spell.description) && Objects.equals(duration, spell.duration) && Objects.equals(level, spell.level) && Objects.equals(name, spell.name) && Objects.equals(range, spell.range) && Objects.equals(ritual, spell.ritual) && Objects.equals(school, spell.school);
    }

    @Override
    public int hashCode() {
        return Objects.hash(casting_time, classes, components, description, duration, level, name, range, ritual, school);
    }

    @Override
    public String toString() {
        String type;
        if(this.level.equals("cantrip"))
            type = this.school + " cantrip";
        else
            type = "Level " + this.level + " " + this.school;


        return "**" + name + "**\n" +
        "*" + type + "*\n" +
        "**Casting Time**: " + casting_time + "\n" +
        "**Range**: " + range + "\n" +
        "**Components**: " + components.getRaw() + "\n" +
        "**Duration**: " + duration + "\n\n" +
        description +"\n\n" +
        "**Spell Lists**: " + Arrays.toString(classes);
    }
}