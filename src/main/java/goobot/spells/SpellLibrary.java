/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.spells;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class SpellLibrary {
    private HashMap<String, Spell> spellMap;

    public SpellLibrary(String spellsFilename){
        this.spellMap = new HashMap<>();
        String spells = readJsonFile(spellsFilename);
        parseSpells(spells);
    }

    /**
     * Reads JSON file and returns its contents as a String
     * @param filename File path
     * @return String containing JSON data
     */
    private String readJsonFile(String filename){
        String functionName = "[readJsonFile()] ";
        String jsonString = null;
        String RES_PATH = "src/main/resources", CURRENT_DIR_PATH = "";
        List<String> jsonFilepaths = Arrays.asList(RES_PATH, CURRENT_DIR_PATH);

        // Search three possible locations for json file.
        try{
            for(String filepath : jsonFilepaths){
                Path path = Paths.get(filepath, filename);
                System.out.println("SEARCHING --- " + path.toAbsolutePath());
                if(Files.isReadable(path)){
                    System.out.println("FOUND --- " + path.toAbsolutePath());
                    jsonString = Files.readString(path);
                    break;
                }
            }
            if(jsonString == null)
                throw new FileNotFoundException("Could not find spells.json file at " + filename);
        }
        catch(Exception e){
            System.err.println(functionName + e);
            System.exit(1);
        }
        return jsonString;
    }

    /**
     * Reads spells from spells.json file.
     * @param jsonString String representation of spells JSON file
     */
    private void parseSpells(String jsonString){
        Gson gson = new Gson();
        Spell[] spellArray = gson.fromJson(jsonString, Spell[].class);
        if(spellArray != null){
            for (Spell spell : spellArray)
                spellMap.put(spell.getName().trim().toLowerCase().replace("-", " "), spell);
        }
    }

    public Spell getSpell(String name){
        return spellMap.get(name);
    }
}
