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
            Path path = Paths.get("src/main/resources", spellFilename);
            System.out.println(path.toAbsolutePath());
            jsonString = Files.readString(path);
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
                spellMap.put(spell.name.trim().toLowerCase(), spell);
        }
    }

    public Spell getSpell(String name){
        return spellMap.get(name);
    }
}
