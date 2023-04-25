/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.model;

import goobot.Constants;
import goobot.Constants.StarlightItemType;
import goobot.Constants.StarlightRarity;
import java.util.Objects;
import java.util.Random;

public class StarlightItem {
    private StarlightItemType type;
    private String name;
    private String rarity;
    private String description;
    private String weight;
    private Integer price;
    private Random rng;

    private static final String SALE_COLOR = "\u001b[0;32m%s\u001b[0;0m"; // Green
    private static final String MARKUP_COLOR = "\u001b[0;31m%s\u001b[0;0m"; // Red

    public StarlightItem(StarlightItemType type, String name, String rarity, String description, String weight, Integer price) {
        this.type = type;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.rng = new Random();
    }

    public StarlightItemType getType() {
        return this.type;
    }

    public void setType(StarlightItemType type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return this.rarity;
    }

    public void setRarity(String rarity) {
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

    public StarlightItem type(StarlightItemType type) {
        setType(type);
        return this;
    }

    public StarlightItem name(String name) {
        setName(name);
        return this;
    }

    public StarlightItem rarity(String rarity) {
        setRarity(rarity);
        return this;
    }

    public StarlightItem description(String description) {
        setDescription(description);
        return this;
    }

    public StarlightItem weight(String weight) {
        setWeight(weight);
        return this;
    }

    public StarlightItem price(Integer price) {
        setPrice(price);
        return this;
    }

    public StarlightItem rng(Random rng) {
        setRng(rng);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StarlightItem)) {
            return false;
        }
        StarlightItem dhItem = (StarlightItem) o;
        return Objects.equals(type, dhItem.type) && Objects.equals(name, dhItem.name) && Objects.equals(rarity, dhItem.rarity) && Objects.equals(description, dhItem.description) && Objects.equals(weight, dhItem.weight) && Objects.equals(price, dhItem.price) && Objects.equals(rng, dhItem.rng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, rarity, description, weight, price, rng);
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s / %d credits\n%s```", name, getFormattedRarity(), weight, price, description);
    }

    public String getShopString(){
        if(rng.nextInt(100) + 1 > 85){ // Small chance item is on sale or more expensive
            double percent = 1.00;
            while(percent < 1.07 && percent > 0.95) // Generate percent sale / markup with greater than 7% swing
                percent = (rng.nextInt(120 - 65 + 1) + 65) / 100.0;
            int newPrice = (int) (price * percent); // New price with markup/discount
            int percentOff = (int) (Math.abs(1.00 - percent) * 100); // Percent added/substracted from original price

            if(newPrice > price){
                String formatMarkupPrice = String.format("%d credits (+%d%%)", newPrice, percentOff) ;
                return String.format("%s / %s / %s\n", name, getFormattedRarity(), String.format(MARKUP_COLOR, formatMarkupPrice));
            }
            String formatDiscountPrice = String.format("%d credits (-%d%%)", newPrice, percentOff) ;
            return String.format("%s / %s / %s\n", name, getFormattedRarity(), String.format(SALE_COLOR, formatDiscountPrice));

        }
        return String.format("%s / %s / %d credits\n", name, getFormattedRarity(), price);
    }
    /**
     * Gets properly formatted and colored ANSI string for rarity type.
     * @return Properly formatted and colored ANSI string
     */
    public String getFormattedRarity(){
        switch (rarity.toLowerCase()) {
            case "abundant":
                return String.format(Constants.RARITY_COLORS.get(StarlightRarity.ABUNDANT), rarity);
            case "plentiful":
                return String.format(Constants.RARITY_COLORS.get(StarlightRarity.PLENTIFUL), rarity);
            case "common":
                return String.format(Constants.RARITY_COLORS.get(StarlightRarity.COMMON), rarity);
            case "average":
                return String.format(Constants.RARITY_COLORS.get(StarlightRarity.AVERAGE), rarity);
            case "uncommon":
                return String.format(Constants.RARITY_COLORS.get(StarlightRarity.UNCOMMON), rarity);
            case "scarce":
                return String.format(Constants.RARITY_COLORS.get(StarlightRarity.SCARCE), rarity);
            case "rare":
                return String.format(Constants.RARITY_COLORS.get(StarlightRarity.RARE), rarity);
            case "very rare":
                return String.format(Constants.RARITY_COLORS.get(StarlightRarity.VERY_RARE), rarity);
            case "extremely rare":
                return String.format(Constants.RARITY_COLORS.get(StarlightRarity.EXTREMELY_RARE), rarity);
            case "near unique":
                return String.format(Constants.RARITY_COLORS.get(StarlightRarity.NEAR_UNIQUE), rarity);
            default:
                return rarity;
        }
    }
}
