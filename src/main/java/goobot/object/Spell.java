package goobot.object;
import java.util.Objects;

public class Spell {
    String name;
    String desc;
    String page;
    String range;
    String components;
    String material;
    String ritual;
    String duration;
    String concentration;
    String castingTime;
    String level;
    String school;
    String classes;

    public Spell() {}

    public Spell(String name, String desc, String page, String range, String components, String material, String ritual, String duration, String concentration, String castingTime, String level, String school, String classes) {
        this.name = name;
        this.desc = desc;
        this.page = page;
        this.range = range;
        this.components = components;
        this.material = material;
        this.ritual = ritual;
        this.duration = duration;
        this.concentration = concentration;
        this.castingTime = castingTime;
        this.level = level;
        this.school = school;
        this.classes = classes;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPage() {
        return this.page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRange() {
        return this.range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getComponents() {
        return this.components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getRitual() {
        return this.ritual;
    }

    public void setRitual(String ritual) {
        this.ritual = ritual;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getConcentration() {
        return this.concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getCastingTime() {
        return this.castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClasses() {
        return this.classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Spell name(String name) {
        setName(name);
        return this;
    }

    public Spell desc(String desc) {
        setDesc(desc);
        return this;
    }

    public Spell page(String page) {
        setPage(page);
        return this;
    }

    public Spell range(String range) {
        setRange(range);
        return this;
    }

    public Spell components(String components) {
        setComponents(components);
        return this;
    }

    public Spell material(String material) {
        setMaterial(material);
        return this;
    }

    public Spell ritual(String ritual) {
        setRitual(ritual);
        return this;
    }

    public Spell duration(String duration) {
        setDuration(duration);
        return this;
    }

    public Spell concentration(String concentration) {
        setConcentration(concentration);
        return this;
    }

    public Spell castingTime(String castingTime) {
        setCastingTime(castingTime);
        return this;
    }

    public Spell level(String level) {
        setLevel(level);
        return this;
    }

    public Spell school(String school) {
        setSchool(school);
        return this;
    }

    public Spell classes(String classes) {
        setClasses(classes);
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
        return Objects.equals(name, spell.name) && Objects.equals(desc, spell.desc) && Objects.equals(page, spell.page) && Objects.equals(range, spell.range) && Objects.equals(components, spell.components) && Objects.equals(material, spell.material) && Objects.equals(ritual, spell.ritual) && Objects.equals(duration, spell.duration) && Objects.equals(concentration, spell.concentration) && Objects.equals(castingTime, spell.castingTime) && Objects.equals(level, spell.level) && Objects.equals(school, spell.school) && Objects.equals(classes, spell.classes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, page, range, components, material, ritual, duration, concentration, castingTime, level, school, classes);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", desc='" + getDesc() + "'" +
            ", page='" + getPage() + "'" +
            ", range='" + getRange() + "'" +
            ", components='" + getComponents() + "'" +
            ", material='" + getMaterial() + "'" +
            ", ritual='" + getRitual() + "'" +
            ", duration='" + getDuration() + "'" +
            ", concentration='" + getConcentration() + "'" +
            ", castingTime='" + getCastingTime() + "'" +
            ", level='" + getLevel() + "'" +
            ", school='" + getSchool() + "'" +
            ", classes='" + getClasses() + "'" +
            "}";
    }
}
