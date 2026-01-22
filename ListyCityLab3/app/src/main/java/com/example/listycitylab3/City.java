package com.example.listycitylab3;

import java.io.Serializable;

/**
 * This class represents a City
 */
public class City implements Serializable {
    // Attribs

    private String name;
    private String province;

    // Constructor

    /**
     * Default constructor for City class
     */
    public City() {
        this.name = "";
        this.province = "";
    }

    /**
     * Creates a City object
     *
     * @param name The name of the City
     * @param province The province in which the city resides
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

    @Override
    public String toString() {
        return String.format("%s\t%s", this.name, this.province);
    }
}
