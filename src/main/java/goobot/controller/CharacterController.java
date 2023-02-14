/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.controller;

import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.io.Reader;
import com.opencsv.CSVReader;
import goobot.model.DndCharacter;

import goobot.Constants;

public class CharacterController {
    private HashMap<String, DndCharacter> charMap;
    private HashMap<String, String> firstNameMap;

    private int HEADER_ROW_INDEX = 0;
    private static final int 
        NAME_INDEX = 0,
        COUNTRY_INDEX = 2,
        TITLE_INDEX = 3,
        GENDER_INDEX = 4,
        SPECIES_INDEX = 5,
        AGE_INDEX = 6,
        HEIGHT_INDEX = 7,
        HAIR_COLOR_INDEX = 8,
        EYE_COLOR_INDEX = 9,
        RELIGION_INDEX = 12,
        STATUS_INDEX = 13,
        DESCRIPTION_INDEX = 14,
        IMAGE_INDEX = 15;

    public CharacterController(List<String> characterFilepaths) throws IOException {
        this.charMap = new HashMap<>();
        this.firstNameMap = new HashMap<>();
        try{
            for(String fp : characterFilepaths){
                Path filepath = getSheetPath(fp);
    
                parseCharacters(filepath);
            }
        }
        catch(Exception e){
            System.err.println(e);
            System.err.println(Constants.CHARACTER_CSV_NOT_FOUND_ERROR);
            System.err.println(characterFilepaths.toString());
            System.exit(Constants.FATAL_FAILURE);
        }
    }

    public void parseCharacters(Path filepath) throws Exception {
            List<String[]> csvList = readAllLines(filepath);
            csvList.remove(HEADER_ROW_INDEX);
            for(String[] strList : csvList){
                // Convert CSV to DndCharacter object
                DndCharacter character = new DndCharacter(
                    strList[NAME_INDEX],
                    strList[COUNTRY_INDEX],
                    strList[TITLE_INDEX],
                    strList[GENDER_INDEX],
                    strList[SPECIES_INDEX],
                    strList[AGE_INDEX],
                    strList[HEIGHT_INDEX],
                    strList[HAIR_COLOR_INDEX],
                    strList[EYE_COLOR_INDEX],
                    strList[RELIGION_INDEX],
                    strList[STATUS_INDEX],
                    strList[DESCRIPTION_INDEX],
                    strList[IMAGE_INDEX]
                );
                this.charMap.put(strList[0].trim().toLowerCase().replace('-', ' '), character);
                String[] words = character.getName().split("\\s+");
                String firstname = words[0].trim().toLowerCase().replace("-", " ");
                if(firstNameMap.get(firstname) == null) // If character doesn't exist, add mapping to full name
                    firstNameMap.put(firstname, character.getName());
                else { // If exists, add mapping to possible names
                    String previousNames = firstNameMap.get(firstname);
                    firstNameMap.put(firstname, previousNames + ", or " + character.getName());
                }
            }
    }

    /**
     * Reads all lines from a CSV character file
     * @param filePath CSV filepath
     * @return String matrix of csv values
     * @throws Exception
     */
    public List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    /**
     * Gets the path of an character CSV file, if it exists
     * @param filename Name of file without path
     * @return String Filepath, if file exists
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

    public List<String> getCharacter(String dndChar){
        String[] words = dndChar.split("\\s+");
        DndCharacter character;
        if(words.length == 1){ // First name search
            System.out.println(dndChar);
            System.out.println(this.firstNameMap.keySet().toString());
            String potentialNames = this.firstNameMap.get(dndChar);
            if(potentialNames == null)
                return Arrays.asList(Constants.CHARACTER_NOT_FOUND_MSG, "");
            if(potentialNames.contains(", ")){ // There are multiple possible firstnames
                String msg = "Multiple characters have first name '" + dndChar +"'. Do you mean " + potentialNames + "?";
                return Arrays.asList(msg, "");
            }
            dndChar = potentialNames.trim().toLowerCase().replace('-', ' ');
        }
        // Full name search
        character = this.charMap.get(dndChar.trim().toLowerCase().replace('-', ' '));
        if(character != null)
            return Arrays.asList(character.toString(), character.getImage()); // Return textbody - image tuple
        return Arrays.asList(Constants.CHARACTER_NOT_FOUND_MSG, "");
    }
}