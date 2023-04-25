/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

 package goobot.controller;

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
import goobot.Constants.DhItemType;
import goobot.model.DhArmor;
import goobot.model.DhCybernetic;
import goobot.model.DhItem;
import goobot.model.DhMeleeWeapon;
import goobot.model.DhRangedWeapon;
import goobot.Constants.DhRarity;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class DhItemController {
    private HashMap<String, DhItem> itemMap;
    private HashMap<String, ArrayList<DhItem>> rarityMap;

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
    LOCATION_COL = 14, 
    SPECIAL_NOTES_COL = 15, 
    WEIGHT_COL = 16,
    PRICE_COL = 17, 
    TYPE_COL = 18;

    public DhItemController(){
        this.itemMap = new HashMap<>();
        this.rarityMap = new HashMap<>();
        rarityMap.put("ubiquitous", new ArrayList<DhItem>());
        rarityMap.put("abundant", new ArrayList<DhItem>());
        rarityMap.put("plentiful", new ArrayList<DhItem>());
        rarityMap.put("common", new ArrayList<DhItem>());
        rarityMap.put("average", new ArrayList<DhItem>());
        rarityMap.put("uncommon", new ArrayList<DhItem>());
        rarityMap.put("scarce", new ArrayList<DhItem>());
        rarityMap.put("rare", new ArrayList<DhItem>());
        rarityMap.put("very rare", new ArrayList<DhItem>());
        rarityMap.put("extremely rare", new ArrayList<DhItem>());
        rarityMap.put("near unique", new ArrayList<DhItem>());
        parseItems();
    }

    private void parseItems() {
        Path path = getSheetPath(Constants.ST_ITEMS_FILEPATH);
        try{
            List<String[]> itemList = readAllLines(path);
            itemList.remove(HEADER_ROW_INDEX);
    
            for(String[] data : itemList){
                DhItem item = null;
                switch(data[TYPE_COL].toLowerCase().trim()){
                    case "ranged weapon":
                        item = new DhRangedWeapon(
                            DhItemType.RANGED_WEAPON, data[NAME_COL], data[RARITY_COL], data[NAME_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]), data[CLASS_COL],
                            data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL]
                        );
                        break;
                    case "melee weapon":
                        item = new DhMeleeWeapon(
                            DhItemType.MELEE_WEAPON, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                            data[TYPE_COL], data[RANGE_COL], data[DMG_COL], data[PEN_COL]
                            );
                        break;
                    case "explosive":
                        item = new DhRangedWeapon(
                            DhItemType.EXPLOSIVE, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                            data[TYPE_COL], data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL]
                            );
                        break;
                    case "armor":
                        item = new DhArmor(
                            DhItemType.ARMOR, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                            data[COVERS_COL], data[AP_COL], data[MAX_AG_COL]
                            );
                        break;
                    case "cybernetic":
                        item = new DhCybernetic(
                            DhItemType.CYBERNETIC, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                            data[CYBER_SLOTS_COL]
                            );
                        break;
                    case "misc":
                        item = new DhItem(DhItemType.MISC, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                        break;
                    case "weapon mod":
                        item = new DhItem(DhItemType.WEAPON_MOD, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                        break;
                    case "special ammo":
                        item = new DhItem(DhItemType.SPECIAL_AMMO, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                        break;
                    case "consumable":
                        item = new DhItem(DhItemType.CONSUMABLE, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                        break;
                    default:
                        System.out.println(String.format("WARNING: Failed to parse item %s from Starlight item sheet.", data[NAME_COL]));
                }
                if(item != null){
                    // Clean string, add to hashmaps for quick lookup
                    itemMap.put(item.getName().toLowerCase().replace("-", " "), item);
                    rarityMap.get(item.getRarity().toLowerCase()).add(item);
                    System.err.println(String.format("ADDED ITEM: %s", item.getName()));
                }
            }
        }
        catch(Exception e){
            System.out.println(String.format("[ERROR] : Parsing items from %s", Constants.ST_ITEMS_FILEPATH));
            e.printStackTrace();
        }
        
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

    public HashMap<String, DhItem> getItemMap(){
        return itemMap;
    }

    public DhItem getItem(String name){
        return itemMap.get(name.toLowerCase());
    }

    /**
     * Returns a number of given dhItems with a given rarity
     * @return Arraylist of requested items
     */
    public ArrayList<DhItem> getRandomItems(Integer scarce, Integer rare, Integer veryRare, Integer extremelyRare){
        ArrayList<DhItem> list = new ArrayList<>();
        for(int i = 0; i < scarce; i++){
            int index = (int)(Math.random() * rarityMap.get("scarce").size());
            DhItem item = rarityMap.get("scarce").get(index);
            if(list.contains(item))
                scarce++;
            else
                list.add(item);
        }
        for(int i = 0; i < rare; i++){
            int index = (int)(Math.random() * rarityMap.get("rare").size());
            DhItem item = rarityMap.get("rare").get(index);
            if(list.contains(item))
                rare++;
            else
                list.add(item);
        }
        for(int i = 0; i < veryRare; i++){
            int index = (int)(Math.random() * rarityMap.get("very rare").size());
            DhItem item = rarityMap.get("very rare").get(index);
            if(list.contains(item))
                veryRare++;
            else
                list.add(item);
        }
        for(int i = 0; i < extremelyRare; i++){
            int index = (int)(Math.random() * rarityMap.get("extremely rare").size());
            DhItem item = rarityMap.get("extremely rare").get(index);
            if(list.contains(item))
                extremelyRare++;
            else
                list.add(item);
        }
        return list;
    }
}
