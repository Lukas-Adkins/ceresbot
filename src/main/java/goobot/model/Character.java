/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.model;

import java.util.Objects;

public class Character {
    private String name;
    private String country;
    private String title;
    private String gender;
    private String species;
    private String age;
    private String height;
    private String hairColor;
    private String eyeColor;
    private String religion;
    private String status;
    private String description;
    private String image;

    public Character() {}

    public Character(String name, String country, String title, String gender, String species, String age, String height, String hairColor, String eyeColor, String religion, String status, String description, String image) {
        this.name = name;
        this.country = country;
        this.title = title;
        this.gender = gender;
        this.species = species;
        this.age = age;
        this.height = height;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.religion = religion;
        this.status = status;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHairColor() {
        return this.hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return this.eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getReligion() {
        return this.religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Character)) {
            return false;
        }
        Character character = (Character) o;
        return Objects.equals(name, character.name) && Objects.equals(country, character.country) && Objects.equals(title, character.title) && Objects.equals(gender, character.gender) && Objects.equals(species, character.species) && Objects.equals(age, character.age) && Objects.equals(height, character.height) && Objects.equals(hairColor, character.hairColor) && Objects.equals(eyeColor, character.eyeColor) && Objects.equals(religion, character.religion) && Objects.equals(status, character.status) && Objects.equals(description, character.description) && Objects.equals(image, character.image);
    }

    @Override
    public String toString() {
        String str = 
        "**" + name + "**\n" + 
        height + ", " + gender.toLowerCase() + ", " +
        species.toLowerCase() + ", " + age.toLowerCase() + " years old, " + status.toLowerCase() + "\n" + 
        eyeColor + " eyes, " + hairColor.toLowerCase() + " hair\n";

        if(!title.isEmpty())
            str = str + "Title(s): " + title + "\n";
        if(!religion.isEmpty())
            str = str + "Religion: " + religion + "\n";

        str = str + "\n" + description;
        return str;
    }
}
