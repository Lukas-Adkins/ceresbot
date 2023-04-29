package goobot.model.starlight;

import goobot.Constants.StRarity;
import goobot.model.WeightedRandomBag;
import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.ArrayList;

public class StMechShop implements StShop {
    private WeightedRandomBag<StItemType> randomTypePicker;
    private HashMap<StItemType, ArrayList<StItem>> itemsByType;

    private static final double 
    ENGINE_WEIGHT = 0.2,
    UTILITY_WEIGHT = 0.4,
    MELEE_WEIGHT = 0.2,
    RANGED_WEIGHT = 0.2;
    
    public StMechShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        randomTypePicker = new WeightedRandomBag<>();
        this.itemsByType = itemsByType;
        randomTypePicker.addEntry(StItemType.MECH_ENGINE, ENGINE_WEIGHT);
        randomTypePicker.addEntry(StItemType.MECH_UTILITY, UTILITY_WEIGHT);
        randomTypePicker.addEntry(StItemType.MECH_MELEE_WEAPON, MELEE_WEIGHT);
        randomTypePicker.addEntry(StItemType.MECH_RANGED_WEAPON, RANGED_WEIGHT);
    }

    @Override
    public StItem getItem(StRarity rarity) {
        StItemType type = randomTypePicker.getRandom();
        int randomIndex = (int)(Math.random() * itemsByType.get(type).size());
        return itemsByType.get(type).get(randomIndex);
    }
}
