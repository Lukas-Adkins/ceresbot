/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.table;

import goobot.Constants.StItemType;

import java.util.Set;

public class StCyberneticsShop extends StItemTable {
    private static final double 
    CYBERNETIC_WEIGHT = 0.75,
    WEAPON_MOD_WEIGHT = 0.1,
    MISC_WEIGHT = 0.1,
    CONSUMABLE_WEIGHT = 0.05;
    
    public StCyberneticsShop(){
        super(Set.<StItemType>of(
            StItemType.CYBERNETIC, StItemType.WEAPON_MOD, StItemType.MISC, StItemType.CONSUMABLE));
        randomItemType.addEntry(StItemType.CYBERNETIC, CYBERNETIC_WEIGHT);
        randomItemType.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        randomItemType.addEntry(StItemType.MISC, MISC_WEIGHT);
        randomItemType.addEntry(StItemType.CONSUMABLE, CONSUMABLE_WEIGHT);
    }
}
