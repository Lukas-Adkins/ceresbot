/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.table;

import goobot.Constants.StItemType;

import java.util.Arrays;
import java.util.Set;

public class StArmorShop extends StItemTable {
    private static final double 
    ARMOR_WEIGHT = 0.7,
    RANGED_WEIGHT = 0.1,
    MELEE_WEIGHT = 0.1,
    WEAPON_MOD_WEIGHT = 0.1;
    
    public StArmorShop(){
        super(Set.<StItemType>of(StItemType.ARMOR, StItemType.RANGED_WEAPON, StItemType.MELEE_WEAPON, StItemType.WEAPON_MOD));
        weightedItemTypes.addEntries(
            Arrays.asList(StItemType.ARMOR, StItemType.RANGED_WEAPON, StItemType.MELEE_WEAPON, StItemType.WEAPON_MOD),
            Arrays.asList(ARMOR_WEIGHT, RANGED_WEIGHT, MELEE_WEIGHT, WEAPON_MOD_WEIGHT)
        );
    }
}
