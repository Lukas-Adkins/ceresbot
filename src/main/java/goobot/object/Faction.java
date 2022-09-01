package goobot.object;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Faction {
    private String name;
    private Integer gp;
    private Mission activeMission;
    private List<Room> currentRooms;
    private List<Mission> currentMissions;
    private List<Character> currentCharacters;
    private List<Upgrade> availableUpgrades;
    
    public Faction(String name, Integer gp){
        this.name = name;
        this.gp = gp;
        this.activeMission = null;
        this.currentRooms = new ArrayList<>();
        this.currentMissions = new ArrayList<>();
        this.currentCharacters = new ArrayList<>();
        this.availableUpgrades = new ArrayList<>();
    }

    public Mission getActiveMission() {
        return activeMission;
    }

    public List<Upgrade> getAvailableUpgrades() {
        return availableUpgrades;
    }

    public List<Character> getCurrentCharacters() {
        return currentCharacters;
    }

    public List<Mission> getCurrentMissions() {
        return currentMissions;
    }

    public List<Room> getCurrentRooms() {
        return currentRooms;
    }

    public Integer getGp() {
        return gp;
    }

    public String getName() {
        return name;
    }

    public void setActiveMission(Mission activeMission) {
        this.activeMission = activeMission;
    }

    public void setAvailableUpgrades(List<Upgrade> availableUpgrades) {
        this.availableUpgrades = availableUpgrades;
    }

    public void setCurrentCharacters(List<Character> currentCharacters) {
        this.currentCharacters = currentCharacters;
    }

    public void setCurrentMissions(List<Mission> currentMissions) {
        this.currentMissions = currentMissions;
    }

    public void addCurrentRoom(Room room) {
        this.currentRooms.add(room);
    }

    public void addAvailableUpgrade(Upgrade upgrade) {
        this.availableUpgrades.add(upgrade);
    }

    public void addCurrentCharacter(Character character) {
        this.currentCharacters.add(character);
    }

    public void addCurrentMission(Mission mission) {
        this.currentMissions.add(mission);
    }

    public void setCurrentRooms(List<Room> currentRooms) {
        this.currentRooms = currentRooms;
    }

    public void setGp(Integer gp) {
        this.gp = gp;
    }

    public void setName(String name) {
        this.name = name;
    }
}
