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
import goobot.Constants.StarlightItemType;
import goobot.model.StarlightArmor;
import goobot.model.StarlightCybernetic;
import goobot.model.StarlightItem;
import goobot.model.StarlightMeleeWeapon;
import goobot.model.StarlightRangedWeapon;
import goobot.Constants.StarlightRarity;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class StarlightItemController {
    private HashMap<String, StarlightItem> itemMap;
    private HashMap<String, ArrayList<StarlightItem>> rarityMap;

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

    public StarlightItemController(){
        this.itemMap = new HashMap<>();
        this.rarityMap = new HashMap<>();
        rarityMap.put("ubiquitous", new ArrayList<StarlightItem>());
        rarityMap.put("abundant", new ArrayList<StarlightItem>());
        rarityMap.put("plentiful", new ArrayList<StarlightItem>());
        rarityMap.put("common", new ArrayList<StarlightItem>());
        rarityMap.put("average", new ArrayList<StarlightItem>());
        rarityMap.put("uncommon", new ArrayList<StarlightItem>());
        rarityMap.put("scarce", new ArrayList<StarlightItem>());
        rarityMap.put("rare", new ArrayList<StarlightItem>());
        rarityMap.put("very rare", new ArrayList<StarlightItem>());
        rarityMap.put("extremely rare", new ArrayList<StarlightItem>());
        rarityMap.put("near unique", new ArrayList<StarlightItem>());
        parseItems();
    }

    private void parseItems() {
        Path path = getSheetPath(Constants.ST_ITEMS_FILEPATH);
        try{
            List<String[]> itemList = readAllLines(path);
            itemList.remove(HEADER_ROW_INDEX);
    
            for(String[] data : itemList){
                StarlightItem item = null;
                switch(data[TYPE_COL].toLowerCase().trim()){
                    case "ranged weapon":
                        item = new StarlightRangedWeapon(
                            StarlightItemType.RANGED_WEAPON, data[NAME_COL], data[RARITY_COL], data[NAME_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]), data[CLASS_COL],
                            data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL]
                        );
                        break;
                    case "melee weapon":
                        item = new StarlightMeleeWeapon(
                            StarlightItemType.MELEE_WEAPON, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                            data[TYPE_COL], data[RANGE_COL], data[DMG_COL], data[PEN_COL]
                            );
                        break;
                    case "explosive":
                        item = new StarlightRangedWeapon(
                            StarlightItemType.EXPLOSIVE, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                            data[TYPE_COL], data[RANGE_COL], data[ROF_COL], data[DMG_COL], data[PEN_COL], data[MAG_COL], data[RELOAD_COL]
                            );
                        break;
                    case "armor":
                        item = new StarlightArmor(
                            StarlightItemType.ARMOR, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                            data[COVERS_COL], data[AP_COL], data[MAX_AG_COL]
                            );
                        break;
                    case "cybernetic":
                        item = new StarlightCybernetic(
                            StarlightItemType.CYBERNETIC, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]),
                            data[CYBER_SLOTS_COL]
                            );
                        break;
                    case "misc":
                        item = new StarlightItem(StarlightItemType.MISC, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                        break;
                    case "weapon mod":
                        item = new StarlightItem(StarlightItemType.WEAPON_MOD, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                        break;
                    case "special ammo":
                        item = new StarlightItem(StarlightItemType.SPECIAL_AMMO, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
                        break;
                    case "consumable":
                        item = new StarlightItem(StarlightItemType.CONSUMABLE, data[NAME_COL], data[RARITY_COL], data[SPECIAL_NOTES_COL], data[WEIGHT_COL], Integer.parseInt(data[PRICE_COL]));
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

    public HashMap<String, StarlightItem> getItemMap(){
        return itemMap;
    }

    public StarlightItem getItem(String name){
        return itemMap.get(name.toLowerCase());
    }

    /**
     * Returns a number of given dhItems with a given rarity
     * @return Arraylist of requested items
     */
    public ArrayList<StarlightItem> getRandomItems(Integer scarce, Integer rare, Integer veryRare, Integer extremelyRare){
        ArrayList<StarlightItem> list = new ArrayList<>();
        for(int i = 0; i < scarce; i++){
            int index = (int)(Math.random() * rarityMap.get("scarce").size());
            StarlightItem item = rarityMap.get("scarce").get(index);
            if(list.contains(item))
                scarce++;
            else
                list.add(item);
        }
        for(int i = 0; i < rare; i++){
            int index = (int)(Math.random() * rarityMap.get("rare").size());
            StarlightItem item = rarityMap.get("rare").get(index);
            if(list.contains(item))
                rare++;
            else
                list.add(item);
        }
        for(int i = 0; i < veryRare; i++){
            int index = (int)(Math.random() * rarityMap.get("very rare").size());
            StarlightItem item = rarityMap.get("very rare").get(index);
            if(list.contains(item))
                veryRare++;
            else
                list.add(item);
        }
        for(int i = 0; i < extremelyRare; i++){
            int index = (int)(Math.random() * rarityMap.get("extremely rare").size());
            StarlightItem item = rarityMap.get("extremely rare").get(index);
            if(list.contains(item))
                extremelyRare++;
            else
                list.add(item);
        }
        return list;
    }
}
