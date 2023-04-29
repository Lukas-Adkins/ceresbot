package goobot.model.starlight;

import goobot.Constants.StRarity;
import goobot.model.WeightedRandomBag;
import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.ArrayList;

public class StCyberneticsShop implements StShop {
    private WeightedRandomBag<StItemType> randomTypePicker;
    private HashMap<StItemType, ArrayList<StItem>> itemsByType;

    private static final double 
    CYBERNETIC_WEIGHT = 0.8,
    WEAPON_MOD_WEIGHT = 0.1,
    MISC_WEIGHT = 0.1;
    
    public StCyberneticsShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        randomTypePicker = new WeightedRandomBag<>();
        this.itemsByType = itemsByType;
        randomTypePicker.addEntry(StItemType.CYBERNETIC, CYBERNETIC_WEIGHT);
        randomTypePicker.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        randomTypePicker.addEntry(StItemType.MISC, MISC_WEIGHT);
    }

    @Override
    public StItem getItem(StRarity rarity) {
        StItemType type = randomTypePicker.getRandom();
        int randomIndex = (int)(Math.random() * itemsByType.get(type).size());
        return itemsByType.get(type).get(randomIndex);
    }
}
