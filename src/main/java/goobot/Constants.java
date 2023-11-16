/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.checkerframework.checker.units.qual.min;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final Integer 
    FATAL_FAILURE = 1,
    DISCORD_MSG_CAP = 2000;
    
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
    MECH_NOT_FOUND_MSG = "Mech not found. Please make sure you have spelled the mech's name correctly.",
    PAT_MSG = "<:ceresblush:875653225385168898>",
    PONG_MSG = "Pong!",
    HELP_MSG= "**Command List:**\n" +
        "`!roll <number of dice>d<dice> <+/-> <modifier>` - Simulates a dice roll for the specified dice values.\n" +
        "`!spell <spell>` - Provides information on the given 5e D&D spell.\n" +
        "`!spellscroll <spell>` - Calculates the price of a spell scroll for the given 5e D&D spell, and what stats you need to use it.\n" +
        "`!character <character>` - Provides information on the given D&D character.\n" +
        "`!item <name>` - Provides information on the given Starlight item.\n" +
        "`!shop <ranged,melee,armor,munitions,cybernetics,mech> <commerce skill>` - Generates a store inventory of Starlight items based on a shop type and commerce skill.\n" + 
        "`!mech <name>` - Provides information on the given Starlight mech.\n" + 
        "`!loot <mech_low, mech_med, mech_high>` - Rolls on a given loot table.\n",
    SPELLS_FILEPATH = "spells.json",
    SPELLS_TEST_FILEPATH = "spells_test.json",
    ST_ITEMS_FILEPATH = "starlight_items.csv",
    DISCORD_TOKEN_STRING = "DISCORD_API_KEY",
    DISCORD_TOKEN_NOT_FOUND_ERROR = "Could not find .env file with DISCORD_API_KEY value set.",
    CHARACTER_CSV_NOT_FOUND_ERROR = "Could not find character CSV file.",
    BOT_START_ERROR = "Could not start Ceresbot.";

    public static final List<String> CHARACTER_FILEPATHS = Arrays.asList("materia_characters.csv", "inferno_characters.csv", "hysteria_characters.csv");
    public static final List<String> CHARACTER_TEST_FILEPATHS = Arrays.asList("characters_test.csv");
    
    // Price map for default PHB spell scroll prices, by level / gp cost.
    public static Map<String, Integer> SPELL_SCROLL_STATIC_PRICES = Stream.of(new Object[][] { 
        { "cantrip", 13 }, 
        { "1", 25 }, 
        { "2", 50 }, 
        { "3", 125 }, 
        { "4", 250 }, 
        { "5", 1250 }, 
        { "6", 2500 }, 
        { "7", 5000 }, 
        { "8", 12500 }, 
        { "9", 25000 }, 
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    public enum ItemType {
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
        MECH_RANGED_WEAPON,
        MECH
    }

    public enum TableType {
        RANGED_SHOP(
            Arrays.asList(ItemType.RANGED_WEAPON, ItemType.EXPLOSIVE, ItemType.SPECIAL_AMMO, ItemType.WEAPON_MOD),
            Arrays.asList(0.7, 0.1, 0.1, 0.1)
        ),
        MELEE_SHOP(
            Arrays.asList(ItemType.MELEE_WEAPON, ItemType.EXPLOSIVE, ItemType.ARMOR, ItemType.WEAPON_MOD),
            Arrays.asList(0.7, 0.1, 0.1, 0.1)
        ),
        ARMOR_SHOP(
            Arrays.asList(ItemType.ARMOR, ItemType.RANGED_WEAPON, ItemType.MELEE_WEAPON, ItemType.WEAPON_MOD),
            Arrays.asList(0.7, 0.1, 0.1, 0.1)
        ),
        CYBERNETIC_SHOP(
            Arrays.asList(ItemType.CYBERNETIC, ItemType.WEAPON_MOD, ItemType.MISC, ItemType.CONSUMABLE),
            Arrays.asList(0.75, 0.1, 0.1, 0.05)
        ),
        MECH_SHOP(
            Arrays.asList(ItemType.MECH_ENGINE, ItemType.MECH_UTILITY, ItemType.MECH_MELEE_WEAPON, ItemType.MECH_RANGED_WEAPON),
            Arrays.asList(0.15, 0.2, 0.3, 0.35)
        ),
        MUNITION_SHOP(
            Arrays.asList(ItemType.EXPLOSIVE, ItemType.WEAPON_MOD, ItemType.SPECIAL_AMMO, ItemType.MISC),
            Arrays.asList(0.5, 0.2, 0.2, 0.1)
        ),
        MECH_LOW_TABLE(
            Arrays.asList(
                ItemType.RANGED_WEAPON, ItemType.MELEE_WEAPON, ItemType.EXPLOSIVE, ItemType.ARMOR,
                ItemType.CYBERNETIC, ItemType.MISC, ItemType.WEAPON_MOD, ItemType.SPECIAL_AMMO, ItemType.CONSUMABLE,
                ItemType.MECH_ENGINE, ItemType.MECH_UTILITY, ItemType.MECH_MELEE_WEAPON, ItemType.MECH_RANGED_WEAPON
                ),
            Arrays.asList(0.02, 0.02, 0.01, 0.02, 0.02, 0.02, 0.01, 0.01, 0.02, 0.2, 0.2, 0.2, 0.2),
            25, 1, 2
        ),
        MECH_MED_TABLE(
            Arrays.asList(
                ItemType.RANGED_WEAPON, ItemType.MELEE_WEAPON, ItemType.EXPLOSIVE, ItemType.ARMOR,
                ItemType.CYBERNETIC, ItemType.MISC, ItemType.WEAPON_MOD, ItemType.SPECIAL_AMMO, ItemType.CONSUMABLE,
                ItemType.MECH_ENGINE, ItemType.MECH_UTILITY, ItemType.MECH_MELEE_WEAPON, ItemType.MECH_RANGED_WEAPON
                ),
            Arrays.asList(0.02, 0.02, 0.01, 0.02, 0.02, 0.02, 0.01, 0.01, 0.02, 0.2, 0.2, 0.2, 0.2),
            40, 1, 3
        ),
        MECH_HIGH_TABLE(
            Arrays.asList(
                ItemType.RANGED_WEAPON, ItemType.MELEE_WEAPON, ItemType.EXPLOSIVE, ItemType.ARMOR,
                ItemType.CYBERNETIC, ItemType.MISC, ItemType.WEAPON_MOD, ItemType.SPECIAL_AMMO, ItemType.CONSUMABLE,
                ItemType.MECH_ENGINE, ItemType.MECH_UTILITY, ItemType.MECH_MELEE_WEAPON, ItemType.MECH_RANGED_WEAPON
                ),
            Arrays.asList(0.02, 0.02, 0.01, 0.02, 0.02, 0.02, 0.01, 0.01, 0.02, 0.2, 0.2, 0.2, 0.2),
            55, 1, 4
        );


        public final List<ItemType> itemTypes;
        public final List<Double> itemWeights;
        public Integer commerce, minItems, maxItems;

        private TableType(List<ItemType> itemTypes, List<Double> itemWeights){
            this.itemTypes = itemTypes;
            this.itemWeights = itemWeights;
        }

        private TableType(List<ItemType> itemTypes, List<Double> itemWeights, Integer commerce, Integer minItems, Integer maxItems){
            this.itemTypes = itemTypes;
            this.itemWeights = itemWeights;
            this.commerce = commerce;
            this.minItems = minItems;
            this.maxItems = maxItems;
        }
    }

    public enum Rarity {
        UBIQUITOUS {
            @Override
            public String toString(){
                return "Ubiquitous";
            }
            @Override
            public Rarity prev(){
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

        public Rarity prev(){
            return values()[ordinal() - 1];
        }
    }

    public static final Map<Rarity, String> RARITY_COLORS = Map.of(
        Rarity.ABUNDANT, "\u001b[0;30m%s\u001b[0;0m", // Gray
        Rarity.PLENTIFUL, "\u001b[0;30m%s\u001b[0;0m", // Gray
        Rarity.COMMON, "\u001b[0;33m%s\u001b[0;0m", // Yellow
        Rarity.AVERAGE, "\u001b[0;32m%s\u001b[0;0m", // Green
        Rarity.SCARCE, "\u001b[0;34m%s\u001b[0;0m", // Blue
        Rarity.RARE, "\u001b[0;36m%s\u001b[0;0m", // Cyan
        Rarity.VERY_RARE, "\u001b[0;35m%s\u001b[0;0m", // Pink
        Rarity.EXTREMELY_RARE, "\u001b[0;31m%s\u001b[0;0m", // Red
        Rarity.NEAR_UNIQUE, "\u001b[0;37m%s\u001b[0;0m" // White
    );
}
