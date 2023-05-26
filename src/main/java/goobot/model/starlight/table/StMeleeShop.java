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

public class StMeleeShop extends StItemTable {
    private static final double 
    MELEE_WEIGHT = 0.7,
    EXPLOSIVE_WEIGHT = 0.1,
    ARMOR_WEIGHT = 0.1,
    WEAPON_MOD_WEIGHT = 0.1;
    
    public StMeleeShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        super(itemsByType, Set.<StItemType>of(
            StItemType.MELEE_WEAPON, StItemType.EXPLOSIVE, StItemType.ARMOR, StItemType.WEAPON_MOD));
        randomItemType.addEntry(StItemType.MELEE_WEAPON, MELEE_WEIGHT);
        randomItemType.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        randomItemType.addEntry(StItemType.ARMOR, ARMOR_WEIGHT);
        randomItemType.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
    }
}