/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight;

import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.ArrayList;

public class StMechShop extends StShop {
    private static final double 
    ENGINE_WEIGHT = 0.15,
    UTILITY_WEIGHT = 0.2,
    MELEE_WEIGHT = 0.3,
    RANGED_WEIGHT = 0.35;
    
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
