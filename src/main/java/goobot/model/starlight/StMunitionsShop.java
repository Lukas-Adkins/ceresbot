package goobot.model.starlight;

import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.ArrayList;

public class StMunitionsShop extends StShop {
    private static final double 
    EXPLOSIVE_WEIGHT = 0.5,
    WEAPON_MOD_WEIGHT = 0.2,
    SPECIAL_AMMO_WEIGHT = 0.2,
    MISC_WEIGHT = 0.1;
    
    public StMunitionsShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        super(itemsByType);
        randomTypePicker.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        randomTypePicker.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        randomTypePicker.addEntry(StItemType.SPECIAL_AMMO, SPECIAL_AMMO_WEIGHT);
        randomTypePicker.addEntry(StItemType.MISC, MISC_WEIGHT);
        shopTypes.add(StItemType.EXPLOSIVE);
        shopTypes.add(StItemType.WEAPON_MOD);
        shopTypes.add(StItemType.SPECIAL_AMMO);
        shopTypes.add(StItemType.MISC);
    }
}
