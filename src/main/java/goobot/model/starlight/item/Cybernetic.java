/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.Rarity;
import goobot.Constants.ItemType;

public class Cybernetic extends Item {
    private String slots;

    public Cybernetic(ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String slots) {
        super(type, name, rarity, description, weight, price);
        this.slots = slots;
    }

    public String getSlots() {
        return this.slots;
    }

    public void setSlots(String slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s cyber-slots / %d CYD\n%s```", getName(), getFormattedRarity(), slots, getPrice(), getDescription());
    }
}
