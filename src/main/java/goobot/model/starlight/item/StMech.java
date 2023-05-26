/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.StRarity;
import goobot.Constants.StItemType;

public class StMech extends StItem {
    private String url;
    
    public StMech(StItemType type, String name, StRarity rarity, String description, String weight, Integer price, String url) {
        super(type, name, rarity, description, weight, price);
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }

    @Override
    public String toString(){
        return String.format("```ansi\n%s / %s / %d credits```", getName(), getFormattedRarity(), getPrice());
    }
}
