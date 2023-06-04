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
import goobot.Constants.ItemType;
import goobot.model.starlight.ItemTable;
import goobot.model.starlight.item.Armor;
import goobot.model.starlight.item.Cybernetic;
import goobot.model.starlight.item.Explosive;
import goobot.model.starlight.item.Item;
import goobot.model.starlight.item.Mech;
import goobot.model.starlight.item.MechMeleeWeapon;
import goobot.model.starlight.item.MechRangedWeapon;
import goobot.model.starlight.item.MechSystem;
import goobot.model.starlight.item.MeleeWeapon;
import goobot.model.starlight.item.RangedWeapon;
import goobot.Constants.Rarity;
import goobot.Constants.TableType;

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
    
    private static HashMap<String, Item> itemsByName;
    private static HashMap<Rarity, ArrayList<Item>> itemsByRarity;
    private static HashMap<ItemType, ArrayList<Item>> itemsByType;

    private ItemTable rangedShop;
    private ItemTable meleeShop;
    private ItemTable armorShop;
    private ItemTable munitionShop;
    private ItemTable cyberneticShop;
    private ItemTable mechShop;

    public ItemService(){
        itemsByName = new HashMap<>();
        itemsByRarity = new HashMap<>();
        itemsByType = new HashMap<>();
        try{
            parseItems(getSheetPath(Constants.ST_ITEMS_FILEPATH));
            this.rangedShop = new ItemTable(TableType.RANGED_SHOP);
            this.meleeShop = new ItemTable(TableType.MELEE_SHOP);
            this.armorShop = new ItemTable(TableType.ARMOR_SHOP);
            this.munitionShop = new ItemTable(TableType.MUNITION_SHOP);
            this.cyberneticShop = new ItemTable(TableType.CYBERNETIC_SHOP);
            this.mechShop = new ItemTable(TableType.MECH_SHOP);
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
            Item item = null;
            // Parse Strings into enum item rarities
            Rarity itemRarity = Rarity.valueOf(data[RARITY_COL].toUpperCase().trim().replace(" ", "_"));

            switch(data[TYPE_COL].toLowerCase().trim()){
                case "ranged weapon":
                    item = new RangedWeapon(
                        ItemType.RANGED_WEAPON, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]), data[CLASS_COL],
                        data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL]
                    );
                    break;
                case "melee weapon":
                    item = new MeleeWeapon(
                        ItemType.MELEE_WEAPON, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[TYPE_COL], data[RANGE_COL], data[DMG_COL], data[PEN_COL]
                        );
                    break;
                case "explosive":
                    item = new Explosive(
                        ItemType.EXPLOSIVE, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[TYPE_COL], data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL]
                        );
                    break;
                case "armor":
                    item = new Armor(
                        ItemType.ARMOR, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[COVERS_COL], data[AP_COL], data[MAX_AG_COL]
                        );
                    break;
                case "cybernetic":
                    item = new Cybernetic(
                        ItemType.CYBERNETIC, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[CYBER_SLOTS_COL]
                        );
                    break;
                case "misc":
                    item = new Item(ItemType.MISC, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                    break;
                case "weapon mod":
                    item = new Item(ItemType.WEAPON_MOD, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                    break;
                case "special ammo":
                    item = new Item(ItemType.SPECIAL_AMMO, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                    break;
                case "consumable":
                    item = new Item(ItemType.CONSUMABLE, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                    break;
                case "mech engine":
                    item = new MechSystem(ItemType.MECH_ENGINE, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                    data[MECH_SLOT_COL], data[MECH_LOCATION_COL]);
                    break;
                case "mech utility":
                    item = new MechSystem(ItemType.MECH_UTILITY, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                    data[MECH_SLOT_COL], data[MECH_LOCATION_COL]);
                    break;
                case "mech melee weapon":
                    item = new MechMeleeWeapon(
                        ItemType.MECH_MELEE_WEAPON, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                        data[TYPE_COL], data[RANGE_COL], data[DMG_COL], data[PEN_COL], data[MECH_SLOT_COL], data[MECH_LOCATION_COL]
                    );
                    break;
                case "mech ranged weapon":
                    item = new MechRangedWeapon(
                        ItemType.MECH_RANGED_WEAPON, data[NAME_COL], itemRarity, data[DESC_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]), data[CLASS_COL],
                        data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL], data[MECH_SLOT_COL], data[MECH_LOCATION_COL]
                    );
                    break;
                case "mech":
                    item = new Mech(
                        ItemType.MECH, data[NAME_COL], itemRarity, data[NAME_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]), data[URL_COL] 
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
    private void addItemToTables(Item item){
        // Add to itemsByName
        String santizedItemName = item.getName().toLowerCase().replace("-", " ");
        itemsByName.put(santizedItemName, item);
        // Add to itemsByRarity
        if(itemsByRarity.get(item.getRarity()) == null)
            itemsByRarity.put(item.getRarity(), new ArrayList<Item>());
        if(item.getType() != ItemType.MECH)
            itemsByRarity.get(item.getRarity()).add(item);
        // Add to itemsByType
        if(itemsByType.get(item.getType()) == null)
            itemsByType.put(item.getType(), new ArrayList<Item>());
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
    public Item getItem(String name){
        return ItemService.itemsByName.get(name);
    }

    /**
     * Returns all items of a specified rarity.
     * @param type
     * @return
     */
    public static ArrayList<Item> getItemByType(ItemType type){
        return itemsByType.get(type);
    }

    /**
     * Returns a specified number of items from a specific loot table.
     * @param table StItemTable to roll upon.
     * @param ubiquitous
     * @param abundant
     * @param plentiful
     * @param common
     * @param average
     * @param scarce
     * @param rare
     * @param veryRare
     * @param extremelyRare
     * @param nearUnique
     * @return
     */
    public ArrayList<Item> getItemsByTable(
        TableType table, int ubiquitous, int abundant, int plentiful, int common,
        int average, int scarce, int rare, int veryRare, int extremelyRare, int nearUnique
        ){
        switch(table){
            case ARMOR_SHOP:
                return armorShop.getItems(ubiquitous, abundant, plentiful, common, average, scarce, rare, veryRare, extremelyRare, nearUnique);
            case CYBERNETIC_SHOP:
                return cyberneticShop.getItems(ubiquitous, abundant, plentiful, common, average, scarce, rare, veryRare, extremelyRare, nearUnique);
            case MECH_SHOP:
                return mechShop.getItems(ubiquitous, abundant, plentiful, common, average, scarce, rare, veryRare, extremelyRare, nearUnique);
            case MELEE_SHOP:
                return meleeShop.getItems(ubiquitous, abundant, plentiful, common, average, scarce, rare, veryRare, extremelyRare, nearUnique);
            case MUNITION_SHOP:
                return munitionShop.getItems(ubiquitous, abundant, plentiful, common, average, scarce, rare, veryRare, extremelyRare, nearUnique);
            case RANGED_SHOP:
                return rangedShop.getItems(ubiquitous, abundant, plentiful, common, average, scarce, rare, veryRare, extremelyRare, nearUnique);
        }
        return null;
    }
}
