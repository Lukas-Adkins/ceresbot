package goobot.object;

enum CharacterStatus {
    READY,
    UNTRAINED,
    UNAVAILABLE;
}

public class Character {
    private Integer lvl;
    private String name;
    private CharacterStatus status;
    private String statblockURL;
    private String imageURL;

    public Character(String name, String statblockURL, String imageURL, Integer lvl, CharacterStatus status) {
        this.name = name;
        this.statblockURL = statblockURL;
        this.imageURL = imageURL;
        this.lvl = lvl;
        this.status = status;
    }

    public Integer getLvl() {
        return lvl;
    }

    public String getName() {
        return name;
    }

    public CharacterStatus getStatus() {
        return status;
    }

    public String getStatblockURL() {
        return statblockURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatblockURL(String statblockURL) {
        this.statblockURL = statblockURL;
    }

    public void setStatus(CharacterStatus status) {
        this.status = status;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
