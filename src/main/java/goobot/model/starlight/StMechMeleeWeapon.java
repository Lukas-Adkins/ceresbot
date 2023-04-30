package goobot.model.starlight;
import goobot.Constants.StItemType;
import goobot.Constants.StRarity;
import java.util.Objects;

public class StMechMeleeWeapon extends StMeleeWeapon {
    private String mechSlot;
    private String mechLocation;

    public StMechMeleeWeapon(
        StItemType type, String name, StRarity rarity, String description, String weight, 
        Integer price, String weaponTypes, String range, String dmg, String pen, String mechSlot, String mechLocation){
        super(type, name, rarity, description, weight, price, weaponTypes, range, dmg, pen);
        this.mechSlot = mechSlot;
        this.mechLocation = mechLocation;
    }

    public String getMechSlot() {
        return this.mechSlot;
    }

    public void setMechSlot(String mechSlot) {
        this.mechSlot = mechSlot;
    }

    public String getMechLocation() {
        return this.mechLocation;
    }

    public void setMechLocation(String mechLocation) {
        this.mechLocation = mechLocation;
    }

    public StMechMeleeWeapon mechSlot(String mechSlot) {
        setMechSlot(mechSlot);
        return this;
    }

    public StMechMeleeWeapon mechLocation(String mechLocation) {
        setMechLocation(mechLocation);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mechSlot, mechLocation);
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %d credits /\n%s / Locations: [%s]\nRange: %s / Damage: %s / Pen: %s \n%s```",
        getName(), getFormattedRarity(), getPrice(), mechSlot, mechLocation, getRange() , getDmg(), getPen(), getDescription());
    }
}
