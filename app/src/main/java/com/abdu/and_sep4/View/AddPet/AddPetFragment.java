package com.abdu.and_sep4.View.AddPet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Pet;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.TerrariumV2;


public class AddPetFragment extends Fragment {

    private AddPetFragmentViewModel addPetVM;
    private View inflate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.fragment_add_pet, container, false);

        addPetVM = new ViewModelProvider(this).get(AddPetFragmentViewModel.class);

        EditText name = (EditText) inflate.findViewById(R.id.name);
        EditText species = (EditText) inflate.findViewById(R.id.species);
        EditText age = (EditText) inflate.findViewById(R.id.age);

        Button addPet = (Button) inflate.findViewById(R.id.addPet);
        TerrariumV2 terrarium = SaveInfo.getInstance().getTerrarium();
        addPet.setOnClickListener((view1) -> {

            Pet pet = new Pet(
                    name.getText().toString(),
                    species.getText().toString(),
                    Integer.parseInt(age.getText().toString()), Long.parseLong(terrarium.getEui())

            );

            addPetVM.addPet(pet);

            name.setText("");
            species.setText("");
            age.setText("");


            Navigation.findNavController(inflate).navigate(R.id.action_AddPetFragments_to_animalListFragment);

        });

        // Inflate the layout for this fragment
        return inflate;

    }




}