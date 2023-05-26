/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight;

import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.ArrayList;

public class StCyberneticsShop extends StShop {
    private static final double 
    CYBERNETIC_WEIGHT = 0.75,
    WEAPON_MOD_WEIGHT = 0.1,
    MISC_WEIGHT = 0.1,
    CONSUMABLE_WEIGHT = 0.05;
    
    public StCyberneticsShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        super(itemsByType);
        randomTypePicker.addEntry(StItemType.CYBERNETIC, CYBERNETIC_WEIGHT);
        randomTypePicker.addEntry(StItemType.WEAPON_MOD, WEAPON_MOD_WEIGHT);
        randomTypePicker.addEntry(StItemType.MISC, MISC_WEIGHT);
        randomTypePicker.addEntry(StItemType.CONSUMABLE, CONSUMABLE_WEIGHT);
        shopTypes.add(StItemType.CYBERNETIC);
        shopTypes.add(StItemType.WEAPON_MOD);
        shopTypes.add(StItemType.MISC);
        shopTypes.add(StItemType.CONSUMABLE);
    }
}
