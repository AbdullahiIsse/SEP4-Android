package com.abdu.and_sep4.View.AddPet;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Animal;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Terrarium;


public class AddPetFragment extends Fragment {

    private AddPetFragmentViewModel addPetVM;
    private View inflate;
    private AppCompatEditText et_name;
    private AppCompatEditText et_species;
    private AppCompatEditText et_age;
    private AppCompatEditText et_gender;
    private AppCompatEditText et_shedding;
    private AppCompatEditText et_hibernating;
    private AppCompatEditText et_hasOffSpring;
    private Button addPet;
    private Terrarium terrarium;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_add_pet, container, false);


        et_name = inflate.findViewById(R.id.name);
        et_species = inflate.findViewById(R.id.species);
        et_age = inflate.findViewById(R.id.age);
        et_gender = inflate.findViewById(R.id.gender);
        et_shedding = inflate.findViewById(R.id.shedding);
        et_hibernating = inflate.findViewById(R.id.hibernating);
        et_hasOffSpring = inflate.findViewById(R.id.hasOffSpring);
        addPet = inflate.findViewById(R.id.addPet);
        terrarium = SaveInfo.getInstance().getTerrarium();

        addPetVM = new ViewModelProvider(this).get(AddPetFragmentViewModel.class);

        addPet.setOnClickListener(this::addPet);


        return inflate;

    }

    private void addPet(View view) {
        String name = et_name.getText().toString();
        String species = et_species.getText().toString();
        String age = et_age.getText().toString();
        String gender = et_gender.getText().toString();
        String shedding = et_shedding.getText().toString();
        String hibernating = et_hibernating.getText().toString();
        String hasOffSpring = et_hasOffSpring.getText().toString();

        if (validations(name, species, age, gender, hasOffSpring, hibernating, shedding)) {

            addPetToDb(name, Integer.parseInt(age), species, gender,Boolean.parseBoolean(hasOffSpring) , Boolean.parseBoolean(hibernating), Boolean.parseBoolean(shedding));

            et_name.setText("");
            et_species.setText("");
            et_age.setText("");
            et_gender.setText("");
            et_shedding.setText("");
            et_hibernating.setText("");
            et_hasOffSpring.setText("");
        }


    }


    private void addPetToDb(String name, int age, String species, String gender, boolean shedding, boolean hibernating, boolean hasOffSpring) {


        Animal animal = new Animal(SaveInfo.getInstance().getTerrarium().getEui(), name, age, species, gender, shedding, hibernating, hasOffSpring);
        Log.e("animal", animal.toString());

        addPetVM.addAnimal(animal);

        Toast.makeText(getContext(),"Pet has been added",Toast.LENGTH_SHORT).show();

    }


    private boolean validations(String name, String species, String age, String gender, String shedding, String hibernating, String hasOffSpring) {
        if (name.isEmpty()) {
            et_name.setError("Name can not be empty");
            et_name.requestFocus();
            return false;
        }

        if (species.isEmpty()) {
            et_species.setError("Species can not be empty");
            et_species.requestFocus();
            return false;
        }


        if (age.isEmpty()) {
            et_age.setError("Age can not be empty");
            et_age.requestFocus();
            return false;
        }
        if (Integer.parseInt(age) <= 0) {
            et_age.setError("Age can not be 0 or less");
            et_age.requestFocus();
            return false;
        }

        if (gender.isEmpty()) {
            et_gender.setError("Gender can not be empty");
            et_gender.requestFocus();
            return false;
        }

        if (gender.length() != 1) {
            et_gender.setError("Gender must be 1 character");
            et_gender.requestFocus();
            return false;
        }

        if ( !gender.contentEquals("m") && !gender.contentEquals("f")  && !gender.contentEquals("M") && !gender.contentEquals("F")) {
            et_gender.setError("Gender must be m or f");
            et_gender.requestFocus();
            return false;
        }



        if (shedding.isEmpty()) {
            et_shedding.setError("Shedding can not be empty");
            et_shedding.requestFocus();
            return false;
        }

        if (!shedding.equalsIgnoreCase("true") && !shedding.equalsIgnoreCase("false") ) {
            et_shedding.setError("Shedding must be true or false");
            et_shedding.requestFocus();
            return false;
        }

        if (hibernating.isEmpty()) {
            et_hibernating.setError("hibernating can not be empty");
            et_hibernating.requestFocus();
            return false;
        }

        if (!hibernating.equalsIgnoreCase("true") && !hibernating.equalsIgnoreCase("false") ) {
            et_hibernating.setError("hibernating must be true or false");
            et_hibernating.requestFocus();
            return false;
        }

        if (hasOffSpring.isEmpty()) {
            et_hasOffSpring.setError("hasOffSpring can not be empty");
            et_hasOffSpring.requestFocus();
            return false;
        }

        if (!hasOffSpring.equalsIgnoreCase("true") && !hasOffSpring.equalsIgnoreCase("false") ) {
            et_hasOffSpring.setError("hasOffSpring must be true or false");
            et_hasOffSpring.requestFocus();
            return false;
        }
        else {
            return true;
        }

    }


}