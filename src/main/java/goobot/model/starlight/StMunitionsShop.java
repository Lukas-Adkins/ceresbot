package goobot.model.starlight;

import goobot.Constants.StRarity;
import goobot.model.WeightedRandomBag;
import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.ArrayList;

public class StMunitionsShop implements StShop {
    private WeightedRandomBag<StItemType> randomTypePicker;
    private HashMap<StItemType, ArrayList<StItem>> itemsByType;

    private static final double 
    EXPLOSIVE_WEIGHT = 0.5,
    WEAPON_MOD_WEIGHT = 0.2,
    SPECIAL_AMMO_WEIGHT = 0.2,
    MISC_WEIGHT = 0.1;
    
    public StMunitionsShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        randomTypePicker = new WeightedRandomBag<>();
        this.itemsByType = itemsByType;
        randomTypePicker.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        randomTypePicker.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        randomTypePicker.addEntry(StItemType.SPECIAL_AMMO, SPECIAL_AMMO_WEIGHT);
        randomTypePicker.addEntry(StItemType.MISC, MISC_WEIGHT);
    }

    @Override
    public StItem getItem(StRarity rarity) {
        StItemType type = randomTypePicker.getRandom();
        int randomIndex = (int)(Math.random() * itemsByType.get(type).size());
        return itemsByType.get(type).get(randomIndex);
    }
}
