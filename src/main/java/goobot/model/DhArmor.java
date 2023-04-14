/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.model;

import java.util.Objects;

import goobot.Constants.DhRarity;
import goobot.Constants.DhItemType;

public class DhArmor extends DhItem {
    private String covers;
    private Integer AP;
    private Integer maxAgility;

    public DhArmor(DhItemType type, String name, String rarity, String description, String weight, Integer price, String covers, Integer AP, Integer maxAgility) {
        super(type, name, rarity, description, weight, price);
        this.covers = covers;
        this.AP = AP;
        this.maxAgility = maxAgility;
    }

    public String getCovers() {
        return this.covers;
    }

    public void setCovers(String covers) {
        this.covers = covers;
    }

    public Integer getAP() {
        return this.AP;
    }

    public void setAP(Integer AP) {
        this.AP = AP;
    }

    public Integer getMaxAgility() {
        return this.maxAgility;
    }

    public void setMaxAgility(Integer maxAgility) {
        this.maxAgility = maxAgility;
    }

    public DhArmor covers(String covers) {
        setCovers(covers);
        return this;
    }

    public DhArmor AP(Integer AP) {
        setAP(AP);
        return this;
    }

    public DhArmor maxAgility(Integer maxAgility) {
        setMaxAgility(maxAgility);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DhArmor)) {
            return false;
        }
        DhArmor dhArmor = (DhArmor) o;
        return Objects.equals(covers, dhArmor.covers) && Objects.equals(AP, dhArmor.AP) && Objects.equals(maxAgility, dhArmor.maxAgility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(covers, AP, maxAgility);
    }


    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %d credits /\nCovers: %s / AP: %d / Max Agility: %d\n%s```", getName(), getFormattedRarity(), getPrice(), covers , AP, maxAgility, getDescription());
    }
}
