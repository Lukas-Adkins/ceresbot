/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.controller;

import java.util.Random;

import goobot.Constants;
import goobot.controller.dnd.DndSpellController;
import goobot.controller.starlight.ItemController;
import goobot.controller.starlight.LootTableController;
import goobot.model.dnd.DndSpell;
import goobot.model.starlight.StItem;

import java.util.Arrays;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandController {
    public DndSpellController spellLibrary;
    public CharacterController characterLibrary;
    public ItemController stItemController;
    public LootTableController lootTable;
    public Random rng = new Random();

    /**
     * Initilizes controller
     * @param spellsFilename Filename of spells json
     */
    public CommandController(String spellsFilepath, List<String> characterFilepaths){
        try{
            this.spellLibrary = new DndSpellController(spellsFilepath);
            this.characterLibrary = new CharacterController(characterFilepaths);
            this.stItemController = new ItemController();
            this.lootTable = stItemController.getLootTable();
        }
        catch(Exception e){
            System.err.println(Constants.BOT_START_ERROR);
            System.exit(Constants.FATAL_FAILURE);
        }
    }

    /**
     * Posts a list of bot commands at a user's request.
     * @param args Arguments, unused for this command
     * @return String list of bot commands
     */
    public String Help(String args){
        return Constants.HELP_MSG;
    }

    /**
     * Posts a response to a user's ping request
     * @param args Arguments, unused for this command
     * @return String response to a user's ping request
     */
    public String Ping(String args){
        return Constants.PONG_MSG;
    }

    /**
     * Posts a response to a user's pat request
     * @param args Arguments, unused for this command
     * @return String representing custom emoji
     */
    public String Pat(String args){
        return Constants.PAT_MSG;
    }

    /**
     * Looks up character info.
     * @param args Arguments, contains name of character
     * @return Tuple representing character information, and the character image URL
     */
    public List<String> CharacterInfo(String args){
        String characterName = args.replace("-", " ");
        List<String> dndChar = this.characterLibrary.getCharacter(characterName);
        if(dndChar != null)
            return dndChar;
        return Arrays.asList(Constants.CHARACTER_NOT_FOUND_MSG, "");
    }

    /**
     * Simulates a tabletop dice roll.
     * @param args Dice and number of dice in <dice>d<dice> format
     * @return Formatted string with roll total and individually rolled dice
     */
    public String Roll(String args){
        Pattern dicePattern = Pattern.compile("([\\+\\-]?\\d+)?(d[\\+\\-]?\\d+)(k[\\+\\-]?\\d+)?([\\+\\-]\\d+)?");
        String sanitized = args.replace(" ", "");
        Matcher diceMatcher = dicePattern.matcher(sanitized);
        int numKeep = -1, numDice = 1, numSides = 20, modifier = 0, total = 0;

        if(diceMatcher.find()){
			if(diceMatcher.group(1)!=null && !diceMatcher.group(1).isEmpty())
                numDice  = Integer.parseInt(diceMatcher.group(1));
			if(diceMatcher.group(2)!=null && !diceMatcher.group(2).isEmpty())
                numSides = Integer.parseInt(diceMatcher.group(2).substring(1));
			if(diceMatcher.group(3)!=null && !diceMatcher.group(3).isEmpty())
                numKeep  = Integer.parseInt(diceMatcher.group(3).substring(1));
			else 
                numKeep=numDice;
            if(diceMatcher.group(4)!=null && !diceMatcher.group(4).isEmpty())
                modifier = Integer.parseInt(diceMatcher.group(4));
            System.out.println(numDice + " " + numKeep + " " + numSides + " " + modifier);
        }

        try { 
            // Sanity check
            if(numDice <= 0 || numSides <= 0 || Math.abs(numKeep)>numDice || !args.matches(".*\\d+.*"))
                throw new Exception();
            
            ArrayList<Integer> rolledDice = rollDice(numDice, numSides);
            for(Integer die : rolledDice){
                total += die;
            }
            total += modifier; // Add math modifier to roll
            if(numDice == 1 && modifier == 0) // If there's only one die, return without showing individually rolled die
                return "**" + total + "**";
            
            String returnMessage = rolledDice.toString();
            if(modifier < 0)
                returnMessage += " - " + Math.abs(modifier) + " = ";
            else
                returnMessage += " + " + modifier + " = ";
            return returnMessage + "**" + total + "**";

        }
        catch(Exception e){
            return Constants.DICE_NOT_PARSED_MSG;
        }
    }

    private ArrayList<Integer> rollDice(int numDice, int numSides){
        ArrayList<Integer> rolledValues = new ArrayList<>();
        Random diceRoller = new Random();
        
        for(int i = 0; i < numDice; i++){ // Add up dice totals
            int num = diceRoller.nextInt(numSides) + 1;
            rolledValues.add(num);
        }
        return rolledValues;
    }

    /**
     * Gets information on a particular D&D spell from spells.json
     * @param args Name of the spell
     * @return Spell information
     */
    public String Spell(String args){
        String spellName = args.replace("-", " ");
        DndSpell spell = spellLibrary.getSpell(spellName);
        if(spell != null){
            return spell.toString();
        }
        else
            return Constants.SPELL_NOT_FOUND_MSG;
    }

    /**
     * Gets information on a spell scroll from a particular D&D spell from spells.json
     * @param args Name of the spell
     * @return String scroll information
     */
    public String SpellScroll(String args){
        String spellName = args.replace("-", " ");
        DndSpell spell = spellLibrary.getSpell(spellName);
        if(spell != null){
            return spell.getPrice();
        }
        else
            return Constants.SPELL_NOT_FOUND_MSG;
    }

    /**
     * Gets information about a particular dh2e item.
     * @param args Name of the item
     * @return String item information
     */
    public String dhItem(String args){
        String itemName = args.replace("-", " ");
        StItem item = lootTable.getItem(itemName);
        if(item != null){
            return item.toString();
        }
        else
            return Constants.ITEM_NOT_FOUND_MESSAGE;
    }

    /**
     * Generates a shop full of random items for the Starlight game system.
     * @param args Commerce skill of the shopkeeper
     * @return Store information formatted as a string
     */
    public String dhShop(String args){
        Integer commerceSkill = Integer.parseInt(args);
        Integer commerceSkillMod = Math.round(commerceSkill/10);
        Integer maxItems = 3 * commerceSkillMod, minItems = 1 * commerceSkillMod, numberOfItems = rng.nextInt(maxItems - minItems + 1) + minItems;
        Integer scarce = 0, rare = 0, veryRare = 0, extremelyRare = 0;
        if(numberOfItems > 14)
            numberOfItems = 14;
        for(int i = 0; i < numberOfItems; i++){
            Integer d100 = rng.nextInt(100) + 1;
            if(d100 < commerceSkill - 40)
                extremelyRare++;
            else if(d100 < commerceSkill - 30)
                veryRare++;
            else if(d100 < commerceSkill - 10)
                rare++;
            else
                scarce++;
        }
    
        String shopList = "Welcome to the CeresBot Starlight storefront generator!\nGenerated a store inventory based on a shopkeep with a Commerce skill of "
         + commerceSkill + ".\n\n";
        String meleeList = "", rangedList = "", armorList = "", explosiveList = "", cyberneticList = "", modList = "", ammoList = "", miscList = "",
        mechEngineList = "", mechUtilityList = "", mechMeleeList = "", mechRangedList = "";
        ArrayList<StItem> itemList = lootTable.getRandomItems(scarce, rare, veryRare, extremelyRare);
        for (StItem item : itemList){
            switch (item.getType()){
                case MELEE_WEAPON:
                    meleeList = meleeList + " " + item.getShopString();
                    break;
                case RANGED_WEAPON:
                    rangedList = rangedList + " " + item.getShopString();
                    break; 
                case ARMOR:
                    armorList = armorList + " " + item.getShopString();
                    break; 
                case EXPLOSIVE:
                    explosiveList = explosiveList + " " + item.getShopString();
                    break;  
                case CYBERNETIC:
                    cyberneticList = cyberneticList + " " + item.getShopString();
                    break;
                case WEAPON_MOD:
                    modList = modList + " " + item.getShopString();
                    break;
                case SPECIAL_AMMO:
                    ammoList = ammoList + " " + item.getShopString();
                    break;
                case CONSUMABLE:
                    miscList = miscList + " " + item.getShopString();
                    break;
                case MISC:
                    miscList = miscList + " " + item.getShopString();
                    break; 
                case MECH_ENGINE:
                    mechEngineList = mechEngineList + " " + item.getShopString();
                    break;
                case MECH_UTILITY:
                    mechUtilityList = mechUtilityList + " " + item.getShopString();
                    break;
                case MECH_MELEE_WEAPON:
                    mechMeleeList = mechMeleeList + " " + item.getShopString();
                    break;
                case MECH_RANGED_WEAPON:
                    mechRangedList = mechRangedList + " " + item.getShopString();
                    break;
            }
        }
        if(!meleeList.isEmpty()){
            shopList = shopList + "Melee Weapons\n" + meleeList + "\n";
        }
        if(!rangedList.isEmpty()){
            shopList = shopList + "Ranged Weapons\n" + rangedList + "\n";
        }
        if(!armorList.isEmpty()){
            shopList = shopList + "Armor\n" + armorList + "\n";
        }
        if(!explosiveList.isEmpty()){
            shopList = shopList + "Explosives\n" + explosiveList + "\n";
        }
        if(!cyberneticList.isEmpty()){
            shopList = shopList + "Cybernetics\n" + cyberneticList + "\n";
        }
        if(!modList.isEmpty()){
            shopList = shopList + "Weapon Mods\n" + modList + "\n";
        }
        if(!ammoList.isEmpty()){
            shopList = shopList + "Special Ammo\n" + ammoList + "\n";
        }
        if(!miscList.isEmpty()){
            shopList = shopList + "Miscellaneous\n" + miscList + "\n";
        }
        return String.format("```ansi\n%s```", shopList);
    }
}
