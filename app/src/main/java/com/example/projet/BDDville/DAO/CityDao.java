package com.example.projet.BDDville.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.projet.BDDville.Entite.City;

import java.util.List;
@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(City city);

    @Query("SELECT * FROM cities ORDER BY name ASC")
    List<City> getAllCities();

    @Delete
    void delete(City city);

    @Query("DELETE FROM cities")
    void clearAll();
}
