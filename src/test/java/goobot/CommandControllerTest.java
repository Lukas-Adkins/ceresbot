/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot;

import org.junit.Test;

import goobot.controller.CommandController;

import static org.junit.Assert.*;
import org.junit.Before;

public class CommandControllerTest {
    private CommandController controller;
 
    @Before
    public void setUp() throws Exception {
        this.controller = new CommandController(Constants.SPELLS_TEST_FILENAME, Constants.CHARACTER_TEST_FILEPATHS);
    }

    @Test
    public void testHelp(){
        assertEquals(Constants.HELP_MSG, this.controller.Help(null));
    }

    @Test
    public void testPing(){
        assertEquals(Constants.PONG_MSG, this.controller.Ping(null));
    }

    @Test
    public void testPat(){
        assertEquals(Constants.PAT_MSG, this.controller.Pat(null));
    }

    @Test
    public void testSpell(){
        String spellString = controller.Spell("Spell That Doesn't Exist");
        assertEquals(Constants.SPELL_NOT_FOUND_MSG, spellString);
    }
}
