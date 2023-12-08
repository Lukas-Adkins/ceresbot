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

    /**
     * Constructs an ItemTable based on a specified TableType.
     *
     * @param table The TableType defining the item types and their associated weights.
     */
    public ItemTable(TableType table) {
        this.itemTypes = table.itemTypes;
        this.weightedItemTypes = new WeightedRandomBag<>();
        this.weightedItemTypes.addEntries(itemTypes, table.itemWeights);
    }
    
    /**
     * Retrieves a random item of a specified rarity from a weighted type.
     * The method iterates through rarity levels until an item is found or rarity becomes null.
     *
     * @param rarity The rarity of the item to be retrieved.
     * @return A randomly selected item with the specified rarity, or null if not found.
     */
    public Item getItem(Rarity rarity) {
        ItemType type = weightedItemTypes.getRandom();
        ArrayList<Item> itemList = ItemService.getItemByType(type);

        while (rarity != null) {
            Item item = getItemOfRarity(rarity, itemList);

            if (item != null) {
                return item;
            }

            rarity = rarity.prev();
        }

        return null;
    }

    /**
     * Retrieves a random item of a specified rarity from a shuffled list.
     *
     * This method shuffles the provided list of items and searches for an item with the specified rarity.
     * The list is shuffled to introduce randomness, and the first item found with the desired rarity is returned.
     * If no item with the specified rarity is found, null is returned.
     *
     * @param rarity     The rarity of the item to be retrieved.
     * @param itemList   The list of items from which to retrieve an item of the specified rarity.
     * @return           A randomly selected item with the specified rarity, or null if not found.
     */
    private Item getItemOfRarity(Rarity rarity, ArrayList<Item> itemList) {
        Collections.shuffle(itemList);

        for (Item item : itemList) {
            if (item.getRarity() == rarity) {
                return item;
            }
        }

        return null;
    }

    /**
     * Retrieves a sorted list of unique items based on the specified rarity counts in the given TableRequest.
     *
     * This method generates a list of items by requesting items of various rarities according to the counts
     * specified in the TableRequest. The resulting list is sorted by rarity in ascending order, and duplicate items
     * are eliminated to ensure uniqueness.
     *
     * @param request The TableRequest specifying the counts of items for each rarity.
     * @return A sorted list of unique items based on the specified rarity counts.
     */
    public ArrayList<Item> getItems(TableRequest request) {
        // Use a set to ensure uniqueness of items
        Set<Item> set = new HashSet<>();

        // Request items of each rarity and add them to the set
        set = getItemsOfRarity(Rarity.UBIQUITOUS, request.ubiquitous, set);
        set = getItemsOfRarity(Rarity.ABUNDANT, request.abundant, set);
        set = getItemsOfRarity(Rarity.PLENTIFUL, request.plentiful, set);
        set = getItemsOfRarity(Rarity.COMMON, request.common, set);
        set = getItemsOfRarity(Rarity.AVERAGE, request.average, set);
        set = getItemsOfRarity(Rarity.SCARCE, request.scarce, set);
        set = getItemsOfRarity(Rarity.RARE, request.rare, set);
        set = getItemsOfRarity(Rarity.VERY_RARE, request.veryRare, set);
        set = getItemsOfRarity(Rarity.EXTREMELY_RARE, request.extremelyRare, set);
        set = getItemsOfRarity(Rarity.NEAR_UNIQUE, request.nearUnique, set);

        // Convert the set to an ArrayList and sort it by rarity
        ArrayList<Item> list = new ArrayList<>(set);
        list.sort(Comparator.comparing(Item::getRarity));
        return list;
    }

    /**
     * Retrieves a set of items with a specified rarity and target size for the inventory.
     *
     * This method populates the given set of existing items with new items of the specified rarity until
     * the target size is reached. It utilizes the inventoryFetcher method to obtain items of the correct
     * rarity and type. The process continues until the target size is achieved, or all available types
     * are exhausted.
     *
     * @param rarity         The Rarity of the items to be fetched.
     * @param size           The target size of the inventory.
     * @param existingItems  The current set of existing items in the inventory.
     * @return               The set of items with the specified rarity and target size.
     */
    private Set<Item> getItemsOfRarity(Rarity rarity, int size, Set<Item> existingItems) {
        if (size <= 0) {
            return existingItems;
        }

        List<ItemType> exhaustedTypes = new ArrayList<>();

        while (existingItems.size() < size) {
            Item item = null;
            Rarity currentRarity = rarity;
            ItemType currentType = weightedItemTypes.getRandom();

            while (item == null) {
                item = inventoryFetcher(currentType, currentRarity, existingItems);
                if (item != null) {
                    existingItems.add(item);
                } else {
                    // Downgrade rarity of item if none found at initial rarity
                    currentRarity = currentRarity.prev();

                    // Break if no more valid items
                    if (currentRarity == null) {
                        exhaustedTypes.add(currentType);
                        break;
                    }
                }
            }

            if (existingItems.size() == size) {
                break;
            }

            if (exhaustedTypes.containsAll(itemTypes)) {
                break;
            }
        }

        return existingItems;
    }

    /**
     * Retrieves a new item of a specified type and rarity for the inventory.
     *
     * This method searches for an item of the specified type and rarity from the available items
     * provided by the ItemService. It ensures that the item is not already present in the existingItems set
     * to avoid duplicates in the inventory.
     *
     * @param type           The ItemType of the item to be fetched.
     * @param rarity         The Rarity of the item to be fetched.
     * @param existingItems  A Set of existing items in the inventory.
     * @return               The fetched item, or null if no suitable item is found.
     */
    private Item inventoryFetcher(ItemType type, Rarity rarity, Set<Item> existingItems) {
        System.out.println("Shop Request: Fetching new item of rarity: " + rarity + " type: " + type);

        for (Item item : ItemService.getItemByType(type)) {
            if (item.getRarity() == rarity && !existingItems.contains(item)) {
                return item;
            }
        }

        return null;
    }

}
