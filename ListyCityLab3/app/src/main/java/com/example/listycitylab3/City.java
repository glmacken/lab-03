package com.example.listycitylab3;

import java.io.Serializable;

/**
 * This class represents a City
 */
public class City implements Serializable {
    // Attributes

    private String name;
    private String province;

    // Constructors

    /**
     * Default constructor for City class
     */
    public City() {
        this.name = "";
        this.province = "";
    }

    /**
     * Creates a City object
     * @param name The name of the City
     * @param province The province in which the City resides
     */
    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() { return province; }

    public void setProvince(String province) { this.province = province; }


    // Functions

    /**
     * Provides a string representation of a City
     * @return the string representation of a City including it's name and province.
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.name, this.province);
    }
}
