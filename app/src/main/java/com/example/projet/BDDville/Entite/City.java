package com.example.projet.BDDville.Entite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cities")
public class City {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String country;
    public int population;
    public double latitude;
    public double longitude;

    public City(String name, String country, int population, double latitude, double longitude) {
        this.name = name;
        this.country = country;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
