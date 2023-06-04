/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.Rarity;
import goobot.Constants.ItemType;

public class RangedWeapon extends Item {
    private String weaponTypes;
    private String range;
    private String RoF;
    private String dmg;
    private String pen;
    private String mag;
    private String reloadTime;

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

    public String getWeaponTypes() {
        return this.weaponTypes;
    }

    public void setWeaponTypes(String weaponTypes) {
        this.weaponTypes = weaponTypes;
    }

    public String getRange() {
        return this.range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getRoF() {
        return this.RoF;
    }

    public void setRoF(String RoF) {
        this.RoF = RoF;
    }

    public String getDmg() {
        return this.dmg;
    }

    public void setDmg(String dmg) {
        this.dmg = dmg;
    }

    public String getPen() {
        return this.pen;
    }

    public void setPen(String pen) {
        this.pen = pen;
    }

    public String getMag() {
        return this.mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public String getReloadTime() {
        return this.reloadTime;
    }

    public void setReloadTime(String reloadTime) {
        this.reloadTime = reloadTime;
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s / %d credits\n%s / Range: %s / RoF: %s / Damage: %s / Pen: %s / Mag: %s / Rld: %s\n%s```", getName(), getFormattedRarity(), getWeight(), getPrice(), weaponTypes, range , RoF, dmg, pen, mag, reloadTime, getDescription());
    }
}
