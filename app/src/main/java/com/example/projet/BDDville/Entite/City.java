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

    public City(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }
}
