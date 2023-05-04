/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

 package goobot.controller;

 import java.io.FileNotFoundException;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.opencsv.CSVReader;
import java.io.Reader;
import java.nio.file.Path;

import goobot.Constants;
import goobot.Constants.StItemType;
import goobot.model.starlight.StArmor;
import goobot.model.starlight.StArmorShop;
import goobot.model.starlight.StCybernetic;
import goobot.model.starlight.StCyberneticsShop;
import goobot.model.starlight.StItem;
import goobot.model.starlight.StMechMeleeWeapon;
import goobot.model.starlight.StMechRangedWeapon;
import goobot.model.starlight.StMechShop;
import goobot.model.starlight.StMechSystem;
import goobot.model.starlight.StMeleeShop;
import goobot.model.starlight.StMeleeWeapon;
import goobot.model.starlight.StMunitionsShop;
import goobot.model.starlight.StRangedWeapon;
import goobot.model.starlight.StShop;
import goobot.Constants.StRarity;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import goobot.model.WeightedRandomBag;
import goobot.model.starlight.StRangedShop;

public class StItemController {
    public static final int 
    HEADER_ROW_INDEX = 0, 
    FATAL_FAILURE = 1,
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
    TYPE_COL = 18;
    
    private HashMap<String, StItem> itemsByName;
    private HashMap<StRarity, ArrayList<StItem>> itemsByRarity;
    private HashMap<StItemType, ArrayList<StItem>> itemsByType;

    private StShop rangedShop;
    private StShop meleeShop;
    private StShop armorShop;
    private StShop munitionsShop;
    private StShop cyberneticsShop;
    private StShop mechShop;

    public StItemController(){
        itemsByName = new HashMap<>();
        itemsByRarity = new HashMap<>();
        itemsByType = new HashMap<>();
        try{
            createItems(getSheetPath(Constants.ST_ITEMS_FILEPATH));
            this.rangedShop = new StRangedShop(itemsByType);
            this.meleeShop = new StMeleeShop(itemsByType);
            this.armorShop = new StArmorShop(itemsByType);
            this.munitionsShop = new StMunitionsShop(itemsByType);
            this.cyberneticsShop = new StCyberneticsShop(itemsByType);
            this.mechShop = new StMechShop(itemsByType);
        }
        catch(Exception e){
            System.out.println(String.format("[ERROR] : Parsing items from %s", Constants.ST_ITEMS_FILEPATH));
            e.printStackTrace();
        }
    }

    private void createItems(Path path) throws Exception {
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
                    //rangedTable.get(itemRarity).add(item);
                    break;
                case "melee weapon":
                    item = new StMeleeWeapon(
                        StItemType.MELEE_WEAPON, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[TYPE_COL], data[RANGE_COL], data[DMG_COL], data[PEN_COL]
                        );
                    break;
                case "explosive":
                    item = new StRangedWeapon(
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
                        StItemType.MECH_RANGED_WEAPON, data[NAME_COL], itemRarity, data[NAME_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]), data[CLASS_COL],
                        data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL], data[MECH_SLOT_COL], data[MECH_LOCATION_COL]
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

    private void addItemToTables(StItem item){
        // Add to itemsByName
        String santizedItemName = item.getName().toLowerCase().replace("-", " ");
        itemsByName.put(santizedItemName, item);
        // Add to itemsByRarity
        if(itemsByRarity.get(item.getRarity()) == null)
            itemsByRarity.put(item.getRarity(), new ArrayList<StItem>());
        itemsByRarity.get(item.getRarity()).add(item);
        // Add to itemsByType
        if(itemsByType.get(item.getType()) == null)
            itemsByType.put(item.getType(), new ArrayList<StItem>());
        itemsByType.get(item.getType()).add(item);
        System.out.println("Added item: " + item.getName());
    }

    private List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

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

    public StItem getItem(String name){
        return this.itemsByName.get(name);
    }

    public ArrayList<StItem> getItemByType(StItemType type){
        return itemsByType.get(type);
    }

    public ArrayList<StItem> getRangedShop(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numUncommon, int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return rangedShop.getInventory(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numUncommon, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }

    public ArrayList<StItem> getMeleeShop(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numUncommon, int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return meleeShop.getInventory(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numUncommon, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }

    public ArrayList<StItem> getArmorShop(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numUncommon, int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return armorShop.getInventory(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numUncommon, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }

    public ArrayList<StItem> getMunitionsShop(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numUncommon, int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return munitionsShop.getInventory(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numUncommon, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }

    public ArrayList<StItem> getCyberneticsShop(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numUncommon, int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return cyberneticsShop.getInventory(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numUncommon, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }

    public ArrayList<StItem> getMechShop(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numUncommon, int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        return mechShop.getInventory(numUbiquitous, numAbundant, numPlentiful,
        numCommon, numAverage, numUncommon, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique);
    }
}


