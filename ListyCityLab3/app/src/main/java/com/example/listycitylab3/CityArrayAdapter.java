package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * An ArrayAdapter to be used with the City class
 */
public class CityArrayAdapter extends ArrayAdapter<City> {

    /**
     * Creates a CityArrayAdapter
     * @param context The calling activity's context. The current state of the app.
     * @param cities an ArrayList of City objects
     */
    public CityArrayAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
    }

    /**
     * Custom getView() function for implementation of a ListView holding City objects
     * @param position index of City object in List View
     * @param convertView used to recycle old views within a ListView
     * @param parent the view containing the List View
     * @return either a new or reused view
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false);
        } else { // If there is a view to reuse, reuse it.
            view = convertView;
        }

        City city = getItem(position);
        TextView cityName = view.findViewById(R.id.city_text);
        TextView provinceName = view.findViewById(R.id.province_text);

        cityName.setText(city.getName());
        provinceName.setText(city.getProvince());

        return view;
    }
}
