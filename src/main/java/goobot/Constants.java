/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */


package goobot;
import java.util.Map;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final Integer FATAL_FAILURE = 1;
    
    public static final Boolean LOG_MESSAGES = true;
    public static final Boolean CUSTOM_SCROLL_PRICES = true;
    public static final Boolean CUSTOM_SCROLL_CASTING = true;

    public static final String BOT_PREFIX = "!";
    public static final String DICE_NOT_PARSED_MSG = "Could not parse provided dice. Please try in format:\n`!roll <number of dice>d<dice>`";
    public static final String SPELL_NOT_FOUND_MSG = "Spell not found. Keep in mind, only PHB, XGtE, and TCoE spells are supported currently.";
    public static final String CHARACTER_NOT_FOUND_MSG = "Character not found. Please make sure you have spelled the character's name correctly.";
    public static final String PAT_MSG = "<:ceresblush:875653225385168898>";
    public static final String PONG_MSG = "Pong!";
    public static final String HELP_MSG= "**Command List:**\n" +
        "`!roll <number of dice>d<dice>` - Simulates a dice roll for the specified dice values.\n" +
        "`!spell <spell>` - Provides information on the given 5e D&D spell.\n" +
        "`!spellscroll <spell>` - Calculates the price of a spell scroll for the given 5e D&D spell, and what stats you need to use it.\n" +
        "`!character <character>` - Provides information on the given D&D character.";
    public static final String SPELLS_FILEPATH = "spells.json";
    public static final String SPELLS_TEST_FILENAME = "spells_test.json";
    public static final List<String> CHARACTER_FILEPATHS = Arrays.asList("materia_characters.csv", "inferno_characters.csv");
    public static final List<String> CHARACTER_TEST_FILEPATHS = Arrays.asList("characters_test.csv");
    public static final String DISCORD_TOKEN_STRING = "DISCORD_API_KEY";
    public static final String DISCORD_TOKEN_NOT_FOUND_ERROR = "Could not find .env file with DISCORD_API_KEY value set.";
    public static final String CHARACTER_CSV_NOT_FOUND_ERROR = "Could not find character CSV file.";
    public static final String BOT_START_ERROR = "Could not start Ceresbot.";
    // Price map for default PHB spell scroll prices, by level / gp cost.
    public static final Map<String, Integer> SPELL_SCROLL_STATIC_PRICES = Map.of(
    "cantrip", 13,
    "1", 25,
    "2", 50,
    "3", 125,
    "4", 250,
    "5", 1250,
    "6", 2500,
    "7", 5000,
    "8", 12500,
    "9", 25000
    );
}
