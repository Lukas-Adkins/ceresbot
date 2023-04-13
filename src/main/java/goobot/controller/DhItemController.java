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

public class DhItemController {
    private HashMap<String, DhItem> itemMap;
    public static final int HEADER_ROW_INDEX = 0;

    public DhItemController(){
        this.itemMap = new HashMap<>();
        try{
            parseItems();
        }
        catch(Exception e){
            System.err.println("Error parsing DHitems: " + e);
            System.exit(Constants.FATAL_FAILURE);
        }
    }

    private void parseItems() throws Exception {
        // Gets gear
        Path itemsPath = getSheetPath("dh/gear.csv");
        List<String[]> csvList = readAllLines(itemsPath);
        csvList.remove(HEADER_ROW_INDEX);
        for(String[] list : csvList){
            DhItem item = new DhItem(DhItemType.MISC, list[0].trim(), list[1], list[2], list[3], Integer.parseInt(list[4]));
            itemMap.put(item.getName().toLowerCase().replace("-", " "), item);
        }

        // Gets consumables
        itemsPath = getSheetPath("dh/consumables.csv");
        csvList = readAllLines(itemsPath);
        csvList.remove(HEADER_ROW_INDEX);
        for(String[] list : csvList){
            DhItem item = new DhItem(DhItemType.CONSUMABLE, list[0].trim(), list[1], list[2], "0kg", Integer.parseInt(list[3]));
            itemMap.put(item.getName().toLowerCase().replace("-", " "), item);
        }

        // Gets weapon mods
        itemsPath = getSheetPath("dh/weapon_mods.csv");
        csvList = readAllLines(itemsPath);
        csvList.remove(HEADER_ROW_INDEX);
        for(String[] list : csvList){
            DhItem item = new DhItem(DhItemType.WEAPON_MOD, list[0].trim(), list[1], list[2], list[3], Integer.parseInt(list[4]));
            itemMap.put(item.getName().toLowerCase().replace("-", " "), item);
        }

        // Gets special ammo
        itemsPath = getSheetPath("dh/special_ammo.csv");
        csvList = readAllLines(itemsPath);
        csvList.remove(HEADER_ROW_INDEX);
        for(String[] list : csvList){
            DhItem item = new DhItem(DhItemType.SPECIAL_AMMO, list[0].trim(), list[1], list[2], "0kg", Integer.parseInt(list[3]));
            itemMap.put(item.getName().toLowerCase().replace("-", " "), item);
        }

        // Gets cybernetics
        itemsPath = getSheetPath("dh/cybernetics.csv");
        csvList = readAllLines(itemsPath);
        csvList.remove(HEADER_ROW_INDEX);
        for(String[] list : csvList){
            DhItem item = new DhCybernetic(DhItemType.CYBERNETIC, list[0], list[2], list[3], "0kg", Integer.parseInt(list[4]), Integer.parseInt(list[1]));
            itemMap.put(item.getName().toLowerCase().replace("-", " "), item);
        }

        // Gets armor
        itemsPath = getSheetPath("dh/armor.csv");
        csvList = readAllLines(itemsPath);
        csvList.remove(HEADER_ROW_INDEX);
        for(String[] list : csvList){
            DhItem item = new DhArmor(DhItemType.ARMOR, list[0], list[5], list[6], list[4], Integer.parseInt(list[7]), list[1], Integer.parseInt(list[2]), Integer.parseInt(list[3]));
            itemMap.put(item.getName().toLowerCase().replace("-", " "), item);
        }

        for(DhItem item : itemMap.values()){
            System.out.println(item.toString());
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
}
