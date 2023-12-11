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

/**
 * Represents a generic item.
 */
public class Item {
    private static final String SALE_COLOR = "\u001b[0;32m%s\u001b[0;0m"; // Green
    private static final String MARKUP_COLOR = "\u001b[0;31m%s\u001b[0;0m"; // Red
    private static final String ANSI_FORMAT = "```ansi\n%s / %s / %s / %d CYD\n%s```";
    
    private ItemType type;
    private String name;
    private Rarity rarity;
    private String description;
    private String weight;
    private Integer price;
    private Random rng;

    /**
     * Represents an item with various attributes such as type, name, rarity, description, weight, and price.
     *
     * @param type        The type of the item (e.g., weapon, armor, potion).
     * @param name        The name of the item.
     * @param rarity      The rarity of the item.
     * @param description A brief description of the item.
     * @param weight      The weight of the item.
     * @param price       The price of the item.
     */
    public Item(ItemType type, String name, Rarity rarity, String description, String weight, Integer price) {
        this.type = type;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.rng = new Random();
    }

    /**
     * Retrieves the type of the item.
     *
     * @return The type of the item.
     */
    public ItemType getType() {
        return this.type;
    }

    /**
     * Sets the type of the item.
     *
     * @param type The new type to set for the item.
     */
    public void setType(ItemType type) {
        this.type = type;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the item.
     *
     * @param name The new name to set for the item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the rarity of the item.
     *
     * @return The rarity of the item.
     */
    public Rarity getRarity() {
        return this.rarity;
    }

    /**
     * Sets the rarity of the item.
     *
     * @param rarity The new rarity to set for the item.
     */
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    /**
     * Retrieves a brief description of the item.
     *
     * @return The description of the item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the item.
     *
     * @param description The new description to set for the item.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the weight of the item.
     *
     * @return The weight of the item.
     */
    public String getWeight() {
        return this.weight;
    }

    /**
     * Sets the weight of the item.
     *
     * @param weight The new weight to set for the item.
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * Retrieves the price of the item.
     *
     * @return The price of the item.
     */
    public Integer getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the item.
     *
     * @param price The new price to set for the item.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Retrieves the random number generator associated with the item.
     *
     * @return The random number generator.
     */
    public Random getRng() {
        return this.rng;
    }

    /**
     * Sets the random number generator for the item.
     *
     * @param rng The new random number generator to set for the item.
     */
    public void setRng(Random rng) {
        this.rng = rng;
    }

    /**
     * Indicates whether some other object is "equal to" this item.
     * Two items are considered equal if they have the same type, name, rarity, description, weight, price, and random number generator.
     *
     * @param o The object to compare with this item.
     * @return {@code true} if this item is equal to the specified object; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item otherItem = (Item) o;
        return Objects.equals(type, otherItem.type) &&
            Objects.equals(name, otherItem.name) &&
            Objects.equals(rarity, otherItem.rarity) &&
            Objects.equals(description, otherItem.description) &&
            Objects.equals(weight, otherItem.weight) &&
            Objects.equals(price, otherItem.price) &&
            Objects.equals(rng, otherItem.rng);
    }

    /**
     * Returns a string representation of the item, including its name, formatted rarity, weight, price, and description (if available).
     *
     * @return A string representation of the item.
     */
    @Override
    public String toString() {
        return String.format(ANSI_FORMAT, name, getFormattedRarity(), weight, price, getDescription() != null ? getDescription() : "");
    }

    /**
     * Generates a formatted display string representing an item in a shop, including its name, rarity,
     * and either the original price or a modified price with a potential discount or markup.
     *
     * If the random number generated is above a predefined sale threshold (85%), the function
     * calculates a random percentage change within the range of a discount (5%) or markup (7%).
     * It then adjusts the item's price accordingly and formats the display string with the calculated values.
     * The display string includes the item's name, rarity, and the formatted price with the percentage change
     * and appropriate color code based on whether it's a discount or markup.
     *
     * If the random number is below the sale threshold, the function returns a string containing
     * the item's name, rarity, and the original price without any modification.
     *
     * @return A formatted display string representing the item in the shop.
     */
    public String displayInShop() {
        final int SALE_THRESHOLD = 85;
        final double MIN_PERCENT = 0.95;
        final double MAX_PERCENT = 1.07;
    
        if (rng.nextInt(100) + 1 > SALE_THRESHOLD) {
            double percent = rng.nextDouble() * (MAX_PERCENT - MIN_PERCENT) + MIN_PERCENT;
            int newPrice = (int) (price * percent);
            int percentChange = (int) Math.abs((1.00 - percent) * 100);
    
            String priceFormat = (newPrice > price) ? "%d CYD (+%d%%)" : "%d CYD (-%d%%)";
            String priceColorFormat = (newPrice > price) ? MARKUP_COLOR : SALE_COLOR;
    
            String formattedPrice = String.format(priceFormat, newPrice, percentChange);
            return String.format("%s / %s / %s\n", name, getFormattedRarity(), String.format(priceColorFormat, formattedPrice));
        }
    
        return String.format("%s / %s / %d CYD\n", name, getFormattedRarity(), price);
    }

    /**
     * Generates a formatted display string representing an item obtained as loot, including its name,
     * rarity, and price in Currency (CYD).
     *
     * @return A formatted display string representing the loot item.
     */
    public String displayInLootTable() {
        return String.format("%s / %s / %s CYD\n", name, getFormattedRarity(), getPrice());
    }

    /**
     * Returns a formatted string representation of the rarity, using color codes
     * defined in the Constants class.
     *
     * @return A formatted string representing the rarity with color codes,
     *         or the string representation of the rarity if no formatting is available.
     */
    public String getFormattedRarity() {
        String rarityColor = Constants.RARITY_COLORS.get(rarity);
        
        if (rarityColor != null) {
            return String.format(rarityColor, rarity);
        } else {
            return rarity.toString();
        }
    }
}
