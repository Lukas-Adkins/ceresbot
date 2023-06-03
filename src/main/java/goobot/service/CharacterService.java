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

import com.opencsv.CSVReader;

import goobot.Constants;
import goobot.model.Character;

public class CharacterService {
    private HashMap<String, Character> characterMap;
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

    public CharacterService(List<String> characterFilepaths){
        this.characterMap = new HashMap<>();
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

    /**
     * Parses character CSV files
     * @param filepath
     * @throws Exception
     */
    private void parseCharacters(Path filepath) throws Exception {
            List<String[]> csvList = readAllLines(filepath);
            csvList.remove(HEADER_ROW_INDEX);
            for(String[] strList : csvList){
                // Convert CSV to DndCharacter object
                Character character = new Character(
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
                this.characterMap.put(strList[0].trim().toLowerCase().replace('-', ' '), character);
                String[] words = character.getName().split("\\s+");
                String firstname = words[0].trim().toLowerCase().replace("-", " ");
                if(firstNameMap.get(firstname) == null) // If character doesn't exist, add mapping to full name
                    firstNameMap.put(firstname, character.getName());
                else { // If exists, add mapping to possible names
                    String previousNames = firstNameMap.get(firstname);
                    if(!previousNames.contains(character.getName()))
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
    private List<String[]> readAllLines(Path filePath) throws Exception {
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

    /**
     * Searches for a character and returns their corresponding info. 
     * @param Character name
     * @return Textbody and image tuple
     */
    public List<String> getCharacter(String name){
        String[] words = name.split("\\s+");
        Character character;
        if(words.length == 1){ // First name search
            String potentialNames = this.firstNameMap.get(name);
            if(potentialNames == null)
                return Arrays.asList(Constants.CHARACTER_NOT_FOUND_MSG, "");
            if(potentialNames.contains(", ")){ // There are multiple possible firstnames
                String msg = "Multiple characters have first name '" + name +"'. Do you mean " + potentialNames + "?";
                return Arrays.asList(msg, "");
            }
            name = potentialNames.trim().toLowerCase().replace('-', ' ');
        }
        // Full name search
        character = this.characterMap.get(name.trim().toLowerCase().replace('-', ' '));
        if(character != null)
            return Arrays.asList(character.toString(), character.getImage()); // Return textbody - image tuple
        return Arrays.asList(Constants.CHARACTER_NOT_FOUND_MSG, "");
    }
}