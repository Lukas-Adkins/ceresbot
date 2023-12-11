/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.Rarity;
import goobot.Constants.ItemType;

/**
 * Represents a mech item.
 */
public class Mech extends Item {
    private static final String ANSI_FORMAT = "```ansi\n%s / %s / %d CYD```";

    private String url;

    /**
     * Represents a mech, a specialized type of item with additional properties including a URL
     * providing further information or visual representation of the mech.
     * Inherits attributes such as type, name, rarity, description, weight, and price from the Item class.
     *
     * @param type        The type of the mech (e.g., light, medium, heavy).
     * @param name        The name of the mech.
     * @param rarity      The rarity of the mech.
     * @param description A brief description of the mech.
     * @param weight      The weight of the mech.
     * @param price       The price of the mech.
     * @param url         The URL providing additional information or visual representation of the mech.
     */
    public Mech(ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String url) {
        super(type, name, rarity, description, weight, price);
        this.url = url;
    }

    /**
     * Retrieves the URL associated with the mech, providing additional information or visual representation.
     *
     * @return The URL of the mech.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Returns a formatted string representation of the mech, including its name, formatted rarity, and price.
     * Overrides the toString method of the Item class.
     *
     * @return A string representation of the mech.
     */
    @Override
    public String toString() {
        return String.format(ANSI_FORMAT, getName(), getFormattedRarity(), getPrice());
    }
}
