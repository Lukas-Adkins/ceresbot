/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.service;

import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.io.Reader;
import java.util.ArrayList;

import goobot.Constants;
import goobot.Constants.StItemType;
import goobot.model.starlight.item.StArmor;
import goobot.model.starlight.item.StCybernetic;
import goobot.model.starlight.item.StExplosive;
import goobot.model.starlight.item.StItem;
import goobot.model.starlight.item.StMech;
import goobot.model.starlight.item.StMechMeleeWeapon;
import goobot.model.starlight.item.StMechRangedWeapon;
import goobot.model.starlight.item.StMechSystem;
import goobot.model.starlight.item.StMeleeWeapon;
import goobot.model.starlight.item.StRangedWeapon;
import goobot.model.starlight.table.StArmorShop;
import goobot.model.starlight.table.StCyberneticShop;
import goobot.model.starlight.table.StItemTable;
import goobot.model.starlight.table.StMechShop;
import goobot.model.starlight.table.StMeleeShop;
import goobot.model.starlight.table.StMunitionShop;
import goobot.model.starlight.table.StRangedShop;
import goobot.Constants.StRarity;

import com.opencsv.CSVReader;

public class ItemService {

    private static final int 
    HEADER_ROW_INDEX = 0, 
    NAME_COL = 0, 
    RARITY_COL = 1, 
    CLASS_COL = 2, 
    RANGE_COL = 3, 
    ROF_COL = 4, 
    DMG_COL = 5, 
    PEN_COL = 6, 
    MAG_COL = 7, 
    RELOAD_COL = 8,
    COVERS_COL = 9, 
    AP_COL = 10, 
    MAX_AG_COL= 11, 
    CYBER_SLOTS_COL = 12, 
    MECH_SLOT_COL = 13, 
    MECH_LOCATION_COL = 14, 
    DESC_COL = 15, 
    WEIGHT_COL = 16,
    PRICE_COL = 17, 
    TYPE_COL = 18,
    URL_COL = 19;
    
    private static HashMap<String, StItem> itemsByName;
    private static HashMap<StRarity, ArrayList<StItem>> itemsByRarity;
    private static HashMap<StItemType, ArrayList<StItem>> itemsByType;

    private StItemTable rangedShop;
    private StItemTable meleeShop;
    private StItemTable armorShop;
    private StItemTable munitionShop;
    private StItemTable cyberneticShop;
    private StItemTable mechShop;

    public ItemService(){
        itemsByName = new HashMap<>();
        itemsByRarity = new HashMap<>();
        itemsByType = new HashMap<>();
        try{
            parseItems(getSheetPath(Constants.ST_ITEMS_FILEPATH));
            this.rangedShop = new StRangedShop();
            this.meleeShop = new StMeleeShop();
            this.armorShop = new StArmorShop();
            this.munitionShop = new StMunitionShop();
            this.cyberneticShop = new StCyberneticShop();
            this.mechShop = new StMechShop();
        }
        catch(Exception e){
            System.out.println(String.format("[ERROR] : Parsing items from %s", Constants.ST_ITEMS_FILEPATH));
            e.printStackTrace();
        }
    }

    /**
     * Parses items from CSV files.
     * @param path Filepath of item database.
     * @throws Exception
     */
    private void parseItems(Path path) throws Exception {
        List<String[]> itemList = readAllLines(path);
        itemList.remove(HEADER_ROW_INDEX);

        for(String[] data : itemList){
            StItem item = null;
            // Parse Strings into enum item rarities
            StRarity itemRarity = StRarity.valueOf(data[RARITY_COL].toUpperCase().trim().replace(" ", "_"));

            switch(data[TYPE_COL].toLowerCase().trim()){
                case "ranged weapon":
                    item = new StRangedWeapon(
                        StItemType.RANGED_WEAPON, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]), data[CLASS_COL],
                        data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL]
                    );
                    break;
                case "melee weapon":
                    item = new StMeleeWeapon(
                        StItemType.MELEE_WEAPON, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[TYPE_COL], data[RANGE_COL], data[DMG_COL], data[PEN_COL]
                        );
                    break;
                case "explosive":
                    item = new StExplosive(
                        StItemType.EXPLOSIVE, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[TYPE_COL], data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL]
                        );
                    break;
                case "armor":
                    item = new StArmor(
                        StItemType.ARMOR, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[COVERS_COL], data[AP_COL], data[MAX_AG_COL]
                        );
                    break;
                case "cybernetic":
                    item = new StCybernetic(
                        StItemType.CYBERNETIC, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[CYBER_SLOTS_COL]
                        );
                    break;
                case "misc":
                    item = new StItem(StItemType.MISC, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                    break;
                case "weapon mod":
                    item = new StItem(StItemType.WEAPON_MOD, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                    break;
                case "special ammo":
                    item = new StItem(StItemType.SPECIAL_AMMO, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                    break;
                case "consumable":
                    item = new StItem(StItemType.CONSUMABLE, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                    break;
                case "mech engine":
                    item = new StMechSystem(StItemType.MECH_ENGINE, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                    data[MECH_SLOT_COL], data[MECH_LOCATION_COL]);
                    break;
                case "mech utility":
                    item = new StMechSystem(StItemType.MECH_UTILITY, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                    data[MECH_SLOT_COL], data[MECH_LOCATION_COL]);
                    break;
                case "mech melee weapon":
                    item = new StMechMeleeWeapon(
                        StItemType.MECH_MELEE_WEAPON, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[TYPE_COL], data[RANGE_COL], data[DMG_COL], data[PEN_COL], data[MECH_SLOT_COL], data[MECH_LOCATION_COL]
                    );
                    break;
                case "mech ranged weapon":
                    item = new StMechRangedWeapon(
                        StItemType.MECH_RANGED_WEAPON, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]), data[CLASS_COL],
                        data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL], data[MECH_SLOT_COL], data[MECH_LOCATION_COL]
                    );
                    break;
                case "mech":
                    item = new StMech(
                        StItemType.MECH, data[NAME_COL], itemRarity, data[NAME_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]), data[URL_COL] 
                    );
                    break;
                default:
                    System.out.println(String.format("WARNING: Failed to parse item %s from Starlight item sheet.", data[NAME_COL]));
            }
            if(item != null){
                addItemToTables(item);
            }
        }
    }

    /**
     * Adds an item to item tables.
     * @param item
     */
    private void addItemToTables(StItem item){
        // Add to itemsByName
        String santizedItemName = item.getName().toLowerCase().replace("-", " ");
        itemsByName.put(santizedItemName, item);
        // Add to itemsByRarity
        if(itemsByRarity.get(item.getRarity()) == null)
            itemsByRarity.put(item.getRarity(), new ArrayList<StItem>());
        if(item.getType() != StItemType.MECH)
            itemsByRarity.get(item.getRarity()).add(item);
        // Add to itemsByType
        if(itemsByType.get(item.getType()) == null)
            itemsByType.put(item.getType(), new ArrayList<StItem>());
        itemsByType.get(item.getType()).add(item);
        System.out.println("Added item: " + item.getName());
    }

    /**
     * Reads all lines from a CSV file
     * @param filePath
     * @return All lines formatted as String list
     * @throws Exception
     */
    private List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    /**
     * Determines filepath of data files by checking a number of locations.
     * @param filename
     * @return valid path
     */
    private Path getSheetPath(String filename){
        String RES_PATH = "src/main/resources", CURRENT_DIR_PATH = "";
        List<String> filePaths = Arrays.asList(RES_PATH, CURRENT_DIR_PATH);

        // Search three possible locations for excel file.
        for(String filepath : filePaths){
            Path path = Paths.get(filepath, filename);
            if(Files.isReadable(path)){
                return path;
            }
        }
        return null;
    }

    /**
     * Returns an item given its name.
     * @param name
     * @return
     */
    public StItem getItem(String name){
        return ItemService.itemsByName.get(name);
    }

    /**
     * Returns all items of a specified rarity.
     * @param type
     * @return
     */
    public static ArrayList<StItem> getItemByType(StItemType type){
        return itemsByType.get(type);
    }

    public ArrayList<StItem> shopRangedItems(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return rangedShop.getItems(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }

    public ArrayList<StItem> shopMeleeItems(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return meleeShop.getItems(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }

    public ArrayList<StItem> shopArmorItems(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return armorShop.getItems(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }

    public ArrayList<StItem> shopMunitionItems(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return munitionShop.getItems(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }

    public ArrayList<StItem> shopCyberneticItems(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return cyberneticShop.getItems(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }

    public ArrayList<StItem> shopMechItems(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return mechShop.getItems(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }
}
