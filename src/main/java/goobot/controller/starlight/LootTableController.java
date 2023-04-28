package goobot.controller.starlight;
import java.util.HashMap;
import goobot.model.starlight.StItem;
import java.util.ArrayList;
import goobot.Constants.StRarity;

public class LootTableController {
    private HashMap<String, StItem> itemTable;
    private HashMap<StRarity, ArrayList<StItem>> rarityTable;
    

    public LootTableController(HashMap<String, StItem> itemMap){
        this.itemTable = itemMap;
        ArrayList<StItem> itemList = new ArrayList<StItem>(itemMap.values());
        this.rarityTable = initItemMap(itemList);
    }

    private HashMap<StRarity, ArrayList<StItem>> initItemMap(ArrayList<StItem> list){
        HashMap<StRarity, ArrayList<StItem>> map = new HashMap<>();
        for(StItem item : list){ // Add items to list
            StRarity rarity = item.getRarity();
            if(map.get(rarity) == null){
                map.put(rarity, new ArrayList<StItem>());
            }
            map.get(rarity).add(item);
        }
        return map;
    }

    /**
     * Returns a number of given dhItems with a given rarity
     * @return Arraylist of requested items
     */
    public ArrayList<StItem> getRandomItems(Integer scarce, Integer rare, Integer veryRare, Integer extremelyRare){
        ArrayList<StItem> list = new ArrayList<>();
        for(int i = 0; i < scarce; i++){
            int index = (int)(Math.random() * rarityTable.get(StRarity.SCARCE).size());
            StItem item = rarityTable.get(StRarity.SCARCE).get(index);
            if(list.contains(item))
                scarce++;
            else
                list.add(item);
        }
        for(int i = 0; i < rare; i++){
            int index = (int)(Math.random() * rarityTable.get(StRarity.RARE).size());
            StItem item = rarityTable.get(StRarity.RARE).get(index);
            if(list.contains(item))
                rare++;
            else
                list.add(item);
        }
        for(int i = 0; i < veryRare; i++){
            int index = (int)(Math.random() * rarityTable.get(StRarity.VERY_RARE).size());
            StItem item = rarityTable.get(StRarity.VERY_RARE).get(index);
            if(list.contains(item))
                veryRare++;
            else
                list.add(item);
        }
        for(int i = 0; i < extremelyRare; i++){
            int index = (int)(Math.random() * rarityTable.get(StRarity.EXTREMELY_RARE).size());
            StItem item = rarityTable.get(StRarity.EXTREMELY_RARE).get(index);
            if(list.contains(item))
                extremelyRare++;
            else
                list.add(item);
        }
        return list;
    }

    public StItem getItem(String name){
        return this.itemTable.get(name);
    }
}
