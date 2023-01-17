package goobot;
import java.util.Objects;

public class Spell {
    String casting_time;
    String description;
    String duration;
    String level;
    String name;
    String range;
    String school;
    String type;

    public Spell() {
    }

    public Spell(String casting_time, String description, String duration, String level, String name, String range, String school, String type) {
        this.casting_time = casting_time;
        this.description = description;
        this.duration = duration;
        this.level = level;
        this.name = name;
        this.range = range;
        this.school = school;
        this.type = type;
    }

    public String getCasting_time() {
        return this.casting_time;
    }

    public void setCasting_time(String casting_time) {
        this.casting_time = casting_time;
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

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
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

    public Spell school(String school) {
        setSchool(school);
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
        return Objects.equals(casting_time, spell.casting_time) && Objects.equals(description, spell.description) && Objects.equals(duration, spell.duration) && Objects.equals(level, spell.level) && Objects.equals(name, spell.name) && Objects.equals(range, spell.range) && Objects.equals(school, spell.school) && Objects.equals(type, spell.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(casting_time, description, duration, level, name, range, school, type);
    }

    @Override
    public String toString() {
        return "{" +
            " casting_time='" + getCasting_time() + "'" +
            ", description='" + getDescription() + "'" +
            ", duration='" + getDuration() + "'" +
            ", level='" + getLevel() + "'" +
            ", name='" + getName() + "'" +
            ", range='" + getRange() + "'" +
            ", school='" + getSchool() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}