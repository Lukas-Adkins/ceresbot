/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

 package goobot;

 import org.junit.Test;
 
 import goobot.controller.CommandController;
import goobot.controller.SpellController;
import goobot.model.Spell;
 
 import static org.junit.Assert.*;
 import org.junit.Before;
public class SpellControllerTest {
    private SpellController controller;
 
    @Before
    public void setUp() throws Exception {
        this.controller = new SpellController(Constants.SPELLS_TEST_FILENAME);
    }

    @Test
    public void testSpell(){
        String spellDesc = "Test spell 1. Used for testing.";
        Spell spell = controller.getSpell("test spell 1");
        assertEquals(spellDesc, spell.getDescription());
    }

    @Test
    public void testSpellNotExist(){
        Spell spell = controller.getSpell("spell that doesn't exist");
        assertEquals(null, spell);
    }
}
