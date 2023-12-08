/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.model.starlight.item;

import goobot.Constants.ItemType;
import goobot.Constants.Rarity;

/**
 * Represents an armor item.
 */
public class Armor extends Item {
    private static final String ANSI_FORMAT = "```ansi\n%s / %s / %d CYD\nCovers: %s / AP: %s / Max Agility: %s\n%s```";

    private String covers;
    private String AP;
    private String maxAgility;

    /**
     * Constructs a new Armor object with the specified attributes.
     *
     * @param type The type of armor.
     * @param name The name of the armor.
     * @param rarity The rarity of the armor.
     * @param description A description of the armor.
     * @param weight The weight of the armor.
     * @param price The price of the armor.
     * @param covers The coverage provided by the armor.
     * @param AP The armor points (AP) of the armor.
     * @param maxAgility The maximum agility supported by the armor.
    */
    public Armor(ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String covers, String AP, String maxAgility) {
        super(type, name, rarity, description, weight, price);
        this.covers = covers;
        this.AP = AP;
        this.maxAgility = maxAgility;
    }

    /**
     * Gets the coverage provided by the armor.
     *
     * @return The coverage of the armor.
     */
    public String getCovers() {
        return this.covers;
    }

    /**
     * Sets the coverage provided by the armor.
     *
     * @param covers The new coverage value to set.
     */
    public void setCovers(String covers) {
        this.covers = covers;
    }

    /**
     * Gets the armor points (AP) of the armor.
     *
     * @return The armor points of the armor.
     */
    public String getAP() {
        return this.AP;
    }

    /**
     * Sets the armor points (AP) of the armor.
     *
     * @param AP The new armor points value to set.
     */
    public void setAP(String AP) {
        this.AP = AP;
    }

    /**
     * Gets the maximum agility supported by the armor.
     *
     * @return The maximum agility of the armor.
     */
    public String getMaxAgility() {
        return this.maxAgility;
    }

    /**
     * Sets the maximum agility supported by the armor.
     *
     * @param maxAgility The new maximum agility value to set.
     */
    public void setMaxAgility(String maxAgility) {
        this.maxAgility = maxAgility;
    }


    /**
     * Returns a formatted string representation of the object suitable for displaying in Discord.
     * The string is formatted as an ANSI code block and includes information about the object's name,
     * rarity, price, covers, armor points (AP), maximum agility, and description.
     *
     * The format is as follows:
     * ```
     * ansi
     * {name} / {formattedRarity} / {price} CYD
     * Covers: {covers} / AP: {AP} / Max Agility: {maxAgility}
     * {description}
     * ```
     *
     * If any of the properties (name, formattedRarity, description, etc.) is null, an empty string is used
     * to prevent NullPointerException in the formatted output.
     *
     * @return A formatted string suitable for displaying in Discord.
     */
    @Override
    public String toString() {
        return String.format(ANSI_FORMAT, getName(), getFormattedRarity(), getPrice(), covers, AP, maxAgility, getDescription() != null ? getDescription() : "");
    }
}
