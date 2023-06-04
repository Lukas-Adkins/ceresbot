/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.model.starlight.item;

import goobot.Constants.ItemType;
import goobot.Constants.Rarity;

public class Armor extends Item {
    private String covers;
    private String AP;
    private String maxAgility;

    public Armor(ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String covers, String AP, String maxAgility) {
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

    public String getAP() {
        return this.AP;
    }

    public void setAP(String AP) {
        this.AP = AP;
    }

    public String getMaxAgility() {
        return this.maxAgility;
    }

    public void setMaxAgility(String maxAgility) {
        this.maxAgility = maxAgility;
    }

    public Armor covers(String covers) {
        setCovers(covers);
        return this;
    }

    @Override
    public String toString(){
        return String.format("```ansi\n%s / %s / %d credits\nCovers: %s / AP: %s / Max Agility: %s\n%s```", getName(), getFormattedRarity(), getPrice(), covers , AP, maxAgility, getDescription());
    }

}
