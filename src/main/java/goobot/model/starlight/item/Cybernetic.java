/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.Rarity;
import goobot.Constants.ItemType;

/**
 * Represents a cybernetic item.
 */
public class Cybernetic extends Item {
    private static final String ANSI_FORMAT = "```ansi\n%s / %s / %s cyber-slots / %d CYD\n%s```";

    private String slots;

    /**
     * Constructs a new Cybernetic object with the specified attributes.
     *
     * @param type        The type of cybernetic item.
     * @param name        The name of the cybernetic item.
     * @param rarity      The rarity of the cybernetic item.
     * @param description A description of the cybernetic item.
     * @param weight      The weight of the cybernetic item.
     * @param price       The price of the cybernetic item.
     * @param slots       The number of cyber-slots this item uses.
     */
    public Cybernetic(ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String slots) {
        super(type, name, rarity, description, weight, price);
        this.slots = slots;
    }

    /**
     * Gets the number of cyber-slots this item uses.
     *
     * @return The number of cyber-slots.
     */
    public String getSlots() {
        return this.slots;
    }

    /**
     * Sets the number of cyber-slots available for enhancements.
     *
     * @param slots The new number of cyber-slots to set.
     */
    public void setSlots(String slots) {
        this.slots = slots;
    }

    /**
     * Returns a formatted string representation of the cybernetic item suitable for displaying in Discord.
     *
     * @return A formatted string suitable for displaying in Discord.
     */

    @Override
    public String toString() {
        return String.format(ANSI_FORMAT, getName(), getFormattedRarity(), slots, getPrice(), getDescription() != null ? getDescription() : "");
    }
}
