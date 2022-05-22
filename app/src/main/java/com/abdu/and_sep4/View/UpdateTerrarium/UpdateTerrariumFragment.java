package com.abdu.and_sep4.View.UpdateTerrarium;

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
import com.abdu.and_sep4.Shared.Terrarium;


public class UpdateTerrariumFragment extends Fragment {

    private View inflate;
    private AppCompatEditText updateTerrariumName;
    private Button updateBtnTerrarium;
    private UpdateTerrariumFragmentViewModel updateTerrariumFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_update_terrarium, container, false);
        updateTerrariumName = inflate.findViewById(R.id.UpdateTerrarium_name);
        updateBtnTerrarium = inflate.findViewById(R.id.updateTerrarium);
        updateTerrariumFragmentViewModel = new ViewModelProvider(this).get(UpdateTerrariumFragmentViewModel.class);


        updateTerrariumName.setText(getArguments().getString("TerrariumName"));


        updateBtnTerrarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTerrariumFragmentViewModel.updateTerrarium(getArguments().getLong("TerrariumId"), new Terrarium((int) getArguments().getLong("TerrariumId"), updateTerrariumName.getText().toString()));
                Navigation.findNavController(inflate).navigate(R.id.action_updateTerrariumFragment_to_homeFragment);
            }
        });


        return inflate;
    }
}