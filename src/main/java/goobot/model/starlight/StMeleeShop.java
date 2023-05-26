/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight;

import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.ArrayList;

public class StMeleeShop extends StShop {
    private static final double 
    MELEE_WEIGHT = 0.7,
    EXPLOSIVE_WEIGHT = 0.1,
    ARMOR_WEIGHT = 0.1,
    WEAPON_MOD_WEIGHT = 0.1;
    
    public StMeleeShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        super(itemsByType);
        randomTypePicker.addEntry(StItemType.MELEE_WEAPON, MELEE_WEIGHT);
        randomTypePicker.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        randomTypePicker.addEntry(StItemType.ARMOR, ARMOR_WEIGHT);
        randomTypePicker.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        shopTypes.add(StItemType.MELEE_WEAPON);
        shopTypes.add(StItemType.EXPLOSIVE);
        shopTypes.add(StItemType.ARMOR);
        shopTypes.add(StItemType.WEAPON_MOD);
    }
}