package com.example.projet.BDDville;

import android.content.Context;

import com.example.projet.BDDville.DAO.CityDao;
import com.example.projet.BDDville.Entite.City;

import java.util.concurrent.Executors;

public class CitySeeder {

    public static void seed(Context context) {
        Executors.newSingleThreadExecutor().execute(() -> {
            CityDao dao = AppDatabase.getInstance(context).cityDao();

            dao.insert(new City("Paris", "France", 2148000, 48.8566, 2.3522));
            dao.insert(new City("Lyon", "France", 518635, 45.7640, 4.8357));
            dao.insert(new City("Marseille", "France", 870321, 43.2965, 5.3698));

            dao.insert(new City("Berlin", "Germany", 3769000, 52.5200, 13.4050));
            dao.insert(new City("Munich", "Germany", 1472000, 48.1351, 11.5820));
            dao.insert(new City("Hamburg", "Germany", 1841000, 53.5511, 9.9937));

            dao.insert(new City("Madrid", "Spain", 3266000, 40.4168, -3.7038));
            dao.insert(new City("Barcelona", "Spain", 1620000, 41.3851, 2.1734));
            dao.insert(new City("Valencia", "Spain", 794288, 39.4699, -0.3763));

            dao.insert(new City("Rome", "Italy", 2873000, 41.9028, 12.4964));
            dao.insert(new City("Milan", "Italy", 1352000, 45.4642, 9.1900));
            dao.insert(new City("Naples", "Italy", 962003, 40.8518, 14.2681));

            dao.insert(new City("London", "United Kingdom", 8982000, 51.5074, -0.1278));
            dao.insert(new City("Manchester", "United Kingdom", 553230, 53.4808, -2.2426));
            dao.insert(new City("Birmingham", "United Kingdom", 1141000, 52.4862, -1.8904));

            dao.insert(new City("Washington, D.C.", "United States", 712816, 38.9072, -77.0369));
            dao.insert(new City("New York", "United States", 8419600, 40.7128, -74.0060));
            dao.insert(new City("Los Angeles", "United States", 3980400, 34.0522, -118.2437));

            dao.insert(new City("Ottawa", "Canada", 994837, 45.4215, -75.6972));
            dao.insert(new City("Toronto", "Canada", 2732000, 43.6510, -79.3470));
            dao.insert(new City("Vancouver", "Canada", 631486, 49.2827, -123.1207));

            dao.insert(new City("Tokyo", "Japan", 13929000, 35.6895, 139.6917));
            dao.insert(new City("Osaka", "Japan", 2691000, 34.6937, 135.5023));
            dao.insert(new City("Nagoya", "Japan", 2296000, 35.1815, 136.9066));

            dao.insert(new City("Beijing", "China", 21540000, 39.9042, 116.4074));
            dao.insert(new City("Shanghai", "China", 24240000, 31.2304, 121.4737));
            dao.insert(new City("Guangzhou", "China", 14904000, 23.1291, 113.2644));

            dao.insert(new City("Brasília", "Brazil", 3055149, -15.8267, -47.9218));
            dao.insert(new City("São Paulo", "Brazil", 12330000, -23.5505, -46.6333));
            dao.insert(new City("Rio de Janeiro", "Brazil", 6748000, -22.9068, -43.1729));

            dao.insert(new City("Cairo", "Egypt", 9900000, 30.0444, 31.2357));
            dao.insert(new City("Alexandria", "Egypt", 5200000, 31.2001, 29.9187));
            dao.insert(new City("Giza", "Egypt", 8700000, 30.0131, 31.2089));
        });
    }
}
