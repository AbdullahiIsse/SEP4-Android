package com.abdu.and_sep4.View.UpdatePet;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Animal;


public class UpdatePetFragment extends Fragment {


    private View inflate;
    private AppCompatEditText et_name;
    private AppCompatEditText et_species;
    private AppCompatEditText et_age;
    private AppCompatEditText et_gender;
    private AppCompatEditText et_shedding;
    private AppCompatEditText et_hibernating;
    private AppCompatEditText et_hasOffSpring;
    private Button updateBtnPet;
    private UpdatePetFragmentViewModel updatePetFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_update_pet, container, false);
        et_name = inflate.findViewById(R.id.Updatename);
        et_species = inflate.findViewById(R.id.Updatespecies);
        et_age = inflate.findViewById(R.id.Updateage);
        et_gender = inflate.findViewById(R.id.Updategender);
        et_shedding = inflate.findViewById(R.id.Updateshedding);
        et_hibernating = inflate.findViewById(R.id.Updatehibernating);
        et_hasOffSpring = inflate.findViewById(R.id.UpdatehasOffSpring);
        updateBtnPet = inflate.findViewById(R.id.updatePet);
        updatePetFragmentViewModel = new ViewModelProvider(this).get(UpdatePetFragmentViewModel.class);


        et_name.setText(getArguments().getString("name"));
        et_species.setText(getArguments().getString("species"));
        et_age.setText(Integer.toString(getArguments().getInt("age")));
        et_gender.setText(getArguments().getString("gender"));
        et_shedding.setText(Boolean.toString(getArguments().getBoolean("shedding")));
        et_hibernating.setText(Boolean.toString(getArguments().getBoolean("hibernating")));
        et_hasOffSpring.setText(Boolean.toString(getArguments().getBoolean("hasOffSpring")));

        Log.e("updatePetId",Integer.toString(getArguments().getInt("id")));

        updateBtnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePetFragmentViewModel.updatePet(getArguments().getInt("id"),new Animal("abc123",
                        et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()),
                        et_species.getText().toString(),et_gender.getText().toString(),
                        Boolean.parseBoolean(et_hasOffSpring.getText().toString()),Boolean.parseBoolean(et_hibernating.getText().toString()),
                        Boolean.parseBoolean(et_shedding.getText().toString())));

                Navigation.findNavController(inflate).navigate(R.id.action_updatePetFragment_to_animalListFragment);
            }
        });


        return inflate;
    }
}