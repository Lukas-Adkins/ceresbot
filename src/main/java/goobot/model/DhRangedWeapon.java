/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.model;

import java.util.List;
import java.util.Objects;
import goobot.Constants.DhRarity;
import goobot.Constants.DhWeaponType;
import goobot.Constants.DhItemType;

public class DhRangedWeapon extends DhItem {
    private String weaponTypes;
    private String range;
    private String RoF;
    private String dmg;
    private String pen;
    private String mag;
    private String reloadTime;

    public DhRangedWeapon(DhItemType type, String name, String rarity, String description, String weight, Integer price, String weaponTypes, String range, String RoF, String dmg, String pen, String mag, String reloadTime) {
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

    public DhRangedWeapon weaponTypes(String weaponTypes) {
        setWeaponTypes(weaponTypes);
        return this;
    }

    public DhRangedWeapon range(String range) {
        setRange(range);
        return this;
    }

    public DhRangedWeapon RoF(String RoF) {
        setRoF(RoF);
        return this;
    }

    public DhRangedWeapon dmg(String dmg) {
        setDmg(dmg);
        return this;
    }

    public DhRangedWeapon pen(String pen) {
        setPen(pen);
        return this;
    }

    public DhRangedWeapon mag(String mag) {
        setMag(mag);
        return this;
    }

    public DhRangedWeapon reloadTime(String reloadTime) {
        setReloadTime(reloadTime);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DhRangedWeapon)) {
            return false;
        }
        DhRangedWeapon dhRangedWeapon = (DhRangedWeapon) o;
        return Objects.equals(weaponTypes, dhRangedWeapon.weaponTypes) && Objects.equals(range, dhRangedWeapon.range) && Objects.equals(RoF, dhRangedWeapon.RoF) && Objects.equals(dmg, dhRangedWeapon.dmg) && Objects.equals(pen, dhRangedWeapon.pen) && Objects.equals(mag, dhRangedWeapon.mag) && Objects.equals(reloadTime, dhRangedWeapon.reloadTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weaponTypes, range, RoF, dmg, pen, mag, reloadTime);
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s / %d credits /\n%s / Range: %s / RoF: %s / Damage: %s / Pen: %s / Mag: %s / Rld: %s\n%s```", getName(), getFormattedRarity(), getWeight(), getPrice(), weaponTypes, range , RoF, dmg, pen, mag, reloadTime, getDescription());
    }
}
