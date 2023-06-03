/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.table;

import goobot.Constants.StItemType;

import java.util.Set;

public class StRangedShop extends StItemTable {
    private static final double 
    RANGED_WEIGHT = 0.7,
    EXPLOSIVE_WEIGHT = 0.1,
    SPECIAL_AMMO_WEIGHT = 0.1,
    WEAPON_MOD_WEIGHT = 0.1;
    
    public StRangedShop(){
        super(Set.<StItemType>of(
            StItemType.RANGED_WEAPON, StItemType.EXPLOSIVE, StItemType.SPECIAL_AMMO, StItemType.WEAPON_MOD));
        weightedItemTypes.addEntry(StItemType.RANGED_WEAPON, RANGED_WEIGHT);
        weightedItemTypes.addEntry(StItemType.EXPLOSIVE, EXPLOSIVE_WEIGHT);
        weightedItemTypes.addEntry(StItemType.SPECIAL_AMMO, SPECIAL_AMMO_WEIGHT);
        weightedItemTypes.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
    }
}
