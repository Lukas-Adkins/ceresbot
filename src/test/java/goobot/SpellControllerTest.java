/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

 package goobot;

 import org.junit.Test;
 
 import goobot.controller.CommandController;
import goobot.controller.dnd.DndSpellController;
import goobot.model.dnd.DndSpell;

import static org.junit.Assert.*;
 import org.junit.Before;
public class SpellControllerTest {
    private DndSpellController controller;
 
    @Before
    public void setUp() throws Exception {
        this.controller = new DndSpellController(Constants.SPELLS_TEST_FILEPATH);
    }

    @Test
    public void testSpell(){
        String spellDesc = "Test spell 1. Used for testing.";
        DndSpell spell = controller.getSpell("test spell 1");
        assertEquals(spellDesc, spell.getDescription());
    }

    @Test
    public void testSpellNotExist(){
        DndSpell spell = controller.getSpell("spell that doesn't exist");
        assertEquals(null, spell);
    }
}
