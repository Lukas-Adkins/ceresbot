/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */


package goobot.model;
import java.util.Objects;
import goobot.Constants.StRarity;

import goobot.Constants.StItemType;

public class StCybernetic extends StItem {
    private String slots;

    public StCybernetic(StItemType type, String name, StRarity rarity, String description, String weight, Integer price, String slots) {
        super(type, name, rarity, description, weight, price);
        this.slots = slots;
    }

    public String getSlots() {
        return this.slots;
    }

    public void setSlots(String slots) {
        this.slots = slots;
    }

    public StCybernetic slots(String slots) {
        setSlots(slots);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StCybernetic)) {
            return false;
        }
        StCybernetic dhCybernetic = (StCybernetic) o;
        return Objects.equals(slots, dhCybernetic.slots);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(slots);
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %s cyber-slots / %d credits\n - %s```", getName(), getFormattedRarity(), slots, getPrice(), getDescription());
    }
}
