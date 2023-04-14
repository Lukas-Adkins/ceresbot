/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */


package goobot.model;
import java.util.Objects;

import goobot.Constants.DhRarity;
import goobot.Constants.DhItemType;

public class DhCybernetic extends DhItem {
    private Integer slots;

    public DhCybernetic(DhItemType type, String name, String rarity, String description, String weight, Integer price, Integer slots) {
        super(type, name, rarity, description, weight, price);
        this.slots = slots;
    }

    public Integer getSlots() {
        return this.slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }

    public DhCybernetic slots(Integer slots) {
        setSlots(slots);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DhCybernetic)) {
            return false;
        }
        DhCybernetic dhCybernetic = (DhCybernetic) o;
        return Objects.equals(slots, dhCybernetic.slots);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(slots);
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %d cyber-slots / %d credits\n - %s```", getName(), getFormattedRarity(), slots, getPrice(), getDescription());
    }
}
