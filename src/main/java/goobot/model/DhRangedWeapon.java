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
    private Integer pen;
    private Integer mag;
    private String reloadTime;

    public DhRangedWeapon(DhItemType type, String name, String rarity, String description, String weight, Integer price, String weaponTypes, String range, String RoF, String dmg, Integer pen, Integer mag, String reloadTime) {
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

    public Integer getPen() {
        return this.pen;
    }

    public void setPen(Integer pen) {
        this.pen = pen;
    }

    public Integer getMag() {
        return this.mag;
    }

    public void setMag(Integer mag) {
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

    public DhRangedWeapon pen(Integer pen) {
        setPen(pen);
        return this;
    }

    public DhRangedWeapon mag(Integer mag) {
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
        DhRangedWeapon dhWeapon = (DhRangedWeapon) o;
        return Objects.equals(weaponTypes, dhWeapon.weaponTypes) && Objects.equals(range, dhWeapon.range) && Objects.equals(RoF, dhWeapon.RoF) && Objects.equals(dmg, dhWeapon.dmg) && Objects.equals(pen, dhWeapon.pen) && Objects.equals(mag, dhWeapon.mag) && Objects.equals(reloadTime, dhWeapon.reloadTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weaponTypes, range, RoF, dmg, pen, mag, reloadTime);
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s / %d credits /\n%s / Range: %s / RoF: %s / Damage: %s / Pen: %d / Mag: %d / Rld: %s\n%s```", getName(), getFormattedRarity(), getWeight(), getPrice(), weaponTypes, range , RoF, dmg, pen, mag, reloadTime, getDescription());
    }
}
