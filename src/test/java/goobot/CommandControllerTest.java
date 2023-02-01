/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot;

import org.junit.Test;

import goobot.controllers.CommandController;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;

public class CommandControllerTest {
    private CommandController controller;
 
    @Before
    public void setUp() throws Exception {
        this.controller = new CommandController();
    }

    @After
    public void tearDown() throws Exception {
        // TODO not yet implemented
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
    public void testRoll(){
        // TODO not yet implemented
    }

    @Test
    public void testSpell(){
        // TODO not yet implemented
    }

    @Test
    public void testSpellScroll(){
        // TODO not yet implemented
    }
}
