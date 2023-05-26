/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.table;

import goobot.Constants.StItemType;

import java.util.Set;

public class StArmorShop extends StItemTable {
    private static final double 
    ARMOR_WEIGHT = 0.7,
    RANGED_WEIGHT = 0.1,
    MELEE_WEIGHT = 0.1,
    WEAPON_MOD_WEIGHT = 0.1;
    
    public StArmorShop(){
        super(Set.<StItemType>of(
            StItemType.ARMOR, StItemType.RANGED_WEAPON, StItemType.MELEE_WEAPON, StItemType.WEAPON_MOD));
        randomItemType.addEntry(StItemType.ARMOR, ARMOR_WEIGHT);
        randomItemType.addEntry(StItemType.RANGED_WEAPON, RANGED_WEIGHT);
        randomItemType.addEntry(StItemType.MELEE_WEAPON, MELEE_WEIGHT);
        randomItemType.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
    }
}
