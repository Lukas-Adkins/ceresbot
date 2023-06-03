/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.table;

import goobot.Constants.StItemType;

import java.util.Set;

public class StMeleeShop extends StItemTable {
    private static final double 
    MELEE_WEIGHT = 0.7,
    EXPLOSIVE_WEIGHT = 0.1,
    ARMOR_WEIGHT = 0.1,
    WEAPON_MOD_WEIGHT = 0.1;
    
    public StMeleeShop(){
        super(Set.<StItemType>of(
            StItemType.MELEE_WEAPON, StItemType.EXPLOSIVE, StItemType.ARMOR, StItemType.WEAPON_MOD));
        weightedItemTypes.addEntry(StItemType.MELEE_WEAPON, MELEE_WEIGHT);
        weightedItemTypes.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        weightedItemTypes.addEntry(StItemType.ARMOR, ARMOR_WEIGHT);
        weightedItemTypes.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
    }
}