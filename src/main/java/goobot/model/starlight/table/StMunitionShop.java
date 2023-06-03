/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.table;

import goobot.Constants.StItemType;

import java.util.Set;

public class StMunitionShop extends StItemTable {
    private static final double 
    EXPLOSIVE_WEIGHT = 0.5,
    WEAPON_MOD_WEIGHT = 0.2,
    SPECIAL_AMMO_WEIGHT = 0.2,
    MISC_WEIGHT = 0.1;
    
    public StMunitionShop(){
        super(Set.<StItemType>of(
            StItemType.EXPLOSIVE, StItemType.WEAPON_MOD, StItemType.SPECIAL_AMMO, StItemType.MISC));
        weightedItemTypes.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        weightedItemTypes.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        weightedItemTypes.addEntry(StItemType.SPECIAL_AMMO, SPECIAL_AMMO_WEIGHT);
        weightedItemTypes.addEntry(StItemType.MISC, MISC_WEIGHT);
    }
}
