/*
 * Java source file for CeresBot.
 * @Author Lukas Adkins
 */

package goobot.model;

import java.util.Objects;

/**
 * Represents a character.
 */
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

    /**
     * Constructs a new Character object with the specified attributes.
     *
     * @param name        The name of the character.
     * @param country     The country of origin or affiliation of the character.
     * @param title       The title(s) associated with the character.
     * @param gender      The gender of the character.
     * @param species     The species or race of the character.
     * @param age         The age of the character.
     * @param height      The height of the character.
     * @param hairColor   The hair color of the character.
     * @param eyeColor    The eye color of the character.
     * @param religion    The religion or belief system of the character.
     * @param status      The current status or condition of the character.
     * @param description A description providing additional details about the character.
     * @param image       The image associated with the character.
     */
    public Character(String name, String country, String title, String gender, String species, String age,
    String height, String hairColor, String eyeColor, String religion, String status,
    String description, String image) {
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

    /**
     * Gets the name of the character.
     *
     * @return The name of the character.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the character.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the country of origin or affiliation of the character.
     *
     * @return The country of the character.
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the country of origin or affiliation of the character.
     *
     * @param country The new country to set.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the title(s) associated with the character.
     *
     * @return The title(s) of the character.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title(s) associated with the character.
     *
     * @param title The new title(s) to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the gender of the character.
     *
     * @return The gender of the character.
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Sets the gender of the character.
     *
     * @param gender The new gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the species or race of the character.
     *
     * @return The species of the character.
     */
    public String getSpecies() {
        return this.species;
    }

    /**
     * Sets the species or race of the character.
     *
     * @param species The new species to set.
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * Gets the age of the character.
     *
     * @return The age of the character.
     */
    public String getAge() {
        return this.age;
    }

    /**
     * Sets the age of the character.
     *
     * @param age The new age to set.
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Gets the height of the character.
     *
     * @return The height of the character.
     */
    public String getHeight() {
        return this.height;
    }

    /**
     * Sets the height of the character.
     *
     * @param height The new height to set.
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * Gets the hair color of the character.
     *
     * @return The hair color of the character.
     */
    public String getHairColor() {
        return this.hairColor;
    }

    /**
     * Sets the hair color of the character.
     *
     * @param hairColor The new hair color to set.
     */
    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Gets the eye color of the character.
     *
     * @return The eye color of the character.
     */
    public String getEyeColor() {
        return this.eyeColor;
    }

    /**
     * Sets the eye color of the character.
     *
     * @param eyeColor The new eye color to set.
     */
    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Gets the religion or belief system of the character.
     *
     * @return The religion of the character.
     */
    public String getReligion() {
        return this.religion;
    }

    /**
     * Sets the religion or belief system of the character.
     *
     * @param religion The new religion to set.
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * Gets the current status or condition of the character.
     *
     * @return The status of the character.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets the current status or condition of the character.
     *
     * @param status The new status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the additional description providing details about the character.
     *
     * @return The description of the character.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the additional description providing details about the character.
     *
     * @param description The new description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the image associated with the character.
     *
     * @return The image of the character.
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Sets the image associated with the character.
     *
     * @param image The new image to set.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Checks if the current character is equal to another object.
     *
     * @param o The object to compare.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name) && Objects.equals(country, character.country)
                && Objects.equals(title, character.title) && Objects.equals(gender, character.gender)
                && Objects.equals(species, character.species) && Objects.equals(age, character.age)
                && Objects.equals(height, character.height) && Objects.equals(hairColor, character.hairColor)
                && Objects.equals(eyeColor, character.eyeColor) && Objects.equals(religion, character.religion)
                && Objects.equals(status, character.status) && Objects.equals(description, character.description)
                && Objects.equals(image, character.image);
    }

    /**
     * Returns a formatted string representation of the character.
     *
     * @return A formatted string representing the character.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        // Basic Information
        str.append("**").append(name).append("**\n")
                .append(height).append(", ").append(gender.toLowerCase()).append(", ")
                .append(species.toLowerCase()).append(", ").append(age.toLowerCase()).append(" years old, ")
                .append(status.toLowerCase()).append("\n")
                .append(eyeColor).append(" eyes, ").append(hairColor.toLowerCase()).append(" hair\n");

        // Optional Information
        if (!title.isEmpty()) {
            str.append("Title(s): ").append(title).append("\n");
        }
        if (!religion.isEmpty()) {
            str.append("Religion: ").append(religion).append("\n");
        }

        // Description
        str.append("\n").append(description);

        return str.toString();
    }

}
