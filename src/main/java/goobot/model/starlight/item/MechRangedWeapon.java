/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.ItemType;
import goobot.Constants.Rarity;

public class MechRangedWeapon extends RangedWeapon {
    private static final String ANSI_FORMAT = "```ansi\n%s / %s / %d CYD\n%s / Locations: [%s]\n%s / Range: %s / RoF: %s / Damage: %s / Pen: %s / Mag: %s / Rld: %s\n%s```";

    private String mechSlot;
    private String mechLocation;

    /**
     * Represents a ranged weapon designed for mechs, combining the attributes of both a mech system
     * and a ranged weapon. Inherits attributes such as type, name, rarity, description, weight, price,
     * weapon types, range, rate of fire (RoF), damage (dmg), penetration (pen), magazine size (mag),
     * and reload time from the RangedWeapon class.
     *
     * @param type           The type of the mech ranged weapon (e.g., ballistic, energy).
     * @param name           The name of the mech ranged weapon.
     * @param rarity         The rarity of the mech ranged weapon.
     * @param description    A brief description of the mech ranged weapon.
     * @param weight         The weight of the mech ranged weapon.
     * @param price          The price of the mech ranged weapon.
     * @param weaponTypes    The types of weapons compatible with the mech ranged weapon.
     * @param range          The effective range of the mech ranged weapon.
     * @param RoF            The rate of fire of the mech ranged weapon.
     * @param dmg            The damage dealt by the mech ranged weapon.
     * @param pen            The penetration ability of the mech ranged weapon.
     * @param mag            The magazine size of the mech ranged weapon.
     * @param reloadTime     The time it takes to reload the mech ranged weapon.
     * @param mechSlot       The specific slot on the mech where the weapon system can be installed.
     * @param mechLocation   The locations on the mech where the weapon system can be installed.
     */
    public MechRangedWeapon(
        ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String weaponTypes,
        String range, String RoF, String dmg, String pen, String mag, String reloadTime, String mechSlot, String mechLocation
    ) {
        super(type, name, rarity, description, weight, price, weaponTypes, range, RoF, dmg, pen, mag, reloadTime);
        this.mechSlot = mechSlot;
        this.mechLocation = mechLocation;
    }

    /**
    * Retrieves the specific slot on the mech where the weapon system can be installed.
    *
    * @return The mech slot of the weapon system.
    */
    public String getMechSlot() {
        return this.mechSlot;
    }

    /**
    * Sets the specific slot on the mech where the weapon system can be installed.
    *
    * @param mechSlot The new mech slot to set.
    */
    public void setMechSlot(String mechSlot) {
        this.mechSlot = mechSlot;
    }

    /**
    * Retrieves the locations on the mech where the weapon system can be installed.
    *
    * @return The mech locations where the weapon system can be installed.
    */
    public String getMechLocation() {
        return this.mechLocation;
    }

    /**
    * Sets the locations on the mech where the weapon system can be installed.
    *
    * @param mechLocation The new mech locations to set.
    */
    public void setMechLocation(String mechLocation) {
        this.mechLocation = mechLocation;
    }

    /**
    * Returns a formatted string representation of the mech ranged weapon, including its name, formatted rarity,
    * price, mech slot, mech locations, weapon types, range, rate of fire, damage, penetration, magazine size,
    * reload time, and description. Overrides the toString method of the RangedWeapon class.
    *
    * @return A string representation of the mech ranged weapon.
    */
    @Override
    public String toString() {
        return String.format(ANSI_FORMAT, getName(), getFormattedRarity(), getPrice(), mechSlot, mechLocation,
            getWeaponTypes(), getRange(), getRoF(), getDmg(), getPen(), getMag(), getReloadTime(),
            getDescription());
    }
}
