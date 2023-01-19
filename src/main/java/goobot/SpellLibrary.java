package goobot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class SpellLibrary {
    public HashMap<String, Spell> spellMap;

    public SpellLibrary(String spellFilename){
        String jsonString = null, RES_PATH = "src/main/resources", CURRENT_DIR_PATH = "";
        String FAT_JAR_PATH = Paths.get("").toAbsolutePath().getParent().getParent().toString() + "/" + RES_PATH;
        List<String> jsonFilepaths = Arrays.asList(FAT_JAR_PATH, RES_PATH, CURRENT_DIR_PATH);
        spellMap = new HashMap<>();
        // Search three possible locations for spells.json file.
        try{
            for(String filepath : jsonFilepaths){
                Path path = Paths.get(filepath, spellFilename);
                System.out.println("SEARCHING --- " + path.toAbsolutePath());
                if(Files.isReadable(path)){
                    System.out.println("FOUND --- " + path.toAbsolutePath());
                    jsonString = Files.readString(path);
                    break;
                }
            }
            if(jsonString == null)
                throw new FileNotFoundException("Could not find spells.json file.");
        }
        catch(Exception e){
            System.err.println("Failed to parse spells! Exiting...");
            System.err.println(e);
            System.exit(1);
        }
        if(jsonString != null){
            parseSpells(jsonString);
        }
    }

    private void parseSpells(String jsonString){
        Gson gson = new Gson();
        Spell[] spellArray = gson.fromJson(jsonString, Spell[].class);
        if(spellArray != null){
            for (Spell spell : spellArray)
                spellMap.put(spell.name.trim().toLowerCase().replace("-", " "), spell);
        }
    }

    public Spell getSpell(String name){
        return spellMap.get(name);
    }
}
