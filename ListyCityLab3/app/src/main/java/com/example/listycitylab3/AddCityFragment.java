package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * A dialog fragment used for adding or editing a City object.
 * This fragment displays UI used to capture user input and return that input
 * to the host activity via the AddCityDialogueListener which must be implemented in the host.
 */
public class AddCityFragment extends DialogFragment {

    public interface AddCityDialogListener {
        void addCity(City city);
        void editCity(City city, String newName, String newProvince);
    }

    private AddCityDialogListener listener;

    /**
     * Overrides the onAttach() method
     * This implementation checks for a listener in the calling Activity
     * @param context the current state of the app provided by the calling Activity
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    /**
     * Overrides the onCreateDialogue() function
     * This implementation is used in conjunction with a ListView consisting of City objects
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     *
     * @return a dialog used to add or edit a City element in a ListView
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // get the City data if we passed any to it
        Bundle args = getArguments();
        City cityToEdit;
        if (args != null) {
            cityToEdit = (City) args.getSerializable("city");
        } else {
            cityToEdit = null;
        }

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // If there's a city to edit, overwrite the default dialogue in the dialogue box
        if (cityToEdit != null) {
            editCityName.setText(cityToEdit.getName());
            editProvinceName.setText(cityToEdit.getProvince());
        }

        return builder
                .setView(view)
                .setTitle("Add/edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    if (cityToEdit != null) { // Edit mode (there's a city to edit)
                        listener.editCity(cityToEdit, cityName, provinceName);
                    } else { // Add mode
                        listener.addCity(new City(cityName, provinceName));
                    }
                })
                .create();
    }

    /**
     * Creates a new instance of AddCityFragment and passes a City to it.
     * @param city the selected City element
     * @return a new AddCityFragment with the selected City data passed to it
     */
    static AddCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);

        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
