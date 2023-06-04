/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight;

import goobot.Constants.Rarity;
import goobot.Constants.TableType;
import goobot.Constants.ItemType;
import goobot.model.WeightedRandomBag;
import goobot.model.starlight.item.Item;
import goobot.service.ItemService;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemTable {
    private WeightedRandomBag<ItemType> weightedItemTypes;
    private List<ItemType> itemTypes;

    public ItemTable(TableType table){
        this.itemTypes = table.itemTypes;
        this.weightedItemTypes = new WeightedRandomBag<>();
        this.weightedItemTypes.addEntries(itemTypes, table.itemWeights);
    }
    
    public Item getItem(Rarity rarity) {
        ItemType type = weightedItemTypes.getRandom();
        ArrayList<Item> scambledList = ItemService.getItemByType(type);
        while(true){
            Item item = getItemOfRarity(rarity, scambledList);
            if(item != null || rarity == null)
                return item;
            rarity = rarity.prev();
        }
    }

    private Item getItemOfRarity(Rarity rarity, ArrayList<Item> scambledList){
        Collections.shuffle(scambledList);
        for(Item item : scambledList){
            if(item.getRarity() == rarity)
                return item;
        }
        return null;
    }
    
    public ArrayList<Item> getItems(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        System.out.println(String.format("Shop Request Rarities:\n ub: %d\n ab: %s\n pl: %s\n co: %s\n av: %s\n sc: %s\n ra: %s\n vr: %s\n er: %s\n nu: %s\n",
        numUbiquitous, numAbundant, numPlentiful, numCommon, numAverage, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique));
        Set<Item> set = new HashSet<>();
        set = getItemsOfRarity(Rarity.UBIQUITOUS, numUbiquitous, set);
        set = getItemsOfRarity(Rarity.ABUNDANT, numAbundant, set);
        set = getItemsOfRarity(Rarity.PLENTIFUL, numPlentiful, set);
        set = getItemsOfRarity(Rarity.COMMON, numCommon, set);
        set = getItemsOfRarity(Rarity.AVERAGE, numAverage, set);
        set = getItemsOfRarity(Rarity.SCARCE, numScarce, set);
        set = getItemsOfRarity(Rarity.RARE, numRare, set);
        set = getItemsOfRarity(Rarity.VERY_RARE, numVeryRare, set);
        set = getItemsOfRarity(Rarity.EXTREMELY_RARE, numExtremelyRare, set);
        set = getItemsOfRarity(Rarity.NEAR_UNIQUE, numNearUnique, set);
        ArrayList<Item> list = new ArrayList<>(set);
        list.sort(Comparator.comparing(Item::getRarity));
        return list;
    }

    private Set<Item> getItemsOfRarity(Rarity rarity, int size, Set<Item> existingItems){
        System.out.println(String.format("Shop Request: Getting inventory of rarity: %s size: %d", rarity, size));
        if(size <= 0){
            return existingItems;
        }
       
        Integer existingSetSize = existingItems.size();
        Set<Item> set = existingItems;
        List<ItemType> exhaustedTypes = new ArrayList<>();
        
        while(true){
            Item item = null;
            Rarity currentRarity = rarity;
            ItemType currentType = weightedItemTypes.getRandom();
            // Get item for shop inventory of correct rarity and type
            while(true){
                item = inventoryFetcher(currentType, currentRarity, set);
                if(item != null){
                    break;
                }
                // Downgrade rarity of item if none found at initial rarity
                currentRarity = currentRarity.prev();
                // Break if no more valid items
                if(currentRarity == null){
                    System.out.println("Shop Request: Encountered bottom of rarity list for type:" + currentType);
                    exhaustedTypes.add(currentType);
                    break;
                }
            }
            if(item != null){
                System.out.println(String.format("Shop Request: Item found: %s %s", item.getName(), item.getRarity()));
                set.add(item);
            }
            if(set.size() == size + existingSetSize){
                System.out.println("Shop Request: Inventory reached target size of " + size + ".");
                break;
            }
            if(exhaustedTypes.equals(itemTypes)){
                System.out.println("Shop Request: All shop types exhausted. Exiting with size " + set.size() + ".");
                break;
            }
        }
        return set;
    }

    private Item inventoryFetcher(ItemType type, Rarity rarity, Set<Item> existingItems){
        System.out.println("Shop Request: Fetching new item of rarity: " + rarity + " type: " + type);
        for(Item item : ItemService.getItemByType(type)){
            if(item.getRarity() == rarity && !existingItems.contains(item)){
                return item;
            }
        }
        for(Item item : ItemService.getItemByType(type)){
            if(item.getRarity() == rarity && !existingItems.contains(item)){
                return item;
            }
        }
        return null;
    }
}
