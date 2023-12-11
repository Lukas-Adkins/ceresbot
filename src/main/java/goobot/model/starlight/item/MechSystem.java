/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.ItemType;
import goobot.Constants.Rarity;

/**
 * Represents a mech system.
 */
public class MechSystem extends Item {
    private static final String ANSI_FORMAT = "```ansi\n%s / %s / %d CYD\n%s / Locations: [%s]\n%s```";

    private String mechSlot;
    private String mechLocation;

    /**
     * Represents a mech system, a specialized type of item with additional properties including
     * the mech slot and location where the system can be installed on a mech.
     * Inherits attributes such as type, name, rarity, description, weight, and price from the Item class.
     *
     * @param type          The type of the mech system (e.g., targeting, propulsion, shielding).
     * @param name          The name of the mech system.
     * @param rarity        The rarity of the mech system.
     * @param description   A brief description of the mech system.
     * @param weight        The weight of the mech system.
     * @param price         The price of the mech system.
     * @param mechSlot      The specific slot on the mech where the system can be installed.
     * @param mechLocation  The locations on the mech where the system can be installed.
     */
    public MechSystem(ItemType type, String name, Rarity rarity, String description, String weight, Integer price,
    String mechSlot, String mechLocation) {
        super(type, name, rarity, description, weight, price);
        this.mechSlot = mechSlot;
        this.mechLocation = mechLocation;
    }

    /**
    * Retrieves the specific slot on the mech where the system can be installed.
    *
    * @return The mech slot of the system.
    */
    public String getMechSlot() {
    return this.mechSlot;
    }

    /**
    * Sets the specific slot on the mech where the system can be installed.
    *
    * @param mechSlot The new mech slot to set.
    */
    public void setMechSlot(String mechSlot) {
    this.mechSlot = mechSlot;
    }

    /**
    * Retrieves the locations on the mech where the system can be installed.
    *
    * @return The mech locations where the system can be installed.
    */
    public String getMechLocation() {
    return this.mechLocation;
    }

    /**
    * Sets the locations on the mech where the system can be installed.
    *
    * @param mechLocation The new mech locations to set.
    */
    public void setMechLocation(String mechLocation) {
    this.mechLocation = mechLocation;
    }

    /**
    * Returns a formatted string representation of the mech system, including its name, formatted rarity, price,
    * mech slot, and mech locations where the system can be installed.
    * Overrides the toString method of the Item class.
    *
    * @return A string representation of the mech system.
    */
    @Override
        public String toString() {
        return String.format(ANSI_FORMAT,
        getName(), getFormattedRarity(), getPrice(), mechSlot, mechLocation,
        getDescription() != null ? getDescription() : "");
    }
}
