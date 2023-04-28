/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

 package goobot.controller.starlight;

 import java.io.FileNotFoundException;
import java.lang.invoke.ConstantBootstraps;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import com.opencsv.CSVReader;
import java.io.Reader;
import java.nio.file.Path;

import goobot.Constants;
import goobot.Constants.StItemType;
import goobot.model.starlight.StArmor;
import goobot.model.starlight.StCybernetic;
import goobot.model.starlight.StItem;
import goobot.model.starlight.StMechMeleeWeapon;
import goobot.model.starlight.StMechRangedWeapon;
import goobot.model.starlight.StMechSystem;
import goobot.model.starlight.StMeleeWeapon;
import goobot.model.starlight.StRangedWeapon;
import goobot.Constants.StRarity;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ItemController {
    private HashMap<String, StItem> itemTable;
    private LootTableController lootTable;

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

    public ItemController(){
        itemTable = new HashMap<>();
        parseItems();
    }

    private void parseItems() {
        Path path = getSheetPath(Constants.ST_ITEMS_FILEPATH);
        try{
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
                    // Clean string, add to hashmaps for quick lookup
                    itemTable.put(item.getName().toLowerCase().replace("-", " "), item);
                    System.err.println(String.format("ADDED ITEM: %s", item.getName()));
                }
            }
        }
        catch(Exception e){
            System.out.println(String.format("[ERROR] : Parsing items from %s", Constants.ST_ITEMS_FILEPATH));
            e.printStackTrace();
        }
        this.lootTable = new LootTableController(itemTable);
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

    public HashMap<String, StItem> getItemTable(){
        return itemTable;
    }

    public LootTableController getLootTable(){
        return this.lootTable;
    }

}
