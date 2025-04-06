package com.example.projet.BDDville;

import android.content.Context;

import com.example.projet.BDDville.DAO.CityDao;
import com.example.projet.BDDville.Entite.City;

import java.util.concurrent.Executors;

public class CitySeeder {

    public static void seed(Context context) {
        Executors.newSingleThreadExecutor().execute(() -> {
            CityDao dao = AppDatabase.getInstance(context).cityDao();

            dao.insert(new City("Paris", "France", 2148000));
            dao.insert(new City("Lyon", "France", 518635));
            dao.insert(new City("Marseille", "France", 870321));

            dao.insert(new City("Berlin", "Germany", 3769000));
            dao.insert(new City("Munich", "Germany", 1472000));
            dao.insert(new City("Hamburg", "Germany", 1841000));

            dao.insert(new City("Madrid", "Spain", 3266000));
            dao.insert(new City("Barcelona", "Spain", 1620000));
            dao.insert(new City("Valencia", "Spain", 794288));

            dao.insert(new City("Rome", "Italy", 2873000));
            dao.insert(new City("Milan", "Italy", 1352000));
            dao.insert(new City("Naples", "Italy", 962003));

            dao.insert(new City("London", "United Kingdom", 8982000));
            dao.insert(new City("Manchester", "United Kingdom", 553230));
            dao.insert(new City("Birmingham", "United Kingdom", 1141000));

            dao.insert(new City("Washington, D.C.", "United States", 712816));
            dao.insert(new City("New York", "United States", 8419600));
            dao.insert(new City("Los Angeles", "United States", 3980400));

            dao.insert(new City("Ottawa", "Canada", 994837));
            dao.insert(new City("Toronto", "Canada", 2732000));
            dao.insert(new City("Vancouver", "Canada", 631486));

            dao.insert(new City("Tokyo", "Japan", 13929000));
            dao.insert(new City("Osaka", "Japan", 2691000));
            dao.insert(new City("Nagoya", "Japan", 2296000));

            dao.insert(new City("Beijing", "China", 21540000));
            dao.insert(new City("Shanghai", "China", 24240000));
            dao.insert(new City("Guangzhou", "China", 14904000));

            dao.insert(new City("Brasília", "Brazil", 3055149));
            dao.insert(new City("São Paulo", "Brazil", 12330000));
            dao.insert(new City("Rio de Janeiro", "Brazil", 6748000));

            dao.insert(new City("Cairo", "Egypt", 9900000));
            dao.insert(new City("Alexandria", "Egypt", 5200000));
            dao.insert(new City("Giza", "Egypt", 8700000));
        });
    }
}
