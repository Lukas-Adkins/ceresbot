package goobot.model.starlight;
import goobot.Constants.StItemType;
import goobot.Constants.StRarity;
import java.util.Objects;

public class StMechRangedWeapon extends StRangedWeapon {
    private String mechSlot;
    private String mechLocation;

    public StMechRangedWeapon(
        StItemType type, String name, StRarity rarity, String description, String weight, Integer price, String weaponTypes, 
        String range, String RoF, String dmg, String pen, String mag, String reloadTime, String mechSlot, String mechLocation
    ){
        super(type, name, rarity, description, weight, price, weaponTypes, range, RoF, dmg, pen, mag, reloadTime);
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

    public StMechRangedWeapon mechSlot(String mechSlot) {
        setMechSlot(mechSlot);
        return this;
    }

    public StMechRangedWeapon mechLocation(String mechLocation) {
        setMechLocation(mechLocation);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mechSlot, mechLocation);
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s / %d credits /\n%s / Locations: [%s]\n%s / Range: %s / RoF: %s / Damage: %s / Pen: %s / Mag: %s / Rld: %s\n%s```", 
        getName(), getFormattedRarity(), getWeight(), getPrice(), mechSlot, mechLocation, getWeaponTypes(), getRange() , getRoF(), getDmg(), getPen(), getMag(), 
        getReloadTime(), getDescription());
    }
}
