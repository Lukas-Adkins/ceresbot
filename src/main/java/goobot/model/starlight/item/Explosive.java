/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.Rarity;
import goobot.Constants.ItemType;

/**
 * Represents an explosive item.
 */
public class Explosive extends RangedWeapon {
    private static final String ANSI_FORMAT = "```ansi\n%s / %s / %s / %d CYD\n%s / Range: %s / Damage: %s / Pen: %s \n%s```";

    /**
     * Constructs a new Explosive object with the specified attributes.
     *
     * @param type        The type of explosive item.
     * @param name        The name of the explosive item.
     * @param rarity      The rarity of the explosive item.
     * @param description A description of the explosive item.
     * @param weight      The weight of the explosive item.
     * @param price       The price of the explosive item.
     * @param weaponTypes The types of weapons compatible with the explosive item.
     * @param range       The effective range of the explosive item.
     * @param RoF         The rate of fire of the explosive item.
     * @param dmg         The damage caused by the explosive item.
     * @param pen         The penetration capability of the explosive item.
     * @param mag         The magazine capacity of the explosive item.
     * @param reloadTime  The time it takes to reload the explosive item.
     */
    public Explosive(ItemType type, String name, Rarity rarity, String description, String weight, Integer price,
    String weaponTypes, String range, String RoF, String dmg, String pen, String mag, String reloadTime) {
        super(type, name, rarity, description, weight, price, weaponTypes, range, RoF, dmg, pen, mag, reloadTime);
    }

    /**
     * Returns a formatted string representation of the explosive item suitable for display in Discord.
     * The format includes the name, formatted rarity, weight, price, weapon types, effective range,
     * damage, penetration capability, and description of the explosive item.
     *
     * The format is as follows:
     * ```
     * ansi
     * {name} / {formattedRarity} / {weight} / {price} CYD
     * Weapon Types: {weaponTypes} / Range: {range} / Damage: {dmg} / Penetration: {pen}
     * {description}
     * ```
     *
     * If the description is null, an empty string is used to prevent NullPointerException in the formatted output.
     *
     * @return A formatted string suitable for displaying in Discord.
     */
    @Override
    public String toString() {
        return String.format(ANSI_FORMAT, getName(), getFormattedRarity(), getWeight(), getPrice(),
                getWeaponTypes(), getRange(), getDmg(), getPen(), getDescription() != null ? getDescription() : "");
    }
}
