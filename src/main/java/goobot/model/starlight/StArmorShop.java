package goobot.model.starlight;

import goobot.Constants.StRarity;
import goobot.model.WeightedRandomBag;
import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class StArmorShop extends StShop {
    private static final double 
    ARMOR_WEIGHT = 0.7,
    RANGED_WEIGHT = 0.1,
    MELEE_WEIGHT = 0.1,
    WEAPON_MOD_WEIGHT = 0.1;
    
    public StArmorShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        super(itemsByType);
        randomTypePicker.addEntry(StItemType.ARMOR, ARMOR_WEIGHT);
        randomTypePicker.addEntry(StItemType.RANGED_WEAPON, RANGED_WEIGHT);
        randomTypePicker.addEntry(StItemType.MELEE_WEAPON, MELEE_WEIGHT);
        randomTypePicker.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        shopTypes.add(StItemType.ARMOR);
        shopTypes.add(StItemType.RANGED_WEAPON);
        shopTypes.add(StItemType.MELEE_WEAPON);
        shopTypes.add(StItemType.WEAPON_MOD);
    }
}
