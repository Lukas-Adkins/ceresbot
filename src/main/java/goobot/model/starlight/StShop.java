package goobot.model.starlight;

import goobot.Constants.StRarity;
import java.util.Set;
import goobot.model.WeightedRandomBag;
import net.dv8tion.jda.api.requests.Request;
import goobot.Constants.StItemType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class StShop {
    HashMap<StItemType, ArrayList<StItem>> itemsByType;
    WeightedRandomBag<StItemType> randomTypePicker;
    Set<StItemType> shopTypes;

    public StShop(HashMap<StItemType, ArrayList<StItem>> itemsByType){
        this.itemsByType = itemsByType;
        this.randomTypePicker = new WeightedRandomBag<>();
        this.shopTypes = new HashSet<>();
    }
    
    public StItem getItem(StRarity rarity) {
        StItemType type = randomTypePicker.getRandom();
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
    
    public ArrayList<StItem> getInventory(int numUbiquitous, int numAbundant, int numPlentiful, int numCommon, int numAverage,
    int numScarce, int numRare, int numVeryRare, int numExtremelyRare, int numNearUnique){
        System.out.println(String.format("Shop Request Rarities:\n ub: %d\n ab: %s\n pl: %s\n co: %s\n av: %s\n sc: %s\n ra: %s\n vr: %s\n er: %s\n nu: %s\n",
        numUbiquitous, numAbundant, numPlentiful, numCommon, numAverage, numScarce, numRare, numVeryRare, numExtremelyRare, numNearUnique));
        Set<StItem> set = new HashSet<>();
        set = getInventoryOfRarity(StRarity.UBIQUITOUS, numUbiquitous, set);
        set = getInventoryOfRarity(StRarity.ABUNDANT, numAbundant, set);
        set = getInventoryOfRarity(StRarity.PLENTIFUL, numPlentiful, set);
        set = getInventoryOfRarity(StRarity.COMMON, numCommon, set);
        set = getInventoryOfRarity(StRarity.AVERAGE, numAverage, set);
        set = getInventoryOfRarity(StRarity.SCARCE, numScarce, set);
        set = getInventoryOfRarity(StRarity.RARE, numRare, set);
        set = getInventoryOfRarity(StRarity.VERY_RARE, numVeryRare, set);
        set = getInventoryOfRarity(StRarity.EXTREMELY_RARE, numExtremelyRare, set);
        set = getInventoryOfRarity(StRarity.NEAR_UNIQUE, numNearUnique, set);
        ArrayList<StItem> list = new ArrayList<>(set);
        list.sort(Comparator.comparing(StItem::getRarity));
        return list;
    }

    private Set<StItem> getInventoryOfRarity(StRarity rarity, int size, Set<StItem> existingItems){
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
            StItemType currentType = randomTypePicker.getRandom();
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
