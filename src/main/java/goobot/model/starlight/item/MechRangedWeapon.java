/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.ItemType;
import goobot.Constants.Rarity;

public class MechRangedWeapon extends RangedWeapon {
    private String mechSlot;
    private String mechLocation;

    public MechRangedWeapon(
        ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String weaponTypes, 
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

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %d CYD\n%s / Locations: [%s]\n%s / Range: %s / RoF: %s / Damage: %s / Pen: %s / Mag: %s / Rld: %s\n%s```", 
        getName(), getFormattedRarity(), getPrice(), mechSlot, mechLocation, getWeaponTypes(), getRange() , getRoF(), getDmg(), getPen(), getMag(), 
        getReloadTime(), getDescription());
    }
}
