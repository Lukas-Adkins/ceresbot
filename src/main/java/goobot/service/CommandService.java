/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.service;

import goobot.Constants;
import goobot.Constants.TableType;
import goobot.model.dnd.DndSpell;
import goobot.model.starlight.item.Item;
import goobot.model.starlight.item.Mech;
import goobot.model.starlight.TableRequest;
import goobot.service.CommandService;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

public class CommandService {
    public SpellService spellService;
    public CharacterService characterService;
    public ItemService itemService;

    private Random rng;

    public CommandService(){
        this.spellService = new SpellService(Constants.SPELLS_FILEPATH);
        this.characterService = new CharacterService(Constants.CHARACTER_FILEPATHS);
        this.itemService = new ItemService();
        this.rng = new Random();
    }

    /**
     * Looks up character info.
     * @param args Arguments, contains name of character
     * @return Tuple representing character information, and the character image URL
     */
    public List<String> CharacterInfo(String args){
        String characterName = args.replace("-", " ");
        List<String> dndChar = this.characterService.getCharacter(characterName);
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
        DndSpell spell = spellService.getSpell(spellName);
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
        DndSpell spell = spellService.getSpell(spellName);
        if(spell != null){
            return spell.getPrice();
        }
        else
            return Constants.SPELL_NOT_FOUND_MSG;
    }

    /**
     * Gets information about a particular Starlight item.
     * @param args Name of the item
     * @return String item information
     */
    public List<String> Item(String args){
        String itemName = args.replace("-", " ");
        Item item = itemService.getItem(itemName);
        if(item != null){
            if(item instanceof Mech){
                Mech mech = (Mech) item;
                return Arrays.asList(mech.toString(), mech.getUrl());
            }
            return Arrays.asList(item.toString(), "");
        }
        else
            return Arrays.asList(Constants.ITEM_NOT_FOUND_MSG, "");
    }

    /**
     * Generates a shop full of random items for the Starlight game system.
     * @param args Commerce skill of the shopkeeper
     * @return Store information formatted as a string
     */
    public String Shop(String args) throws Exception {
        String arr[] = args.split(" ", 2);        
        Integer commerce = Integer.parseInt(arr[1]),
        commerceMod = Math.round(commerce/10),
        maxItems = 3 * commerceMod, 
        minItems = 2 * commerceMod, 
        items = rng.nextInt(maxItems - minItems + 1) + minItems;
        String shopList = "", meleeList = "", rangedList = "", armorList = "", explosiveList = "", cyberneticList = "", modList = "", ammoList = "", miscList = "",
        mechEngineList = "", mechUtilityList = "", mechMeleeList = "", mechRangedList = "", mechList = "";
        if(items > 15)
            items = 15;
        
        TableType tableType = TableType.valueOf(arr[0].toUpperCase() + "_SHOP");
        ArrayList<Item> itemList = itemService.getItemsByTable(tableType, TableRequest.requisition(commerce, items));
        for (Item item : itemList){
            switch (item.getType()){
                case MELEE_WEAPON:
                    meleeList = meleeList + " " + item.displayShop();
                    break;
                case RANGED_WEAPON:
                    rangedList = rangedList + " " + item.displayShop();
                    break; 
                case ARMOR:
                    armorList = armorList + " " + item.displayShop();
                    break; 
                case EXPLOSIVE:
                    explosiveList = explosiveList + " " + item.displayShop();
                    break;  
                case CYBERNETIC:
                    cyberneticList = cyberneticList + " " + item.displayShop();
                    break;
                case WEAPON_MOD:
                    modList = modList + " " + item.displayShop();
                    break;
                case SPECIAL_AMMO:
                    ammoList = ammoList + " " + item.displayShop();
                    break;
                case CONSUMABLE:
                    miscList = miscList + " " + item.displayShop();
                    break;
                case MISC:
                    miscList = miscList + " " + item.displayShop();
                    break; 
                case MECH_ENGINE:
                    mechEngineList = mechEngineList + " " + item.displayShop();
                    break;
                case MECH_UTILITY:
                    mechUtilityList = mechUtilityList + " " + item.displayShop();
                    break;
                case MECH_MELEE_WEAPON:
                    mechMeleeList = mechMeleeList + " " + item.displayShop();
                    break;
                case MECH_RANGED_WEAPON:
                    mechRangedList = mechRangedList + " " + item.displayShop();
                    break;
                case MECH:
                    mechList = mechList + " " + item.displayShop();
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
        if(!mechRangedList.isEmpty()){
            shopList = shopList + "Mech Ranged Weapon Systems\n" + mechRangedList + "\n";
        }
        if(!mechMeleeList.isEmpty()){
            shopList = shopList + "Mech Melee Weapon Systems\n" + mechMeleeList + "\n";
        }
        if(!mechUtilityList.isEmpty()){
            shopList = shopList + "Mech Utility Systems\n" + mechUtilityList + "\n";
        }
        if(!mechEngineList.isEmpty()){
            shopList = shopList + "Mech Engine System\n" + mechEngineList + "\n";
        }
        return String.format("```ansi\n%s```", shopList);
    }

    public String Loot(String args){
        TableType tableType = TableType.valueOf(args.toUpperCase() + "_TABLE");
        ArrayList<Item> items = itemService.getItemsByTable(
            tableType, TableRequest.requisition(tableType.commerce, rng.nextInt(tableType.maxItems - tableType.minItems + 1) + tableType.minItems)
        );
        return String.format("```ansi\n%s```", formatLoot(items));
    }

    private String formatLoot(ArrayList<Item> items){
        StringBuilder sb = new StringBuilder();
        for(Item item : items){
            sb.append(item.displayLoot());
        }
        return sb.toString();
    }
}
