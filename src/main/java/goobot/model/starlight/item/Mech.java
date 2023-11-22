/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.Rarity;
import goobot.Constants.ItemType;

public class Mech extends Item {
    private String url;
    
    public Mech(ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String url) {
        super(type, name, rarity, description, weight, price);
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }

    @Override
    public String toString(){
        return String.format("```ansi\n%s / %s / %d CYD```", getName(), getFormattedRarity(), getPrice());
    }
}
