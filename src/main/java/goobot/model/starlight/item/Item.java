/*
* Java source file for CeresBot.
* @Author Lukas Adkins
*/

package goobot.model.starlight.item;

import goobot.Constants;
import goobot.Constants.ItemType;
import goobot.Constants.Rarity;

import java.util.Objects;
import java.util.Random;

public class Item {
    private ItemType type;
    private String name;
    private Rarity rarity;
    private String description;
    private String weight;
    private Integer price;
    private Random rng;

    private static final String SALE_COLOR = "\u001b[0;32m%s\u001b[0;0m"; // Green
    private static final String MARKUP_COLOR = "\u001b[0;31m%s\u001b[0;0m"; // Red

    public Item(ItemType type, String name, Rarity rarity, String description, String weight, Integer price) {
        this.type = type;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.rng = new Random();
    }

    public ItemType getType() {
        return this.type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Random getRng() {
        return this.rng;
    }

    public void setRng(Random rng) {
        this.rng = rng;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item)) {
            return false;
        }
        Item dhItem = (Item) o;
        return Objects.equals(type, dhItem.type) && Objects.equals(name, dhItem.name) && Objects.equals(rarity, dhItem.rarity) && Objects.equals(description, dhItem.description) && Objects.equals(weight, dhItem.weight) && Objects.equals(price, dhItem.price) && Objects.equals(rng, dhItem.rng);
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s / %d CYD\n%s```", name, getFormattedRarity(), weight, price, description);
    }

    /**
     * Returns string to display item in a shop with coloring if the item is on sale.
     * e.g. "Rifle / Common / 120 CYD"
     * @return Shop string
     */
    public String displayShop(){
        if(rng.nextInt(100) + 1 > 85){ // Small chance item is on sale or more expensive
            double percent = 1.00;
            while(percent < 1.07 && percent > 0.95) // Generate percent sale / markup with greater than 7% swing
                percent = (rng.nextInt(120 - 65 + 1) + 65) / 100.0;
            int newPrice = (int) (price * percent); // New price with markup/discount
            int percentOff = (int) (Math.abs(1.00 - percent) * 100); // Percent added/substracted from original price

            if(newPrice > price){
                String formatMarkupPrice = String.format("%d CYD (+%d%%)", newPrice, percentOff) ;
                return String.format("%s / %s / %s\n", name, getFormattedRarity(), String.format(MARKUP_COLOR, formatMarkupPrice));
            }
            String formatDiscountPrice = String.format("%d CYD (-%d%%)", newPrice, percentOff) ;
            return String.format("%s / %s / %s\n", name, getFormattedRarity(), String.format(SALE_COLOR, formatDiscountPrice));

        }
        return String.format("%s / %s / %d CYD\n", name, getFormattedRarity(), price);
    }

    public String displayLoot(){
        return String.format("%s / %s / %s CYD\n", name, getFormattedRarity(), getPrice());
    }
    
    /**
     * Gets properly formatted and colored ANSI string for rarity type.
     * @return Properly formatted and colored ANSI string
     */
    public String getFormattedRarity(){
        switch (rarity) {
            case ABUNDANT:
                return String.format(Constants.RARITY_COLORS.get(Rarity.ABUNDANT), rarity);
            case PLENTIFUL:
                return String.format(Constants.RARITY_COLORS.get(Rarity.PLENTIFUL), rarity);
            case COMMON:
                return String.format(Constants.RARITY_COLORS.get(Rarity.COMMON), rarity);
            case AVERAGE:
                return String.format(Constants.RARITY_COLORS.get(Rarity.AVERAGE), rarity);
            case SCARCE:
                return String.format(Constants.RARITY_COLORS.get(Rarity.SCARCE), rarity);
            case RARE:
                return String.format(Constants.RARITY_COLORS.get(Rarity.RARE), rarity);
            case VERY_RARE:
                return String.format(Constants.RARITY_COLORS.get(Rarity.VERY_RARE), rarity);
            case EXTREMELY_RARE:
                return String.format(Constants.RARITY_COLORS.get(Rarity.EXTREMELY_RARE), rarity);
            case NEAR_UNIQUE:
                return String.format(Constants.RARITY_COLORS.get(Rarity.NEAR_UNIQUE), rarity);
            default:
                return rarity.toString();
        }
    }
}
