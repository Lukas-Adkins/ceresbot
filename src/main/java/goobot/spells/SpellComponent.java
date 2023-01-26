/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.spells;
import java.util.Objects;

public class SpellComponent {
    private Boolean material;
    private String raw;
    private Boolean somatic;
    private Boolean verbal;

    public SpellComponent() {}

    public SpellComponent(Boolean material, String raw, Boolean somatic, Boolean verbal) {
        this.material = material;
        this.raw = raw;
        this.somatic = somatic;
        this.verbal = verbal;
    }

    public Boolean isMaterial() {
        return this.material;
    }

    public Boolean getMaterial() {
        return this.material;
    }

    public void setMaterial(Boolean material) {
        this.material = material;
    }

    public String getRaw() {
        return this.raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Boolean isSomatic() {
        return this.somatic;
    }

    public Boolean getSomatic() {
        return this.somatic;
    }

    public void setSomatic(Boolean somatic) {
        this.somatic = somatic;
    }

    public Boolean isVerbal() {
        return this.verbal;
    }

    public Boolean getVerbal() {
        return this.verbal;
    }

    public void setVerbal(Boolean verbal) {
        this.verbal = verbal;
    }

    public SpellComponent material(Boolean material) {
        setMaterial(material);
        return this;
    }

    public SpellComponent raw(String raw) {
        setRaw(raw);
        return this;
    }

    public SpellComponent somatic(Boolean somatic) {
        setSomatic(somatic);
        return this;
    }

    public SpellComponent verbal(Boolean verbal) {
        setVerbal(verbal);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SpellComponent)) {
            return false;
        }
        SpellComponent spellComponent = (SpellComponent) o;
        return Objects.equals(material, spellComponent.material) && Objects.equals(raw, spellComponent.raw) && Objects.equals(somatic, spellComponent.somatic) && Objects.equals(verbal, spellComponent.verbal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, raw, somatic, verbal);
    }

    @Override
    public String toString() {
        return "{" +
            " material='" + isMaterial() + "'" +
            ", raw='" + getRaw() + "'" +
            ", somatic='" + isSomatic() + "'" +
            ", verbal='" + isVerbal() + "'" +
            "}";
    }

}
