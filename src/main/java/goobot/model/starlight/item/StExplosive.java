/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.StRarity;
import goobot.Constants.StItemType;

public class StExplosive extends StRangedWeapon {

   public StExplosive(StItemType type, String name, StRarity rarity, String description, String weight, Integer price, String weaponTypes, 
   String range, String RoF, String dmg, String pen, String mag, String reloadTime){
    super(type, name, rarity, description, weight, price, weaponTypes, range, RoF, dmg, pen, mag, reloadTime);
   }

   @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s / %d credits\n%s / Range: %s / Damage: %s / Pen: %s \n%s```", 
        getName(),getFormattedRarity(), getWeight(), getPrice(), getWeaponTypes(), getRange(), getDmg(), getPen(), getDescription());
    }
}
