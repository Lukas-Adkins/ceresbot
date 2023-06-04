/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model;

import java.util.List;

import goobot.model.starlight.item.Mech;

public interface CommandInterface {
    public String Help(String args) throws Exception;
    public String Ping(String args) throws Exception;
    public String Pat(String args) throws Exception;
    public List<String> CharacterInfo(String args) throws Exception;
    public Mech MechInfo(String args) throws Exception;
    public String Roll(String args) throws Exception;
    public String Spell(String args) throws Exception;
    public String SpellScroll(String args) throws Exception;
    public String Item(String args) throws Exception;
    public String Shop(String args) throws Exception;
    public String Loot(String args) throws Exception;
}
