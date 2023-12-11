/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.model.starlight.item;

import goobot.Constants.ItemType;
import goobot.Constants.Rarity;

/**
 * Represents a melee weapon.
 */
public class MeleeWeapon extends Item {
    private static final String ANSI_FORMAT = "```ansi\n%s / %s / %d CYD\nRange: %s / Damage: %s / Pen: %s \n%s```";
    
    private String weaponTypes;
    private String range;
    private String dmg;
    private String pen;

    /**
     * Represents a melee weapon, a specialized type of item designed for close combat, with attributes
     * including weapon types, range, damage (dmg), and penetration (pen). Inherits attributes such as
     * type, name, rarity, description, weight, and price from the Item class.
     *
     * @param type        The type of the melee weapon (e.g., sword, axe, mace).
     * @param name        The name of the melee weapon.
     * @param rarity      The rarity of the melee weapon.
     * @param description A brief description of the melee weapon.
     * @param weight      The weight of the melee weapon.
     * @param price       The price of the melee weapon.
     * @param weaponTypes The types of weapons associated with the melee weapon.
     * @param range       The effective range of the melee weapon.
     * @param dmg         The damage dealt by the melee weapon.
     * @param pen         The penetration ability of the melee weapon.
     */
    public MeleeWeapon(ItemType type, String name, Rarity rarity, String description, String weight, Integer price,
    String weaponTypes, String range, String dmg, String pen) {
        super(type, name, rarity, description, weight, price);
        this.weaponTypes = weaponTypes;
        this.range = range;
        this.dmg = dmg;
        this.pen = pen;
    }

    /**
    * Retrieves the types of weapons associated with the melee weapon.
    *
    * @return The weapon types of the melee weapon.
    */
    public String getWeaponTypes() {
        return this.weaponTypes;
    }

    /**
    * Sets the types of weapons associated with the melee weapon.
    *
    * @param weaponTypes The new weapon types to set.
    */
    public void setWeaponTypes(String weaponTypes) {
        this.weaponTypes = weaponTypes;
    }

    /**
    * Retrieves the effective range of the melee weapon.
    *
    * @return The range of the melee weapon.
    */
    public String getRange() {
        return this.range;
    }

    /**
    * Sets the effective range of the melee weapon.
    *
    * @param range The new range to set.
    */
    public void setRange(String range) {
        this.range = range;
    }

    /**
    * Retrieves the damage dealt by the melee weapon.
    *
    * @return The damage of the melee weapon.
    */
    public String getDmg() {
        return this.dmg;
    }

    /**
    * Sets the damage dealt by the melee weapon.
    *
    * @param dmg The new damage to set.
    */
    public void setDmg(String dmg) {
        this.dmg = dmg;
    }

    /**
    * Retrieves the penetration ability of the melee weapon.
    *
    * @return The penetration of the melee weapon.
    */
    public String getPen() {
        return this.pen;
    }

    /**
    * Sets the penetration ability of the melee weapon.
    *
    * @param pen The new penetration to set.
    */
    public void setPen(String pen) {
        this.pen = pen;
    }

    /**
    * Returns a formatted string representation of the melee weapon, including its name, formatted rarity,
    * price, range, damage, penetration, and description. Overrides the toString method of the Item class.
    *
    * @return A string representation of the melee weapon.
    */
    @Override
    public String toString() {
        return String.format(ANSI_FORMAT, getName(), getFormattedRarity(), getPrice(), range, dmg, pen,
        getDescription() != null ? getDescription() : "");
    }
}
