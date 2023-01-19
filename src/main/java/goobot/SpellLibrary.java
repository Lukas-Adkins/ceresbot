package goobot;

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

import com.google.gson.Gson;

public class SpellLibrary {
    public HashMap<String, Spell> spellMap;

    public SpellLibrary(String spellFilename){
        String jsonString = null;
        spellMap = new HashMap<>();
        try{
            // Tries to check resources folder
            Path path = Paths.get("src/main/resources", spellFilename);
            if(Files.isReadable(path)){
                jsonString = Files.readString(path);
            }
            else{
                // Tries to check root folder for fat JAR run
                path = Paths.get(spellFilename);
                jsonString = Files.readString(path);
            }
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
