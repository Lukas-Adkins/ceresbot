/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight;

import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.ArrayList;

public class StRangedShop extends StShop {
    private static final double 
    RANGED_WEIGHT = 0.7,
    EXPLOSIVE_WEIGHT = 0.1,
    SPECIAL_AMMO_WEIGHT = 0.1,
    WEAPON_MOD_WEIGHT = 0.1;
    
    public StRangedShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        super(itemsByType);
        randomTypePicker.addEntry(StItemType.RANGED_WEAPON, RANGED_WEIGHT);
        randomTypePicker.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        randomTypePicker.addEntry(StItemType.SPECIAL_AMMO, SPECIAL_AMMO_WEIGHT);
        randomTypePicker.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        shopTypes.add(StItemType.RANGED_WEAPON);
        shopTypes.add(StItemType.EXPLOSIVE);
        shopTypes.add(StItemType.SPECIAL_AMMO);
        shopTypes.add(StItemType.WEAPON_MOD);
    }
}
