/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.Rarity;
import goobot.Constants.ItemType;

/**
 * Represents a ranged weapon.
 */
public class RangedWeapon extends Item {
    private static final String ANSI_FORMAT = "```ansi\n%s / %s / %s / %d CYD\n%s / Range: %s / RoF: %s / Damage: %s / Pen: %s / Mag: %s / Rld: %s\n%s```";

    private String weaponTypes;
    private String range;
    private String RoF;
    private String dmg;
    private String pen;
    private String mag;
    private String reloadTime;

    /**
     * Represents a ranged weapon, extending the base item class with additional attributes specific to ranged weapons.
     * 
     * @param type The type of the ranged weapon (e.g., pistol, rifle).
     * @param name The name of the ranged weapon.
     * @param rarity The rarity of the ranged weapon.
     * @param description A brief description of the ranged weapon.
     * @param weight The weight of the ranged weapon.
     * @param price The price of the ranged weapon.
     * @param weaponTypes The types of the ranged weapon (e.g., automatic, semi-automatic).
     * @param range The effective range of the ranged weapon.
     * @param RoF The rate of fire of the ranged weapon.
     * @param dmg The damage inflicted by the ranged weapon.
     * @param pen The penetration power of the ranged weapon.
     * @param mag The magazine capacity of the ranged weapon.
     * @param reloadTime The time it takes to reload the ranged weapon.
     */
    public RangedWeapon(
        ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String weaponTypes, 
        String range, String RoF, String dmg, String pen, String mag, String reloadTime) {
        super(type, name, rarity, description, weight, price);
        this.weaponTypes = weaponTypes;
        this.range = range;
        this.RoF = RoF;
        this.dmg = dmg;
        this.pen = pen;
        this.mag = mag;
        this.reloadTime = reloadTime;
    }

    /**
     * Gets the types of the ranged weapon (e.g., automatic, semi-automatic).
     *
     * @return The weapon types.
     */
    public String getWeaponTypes() {
        return this.weaponTypes;
    }

    /**
     * Sets the types of the ranged weapon.
     *
     * @param weaponTypes The new weapon types to set.
     */
    public void setWeaponTypes(String weaponTypes) {
        this.weaponTypes = weaponTypes;
    }

    /**
     * Gets the effective range of the ranged weapon.
     *
     * @return The effective range.
     */
    public String getRange() {
        return this.range;
    }

    /**
     * Sets the effective range of the ranged weapon.
     *
     * @param range The new effective range to set.
     */
    public void setRange(String range) {
        this.range = range;
    }

    /**
     * Gets the rate of fire of the ranged weapon.
     *
     * @return The rate of fire.
     */
    public String getRoF() {
        return this.RoF;
    }

    /**
     * Sets the rate of fire of the ranged weapon.
     *
     * @param RoF The new rate of fire to set.
     */
    public void setRoF(String RoF) {
        this.RoF = RoF;
    }

    /**
     * Gets the damage inflicted by the ranged weapon.
     *
     * @return The damage.
     */
    public String getDmg() {
        return this.dmg;
    }

    /**
     * Sets the damage inflicted by the ranged weapon.
     *
     * @param dmg The new damage to set.
     */
    public void setDmg(String dmg) {
        this.dmg = dmg;
    }

    /**
     * Gets the penetration power of the ranged weapon.
     *
     * @return The penetration power.
     */
    public String getPen() {
        return this.pen;
    }

    /**
     * Sets the penetration power of the ranged weapon.
     *
     * @param pen The new penetration power to set.
     */
    public void setPen(String pen) {
        this.pen = pen;
    }

    /**
     * Gets the magazine capacity of the ranged weapon.
     *
     * @return The magazine capacity.
     */
    public String getMag() {
        return this.mag;
    }

    /**
     * Sets the magazine capacity of the ranged weapon.
     *
     * @param mag The new magazine capacity to set.
     */
    public void setMag(String mag) {
        this.mag = mag;
    }

    /**
     * Gets the reload time of the ranged weapon.
     *
     * @return The reload time.
     */
    public String getReloadTime() {
        return this.reloadTime;
    }

    /**
     * Sets the reload time of the ranged weapon.
     *
     * @param reloadTime The new reload time to set.
     */
    public void setReloadTime(String reloadTime) {
        this.reloadTime = reloadTime;
    }


    @Override
    public String toString() {
        return String.format(ANSI_FORMAT, getName(), getFormattedRarity(), getWeight(), getPrice(), weaponTypes, range , RoF, dmg, pen, mag, reloadTime,  getDescription() != null ? getDescription() : "");
    }
}
