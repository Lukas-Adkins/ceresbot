/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model;

import java.util.List;

import goobot.model.starlight.item.StMech;

public interface CommandInterface {
    public String Help(String args) throws Exception;
    public String Ping(String args) throws Exception;
    public String Pat(String args) throws Exception;
    public List<String> CharacterInfo(String args) throws Exception;
    public StMech MechInfo(String args) throws Exception;
    public String Roll(String args) throws Exception;
    public String Spell(String args) throws Exception;
    public String SpellScroll(String args) throws Exception;
    public String StItem(String args) throws Exception;
    public String StShop(String args) throws Exception;
}
