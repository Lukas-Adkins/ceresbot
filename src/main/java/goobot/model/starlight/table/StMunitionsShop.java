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

public class StMunitionsShop extends StItemTable {
    private static final double 
    EXPLOSIVE_WEIGHT = 0.5,
    WEAPON_MOD_WEIGHT = 0.2,
    SPECIAL_AMMO_WEIGHT = 0.2,
    MISC_WEIGHT = 0.1;
    
    public StMunitionsShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        super(itemsByType, Set.<StItemType>of(
            StItemType.EXPLOSIVE, StItemType.WEAPON_MOD, StItemType.SPECIAL_AMMO, StItemType.MISC));
        randomItemType.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        randomItemType.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        randomItemType.addEntry(StItemType.SPECIAL_AMMO, SPECIAL_AMMO_WEIGHT);
        randomItemType.addEntry(StItemType.MISC, MISC_WEIGHT);
    }
}
