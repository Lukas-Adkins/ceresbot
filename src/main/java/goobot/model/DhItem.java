/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.model;

import goobot.Constants;
import goobot.Constants.DhItemType;
import goobot.Constants.DhRarity;
import java.util.Objects;

public class DhItem {
    private DhItemType type;
    private String name;
    private String rarity;
    private String description;
    private String weight;
    private Integer price;

    public DhItem() {
    }

    public DhItem(DhItemType type, String name, String rarity, String description, String weight, Integer price) {
        this.type = type;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.weight = weight;
        this.price = price;
    }

    public DhItemType getType() {
        return this.type;
    }

    public void setType(DhItemType type) {
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

    public DhItem type(DhItemType type) {
        setType(type);
        return this;
    }

    public DhItem name(String name) {
        setName(name);
        return this;
    }

    public DhItem rarity(String rarity) {
        setRarity(rarity);
        return this;
    }

    public DhItem description(String description) {
        setDescription(description);
        return this;
    }

    public DhItem weight(String weight) {
        setWeight(weight);
        return this;
    }

    public DhItem price(Integer price) {
        setPrice(price);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DhItem)) {
            return false;
        }
        DhItem dhItem = (DhItem) o;
        return Objects.equals(type, dhItem.type) && Objects.equals(name, dhItem.name) && Objects.equals(rarity, dhItem.rarity) && Objects.equals(description, dhItem.description) && Objects.equals(weight, dhItem.weight) && Objects.equals(price, dhItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, rarity, description, weight, price);
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s / %d credits\n%s```", name, getFormattedRarity(), weight, price, description);
    }

    public String getShopString(){
        return String.format("%s / %s / %d credits\n", name, getFormattedRarity(), price);
    }

    /**
     * Gets properly formatted and colored ANSI string for rarity type.
     * @return Properly formatted and colored ANSI string
     */
    public String getFormattedRarity(){
        switch (rarity.toLowerCase()) {
            case "abundant":
                return String.format(Constants.RARITY_COLORS.get(DhRarity.ABUNDANT), rarity);
            case "plentiful":
                return String.format(Constants.RARITY_COLORS.get(DhRarity.PLENTIFUL), rarity);
            case "common":
                return String.format(Constants.RARITY_COLORS.get(DhRarity.COMMON), rarity);
            case "average":
                return String.format(Constants.RARITY_COLORS.get(DhRarity.AVERAGE), rarity);
            case "uncommon":
                return String.format(Constants.RARITY_COLORS.get(DhRarity.UNCOMMON), rarity);
            case "scarce":
                return String.format(Constants.RARITY_COLORS.get(DhRarity.SCARCE), rarity);
            case "rare":
                return String.format(Constants.RARITY_COLORS.get(DhRarity.RARE), rarity);
            case "very rare":
                return String.format(Constants.RARITY_COLORS.get(DhRarity.VERY_RARE), rarity);
            case "extremely rare":
                return String.format(Constants.RARITY_COLORS.get(DhRarity.EXTREMELY_RARE), rarity);
            case "near unique":
                return String.format(Constants.RARITY_COLORS.get(DhRarity.NEAR_UNIQUE), rarity);
            default:
                return rarity;
        }
    }

}
