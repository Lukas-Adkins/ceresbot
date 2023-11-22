/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.ItemType;
import goobot.Constants.Rarity;

public class MechSystem extends Item {
    private String mechSlot;
    private String mechLocation;

    public MechSystem(ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String mechSlot, String mechLocation){
        super(type, name, rarity, description, weight, price);
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
        return String.format("```ansi\n%s / %s / %d CYD\n%s / Locations: [%s]\n%s```",
        getName(), getFormattedRarity(), getPrice(), mechSlot, mechLocation, getDescription());
    }
}
