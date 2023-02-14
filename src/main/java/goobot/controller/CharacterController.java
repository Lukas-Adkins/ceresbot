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

    public DndCharacter getCharacter(String dndChar){
        return this.charMap.get(dndChar.trim().toLowerCase().replace('-', ' '));
    }
}