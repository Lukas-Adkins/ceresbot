/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.table;

import goobot.Constants.StItemType;
import goobot.model.starlight.StItem;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class StRangedShop extends StItemTable {
    private static final double 
    RANGED_WEIGHT = 0.7,
    EXPLOSIVE_WEIGHT = 0.1,
    SPECIAL_AMMO_WEIGHT = 0.1,
    WEAPON_MOD_WEIGHT = 0.1;
    
    public StRangedShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        super(itemsByType, Set.<StItemType>of(
            StItemType.RANGED_WEAPON, StItemType.EXPLOSIVE, StItemType.SPECIAL_AMMO, StItemType.WEAPON_MOD));
        randomItemType.addEntry(StItemType.RANGED_WEAPON, RANGED_WEIGHT);
        randomItemType.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        randomItemType.addEntry(StItemType.SPECIAL_AMMO, SPECIAL_AMMO_WEIGHT);
        randomItemType.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
    }
}
