package com.abdu.and_sep4.View.AddPet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Pet;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddPetFragment extends Fragment {

    private AddPetFragmentViewModel addPetVM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_pet, container, false);

        addPetVM = new ViewModelProvider(this).get(AddPetFragmentViewModel.class);

        EditText name = (EditText) view.findViewById(R.id.name);
        EditText species = (EditText) view.findViewById(R.id.species);
        EditText age = (EditText) view.findViewById(R.id.age);

        Button addPet = (Button) view.findViewById(R.id.addPet);
        addPet.setOnClickListener((view1) -> {

            Pet pet = new Pet(
                    name.getText().toString(),
                    species.getText().toString(),
                    Integer.parseInt(age.getText().toString())

            );

            addPetVM.addPet(pet);



        });

        // Inflate the layout for this fragment
        return view;

    }




}