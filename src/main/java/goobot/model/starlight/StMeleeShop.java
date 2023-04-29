package goobot.model.starlight;

import goobot.Constants.StRarity;
import goobot.model.WeightedRandomBag;
import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.ArrayList;

public class StMeleeShop implements StShop {
    private WeightedRandomBag<StItemType> randomTypePicker;
    private HashMap<StItemType, ArrayList<StItem>> itemsByType;

    private static final double 
    MELEE_WEIGHT = 0.7,
    EXPLOSIVE_WEIGHT = 0.1,
    ARMOR_WEIGHT = 0.1,
    WEAPON_MOD_WEIGHT = 0.1;
    
    public StMeleeShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        randomTypePicker = new WeightedRandomBag<>();
        this.itemsByType = itemsByType;
        randomTypePicker.addEntry(StItemType.MELEE_WEAPON, MELEE_WEIGHT);
        randomTypePicker.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        randomTypePicker.addEntry(StItemType.ARMOR, ARMOR_WEIGHT);
        randomTypePicker.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
    }

    @Override
    public StItem getItem(StRarity rarity) {
        StItemType type = randomTypePicker.getRandom();
        int randomIndex = (int)(Math.random() * itemsByType.get(type).size());
        return itemsByType.get(type).get(randomIndex);
    }
}
