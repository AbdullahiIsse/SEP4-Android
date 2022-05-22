package com.abdu.and_sep4.View.UpdatePet;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Pet;


public class UpdatePetFragment extends Fragment {


    private View inflate;
    private AppCompatEditText updatePetName;
    private AppCompatEditText updatePetSpecies;
    private AppCompatEditText updatePetAge;
    private Button updateBtnPet;
    private UpdatePetFragmentViewModel updatePetFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_update_pet, container, false);
        updatePetName = inflate.findViewById(R.id.update_name);
        updatePetSpecies = inflate.findViewById(R.id.update_species);
        updatePetAge = inflate.findViewById(R.id.update_age);
        updateBtnPet = inflate.findViewById(R.id.updatePet);
        updatePetFragmentViewModel = new ViewModelProvider(this).get(UpdatePetFragmentViewModel.class);

        updatePetName.setText(getArguments().getString("name"));
        updatePetSpecies.setText(getArguments().getString("species"));
        updatePetAge.setText(Integer.toString(getArguments().getInt("age")));

        updateBtnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePetFragmentViewModel.updatePet(getArguments().getLong("id"), new Pet(getArguments().getLong("id"), updatePetName.getText().toString()
                        , updatePetSpecies.getText().toString(), Integer.parseInt(updatePetAge.getText().toString())));

                Navigation.findNavController(inflate).navigate(R.id.action_updatePetFragment_to_animalListFragment);
            }
        });


        return inflate;
    }
}