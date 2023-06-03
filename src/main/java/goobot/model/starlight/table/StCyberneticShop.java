/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.table;

import goobot.Constants.StItemType;

import java.util.Set;

public class StCyberneticShop extends StItemTable {
    private static final double 
    CYBERNETIC_WEIGHT = 0.75,
    WEAPON_MOD_WEIGHT = 0.1,
    MISC_WEIGHT = 0.1,
    CONSUMABLE_WEIGHT = 0.05;
    
    public StCyberneticShop(){
        super(Set.<StItemType>of(
            StItemType.CYBERNETIC, StItemType.WEAPON_MOD, StItemType.MISC, StItemType.CONSUMABLE));
        weightedItemTypes.addEntry(StItemType.CYBERNETIC, CYBERNETIC_WEIGHT);
        weightedItemTypes.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        weightedItemTypes.addEntry(StItemType.MISC, MISC_WEIGHT);
        weightedItemTypes.addEntry(StItemType.CONSUMABLE, CONSUMABLE_WEIGHT);
    }
}
