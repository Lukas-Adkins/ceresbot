package goobot.object;

enum CharacterStatus {
    READY,
    UNTRAINED,
    UNAVAILABLE;
}

enum CharacterStatblock {
    PALADIN,
    CLERIC,
    FIREPRIEST,
    ACOLYTE,
    COMMONER,
    CUSTOM;
}

public class Character {
    private Integer lvl;
    private String name;
    private CharacterStatus status;
    private CharacterStatblock statblock;

    public Character(String name, CharacterStatblock statblock, Integer lvl) {
        this.name = name;
        this.statblock = statblock;
        this.lvl = lvl;
        this.status = CharacterStatus.READY;
    }
}


