package goobot.model.starlight;

import goobot.Constants.StRarity;
import goobot.model.WeightedRandomBag;
import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class StMechShop extends StShop {
    private static final double 
    ENGINE_WEIGHT = 0.2,
    UTILITY_WEIGHT = 0.4,
    MELEE_WEIGHT = 0.2,
    RANGED_WEIGHT = 0.2;
    
    public StMechShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        super(itemsByType);
        randomTypePicker.addEntry(StItemType.MECH_ENGINE, ENGINE_WEIGHT);
        randomTypePicker.addEntry(StItemType.MECH_UTILITY, UTILITY_WEIGHT);
        randomTypePicker.addEntry(StItemType.MECH_MELEE_WEAPON, MELEE_WEIGHT);
        randomTypePicker.addEntry(StItemType.MECH_RANGED_WEAPON, RANGED_WEIGHT);
        shopTypes.add(StItemType.MECH_ENGINE);
        shopTypes.add(StItemType.MECH_UTILITY);
        shopTypes.add(StItemType.MECH_MELEE_WEAPON);
        shopTypes.add(StItemType.MECH_RANGED_WEAPON);
    }
}
