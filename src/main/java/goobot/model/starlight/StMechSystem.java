package goobot.model.starlight;
import goobot.Constants.StItemType;
import goobot.Constants.StRarity;
import java.util.Objects;

public class StMechSystem extends StItem {
    private String mechSlot;
    private String mechLocation;

    public StMechSystem(StItemType type, String name, StRarity rarity, String description, String weight, Integer price, String mechSlot, String mechLocation){
        super(type, name, rarity, description, weight, price);
        this.mechSlot = mechSlot;
        this.mechLocation = mechLocation;
    }

    public String getMechSlot() {
        return this.mechSlot;
    }

    public void setMechSlot(String mechSlot) {
        this.mechSlot = mechSlot;
    }

    public String getMechLocation() {
        return this.mechLocation;
    }

    public void setMechLocation(String mechLocation) {
        this.mechLocation = mechLocation;
    }

    public StMechSystem mechSlot(String mechSlot) {
        setMechSlot(mechSlot);
        return this;
    }

    public StMechSystem mechLocation(String mechLocation) {
        setMechLocation(mechLocation);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mechSlot, mechLocation);
    }

    @Override
    public String toString() {
        return String.format("```ansi\n%s / %s / %d credits\n%s / Locations: [%s]\n%s```",
        getName(), getFormattedRarity(), getPrice(), mechSlot, mechLocation, getDescription());
    }
}
