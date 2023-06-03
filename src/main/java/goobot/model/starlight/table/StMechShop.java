/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.table;

import goobot.Constants.StItemType;

import java.util.Set;

public class StMechShop extends StItemTable {
    private static final double 
    ENGINE_WEIGHT = 0.15,
    UTILITY_WEIGHT = 0.2,
    MELEE_WEIGHT = 0.3,
    RANGED_WEIGHT = 0.35;
    
    public StMechShop(){
        super(Set.<StItemType>of(
            StItemType.MECH_ENGINE, StItemType.MECH_UTILITY, StItemType.MECH_MELEE_WEAPON, StItemType.MECH_RANGED_WEAPON));
        weightedItemTypes.addEntry(StItemType.MECH_ENGINE, ENGINE_WEIGHT);
        weightedItemTypes.addEntry(StItemType.MECH_UTILITY, UTILITY_WEIGHT);
        weightedItemTypes.addEntry(StItemType.MECH_MELEE_WEAPON, MELEE_WEIGHT);
        weightedItemTypes.addEntry(StItemType.MECH_RANGED_WEAPON, RANGED_WEIGHT);
    }
}
