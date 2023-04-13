/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.model;

import java.util.List;
import java.util.Objects;
import goobot.Constants.DhRarity;
import goobot.Constants.DhWeaponType;

public class DhWeapon extends DhItem {

    private List<String> weaponTypes;
    private String range;
    private String RoF;
    private String Dmg;
    private Integer pen;
    private Integer mag;
    private String reloadTime;
    private List<String> special;
}
