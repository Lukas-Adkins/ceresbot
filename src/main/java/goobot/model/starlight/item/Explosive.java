/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants.Rarity;
import goobot.Constants.ItemType;

public class Explosive extends RangedWeapon {

   public Explosive(ItemType type, String name, Rarity rarity, String description, String weight, Integer price, String weaponTypes, 
   String range, String RoF, String dmg, String pen, String mag, String reloadTime){
    super(type, name, rarity, description, weight, price, weaponTypes, range, RoF, dmg, pen, mag, reloadTime);
   }

   @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s / %d CYD\n%s / Range: %s / Damage: %s / Pen: %s \n%s```", 
        getName(),getFormattedRarity(), getWeight(), getPrice(), getWeaponTypes(), getRange(), getDmg(), getPen(), getDescription());
    }
}
