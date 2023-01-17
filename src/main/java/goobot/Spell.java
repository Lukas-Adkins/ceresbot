package goobot;
import java.util.Arrays;
import java.util.Objects;

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
    String[] tags;
    String type;

    public Spell() {
    }

    public Spell(String casting_time, String[] classes, SpellComponent components, String description, String duration, String level, String name, String range, Boolean ritual, String school, String[] tags, String type) {
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
        this.tags = tags;
        this.type = type;
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

    public String[] getTags() {
        return this.tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Spell tags(String[] tags) {
        setTags(tags);
        return this;
    }

    public Spell type(String type) {
        setType(type);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Spell)) {
            return false;
        }
        Spell spell = (Spell) o;
        return Objects.equals(casting_time, spell.casting_time) && Objects.equals(classes, spell.classes) && Objects.equals(components, spell.components) && Objects.equals(description, spell.description) && Objects.equals(duration, spell.duration) && Objects.equals(level, spell.level) && Objects.equals(name, spell.name) && Objects.equals(range, spell.range) && Objects.equals(ritual, spell.ritual) && Objects.equals(school, spell.school) && Objects.equals(tags, spell.tags) && Objects.equals(type, spell.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(casting_time, classes, components, description, duration, level, name, range, ritual, school, tags, type);
    }

    @Override
    public String toString() {
        return "**" + name + "**\n" +
        "*" + type + "*\n" +
        "**Casting Time**: " + casting_time + "\n" +
        "**Range**: " + range + "\n" +
        "**Components**: " + components.getRaw() + "\n" +
        "**Duration**: " + duration + "\n\n" +
        description +"\n\n" +
        "**Tags**: " + Arrays.toString(tags);
    }
}