package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private ArrayAdapter<City> cityAdapter;

    // Main Entry Point

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = { "Edmonton", "Vancouver", "Toronto" };
        String[] provinces = { "AB", "BC", "ON" };

        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        // Set on click listeners

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Create new fragment and pass selected City data to it
                AddCityFragment editCityFragment = AddCityFragment.newInstance(cityAdapter.getItem(i));
                editCityFragment.show(getSupportFragmentManager(), "Edit City");
            }
        });
    }

    // Other Functions

    /**
     * Implements the addCity() method in the AddCityDialogListener interface
     * Adds a City to cityList
     * @param city the City to add to cityList
     */
    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    /**
     * Implements the editCity() method in the AddCityDialogListener interface
     * Edits the data of a City from cityList
     * @param city the City to edit from cityList
     * @param newName the new name to apply to city
     * @param newProvince the new province to apply to city
     */
    @Override
    public void editCity(City city, String newName, String newProvince) {
        // Make the edit
        city.setName(newName);
        city.setProvince(newProvince);
        // Let the ListView know you changed it so that it can display the edit
        cityAdapter.notifyDataSetChanged();
    }
}