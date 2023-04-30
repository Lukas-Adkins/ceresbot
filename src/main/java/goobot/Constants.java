/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot;
import java.util.Map;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final Integer 
    FATAL_FAILURE = 1;
    
    public static final Boolean 
    LOG_MESSAGES = false,
    CUSTOM_SCROLL_PRICES = true,
    CUSTOM_SCROLL_CASTING = true;

    public static final String 
    BOT_PREFIX = "!",
    DICE_NOT_PARSED_MSG = "Could not parse provided dice. Please try in format:\n`!roll <number of dice>d<dice> <+/-> <modifier>`",
    SPELL_NOT_FOUND_MSG = "Spell not found. Keep in mind, only PHB, XGtE, and TCoE spells are supported currently.",
    ITEM_NOT_FOUND_MESSAGE = "Item not found.",
    CHARACTER_NOT_FOUND_MSG = "Character not found. Please make sure you have spelled the character's name correctly.",
    PAT_MSG = "<:ceresblush:875653225385168898>",
    PONG_MSG = "Pong!",
    HELP_MSG= "**Command List:**\n" +
        "`!roll <number of dice>d<dice> <+/-> <modifier>` - Simulates a dice roll for the specified dice values.\n" +
        "`!spell <spell>` - Provides information on the given 5e D&D spell.\n" +
        "`!spellscroll <spell>` - Calculates the price of a spell scroll for the given 5e D&D spell, and what stats you need to use it.\n" +
        "`!character <character>` - Provides information on the given D&D character.\n" +
        "`!item <item name>` - Provides information on the given Starlight item.\n" +
        "`!shop <ranged,melee,armor,munitions,cybernetics,mech> <commerce skill>` - Generates a store inventory of Starlight items based on a shop type and commerce skill.\n",
    SPELLS_FILEPATH = "spells.json",
    SPELLS_TEST_FILEPATH = "spells_test.json",
    ST_ITEMS_FILEPATH = "starlight_items.csv",
    DISCORD_TOKEN_STRING = "DISCORD_API_KEY",
    DISCORD_TOKEN_NOT_FOUND_ERROR = "Could not find .env file with DISCORD_API_KEY value set.",
    CHARACTER_CSV_NOT_FOUND_ERROR = "Could not find character CSV file.",
    BOT_START_ERROR = "Could not start Ceresbot.";

    public static final List<String> CHARACTER_FILEPATHS = Arrays.asList("materia_characters.csv", "inferno_characters.csv");
    public static final List<String> CHARACTER_TEST_FILEPATHS = Arrays.asList("characters_test.csv");
    
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

    public enum StItemType {
        RANGED_WEAPON,
        MELEE_WEAPON,
        EXPLOSIVE,
        ARMOR,
        CYBERNETIC,
        MISC,
        WEAPON_MOD,
        SPECIAL_AMMO,
        CONSUMABLE,
        MECH_ENGINE,
        MECH_UTILITY,
        MECH_MELEE_WEAPON,
        MECH_RANGED_WEAPON
    }

    public enum StShopType {
        
    }

    public enum StRarity {
        UBIQUITOUS {
            @Override
            public String toString(){
                return "Ubiquitous";
            }
            @Override
            public StRarity prev(){
                return null;
            }
        },
        ABUNDANT {
            @Override
            public String toString(){
                return "Abundant";
            }
        },
        PLENTIFUL {
            @Override
            public String toString(){
                return "Plentiful";
            }
        },
        COMMON {
            @Override
            public String toString(){
                return "Common";
            }
        },
        AVERAGE {
            @Override
            public String toString(){
                return "Average";
            }
        },
        UNCOMMON {
            @Override
            public String toString(){
                return "Uncommon";
            }
        },
        SCARCE {
            @Override
            public String toString(){
                return "Scarce";
            }
        },
        RARE {
            @Override
            public String toString(){
                return "Rare";
            }
        },
        VERY_RARE {
            @Override
            public String toString(){
                return "Very Rare";
            }
        },
        EXTREMELY_RARE {
            @Override
            public String toString(){
                return "Extremely Rare";
            }
        },
        NEAR_UNIQUE {
            @Override
            public String toString(){
                return "Near Unique";
            }
        },
        UNIQUE {
            @Override
            public String toString(){
                return "Unique";
            }
        };

        public StRarity prev(){
            return values()[ordinal() - 1];
        }
    }

    public static final Map<StRarity, String> RARITY_COLORS = Map.of(
        StRarity.ABUNDANT, "\u001b[0;30m%s\u001b[0;0m", // Gray
        StRarity.PLENTIFUL, "\u001b[0;30m%s\u001b[0;0m", // Gray
        StRarity.COMMON, "\u001b[0;33m%s\u001b[0;0m", // Yellow
        StRarity.AVERAGE, "\u001b[0;33m%s\u001b[0;0m", // Yellow
        StRarity.UNCOMMON, "\u001b[0;32m%s\u001b[0;0m", // Green
        StRarity.SCARCE, "\u001b[0;34m%s\u001b[0;0m", // Blue
        StRarity.RARE, "\u001b[0;36m%s\u001b[0;0m", // Cyan
        StRarity.VERY_RARE, "\u001b[0;35m%s\u001b[0;0m", // Pink
        StRarity.EXTREMELY_RARE, "\u001b[0;31m%s\u001b[0;0m", // Red
        StRarity.NEAR_UNIQUE, "\u001b[0;37m%s\u001b[0;0m" // White
    );
}
