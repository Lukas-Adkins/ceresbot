/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.table;

import goobot.Constants.StRarity;
import goobot.Constants.StItemType;
import goobot.model.WeightedRandomBag;
import goobot.model.starlight.StItem;

import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class StItemTable {
    HashMap<StItemType, ArrayList<StItem>> itemsByType;
    WeightedRandomBag<StItemType> randomItemType;
    Set<StItemType> shopTypes;

    public StItemTable(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        this.itemsByType = itemsByType;
        this.randomItemType = new WeightedRandomBag<>();
        this.shopTypes = new HashSet<>();
    }

    public StItemTable(HashMap<StItemType, ArrayList<StItem>> itemsByType, Set<StItemType> shopTypes){
        this.itemsByType = itemsByType;
        this.randomItemType = new WeightedRandomBag<>();
        this.shopTypes = shopTypes;
    }
    
    public StItem getItem(StRarity rarity) {
        StItemType type = randomItemType.getRandom();
        ArrayList<StItem> scambledList = itemsByType.get(type);
        while(true){
            StItem item = getItemOfRarity(rarity, scambledList);
            if(item != null || rarity == null)
                return item;
            rarity = rarity.prev();
        }
    }

    private StItem getItemOfRarity(StRarity rarity, ArrayList<StItem> scambledList){
        Collections.shuffle(scambledList);
        for(StItem item : scambledList){
            if(item.getRarity() == rarity)
                return item;
        }
        return null;
    }
    
    public ArrayList<StItem> getItems(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        System.out.println(String.format("Shop Request Rarities:\n ub: %d\n ab: %s\n pl: %s\n co: %s\n av: %s\n sc: %s\n ra: %s\n vr: %s\n er: %s\n nu: %s\n",
        numUbiquitous, numAbundant, numPlentiful, numCommon, numAverage, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique));
        Set<StItem> set = new HashSet<>();
        set = getItemsOfRarity(StRarity.UBIQUITOUS, numUbiquitous, set);
        set = getItemsOfRarity(StRarity.ABUNDANT, numAbundant, set);
        set = getItemsOfRarity(StRarity.PLENTIFUL, numPlentiful, set);
        set = getItemsOfRarity(StRarity.COMMON, numCommon, set);
        set = getItemsOfRarity(StRarity.AVERAGE, numAverage, set);
        set = getItemsOfRarity(StRarity.SCARCE, numScarce, set);
        set = getItemsOfRarity(StRarity.RARE, numRare, set);
        set = getItemsOfRarity(StRarity.VERY_RARE, numVeryRare, set);
        set = getItemsOfRarity(StRarity.EXTREMELY_RARE, numExtremelyRare, set);
        set = getItemsOfRarity(StRarity.NEAR_UNIQUE, numNearUnique, set);
        ArrayList<StItem> list = new ArrayList<>(set);
        list.sort(Comparator.comparing(StItem::getRarity));
        return list;
    }

    private Set<StItem> getItemsOfRarity(StRarity rarity, int size, Set<StItem> existingItems){
        System.out.println(String.format("Shop Request: Getting inventory of rarity: %s size: %d", rarity, size));
        if(size <= 0){
            return existingItems;
        }
       
        Integer existingSetSize = existingItems.size();
        Set<StItem> set = existingItems;

        Set<StItemType> exhaustedTypes = new HashSet<>();
        
        while(true){
            StItem item = null;
            StRarity currentRarity = rarity;
            StItemType currentType = randomItemType.getRandom();
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
            if(exhaustedTypes.equals(shopTypes)){
                System.out.println("Shop Request: All shop types exhausted. Exiting with size " + set.size() + ".");
                break;
            }
        }
        return set;
    }

    private StItem inventoryFetcher(StItemType type, StRarity rarity, Set<StItem> existingItems){
        System.out.println("Shop Request: Fetching new item of rarity: " + rarity + " type: " + type);
        for(StItem item : itemsByType.get(type)){
            if(item.getRarity() == rarity && !existingItems.contains(item)){
                return item;
            }
        }
        for(StItem item : itemsByType.get(type)){
            if(item.getRarity() == rarity && !existingItems.contains(item)){
                return item;
            }
        }
        return null;
    }
}
