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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UpdateTerrariumFragment extends Fragment {

    private View inflate;
    private Button updateBtnTerrarium;
    private UpdateTerrariumFragmentViewModel updateTerrariumFragmentViewModel;
    private AppCompatEditText et_TerrariumEui;
    private AppCompatEditText et_tempMax;
    private AppCompatEditText et_tempMin;
    private AppCompatEditText et_humMax;
    private AppCompatEditText et_humMin;
    private AppCompatEditText et_co2Max;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_update_terrarium, container, false);
        et_TerrariumEui = inflate.findViewById(R.id.UpdateTerrarium_Eui);
        et_tempMax = inflate.findViewById(R.id.UpdateTemp_max);
        et_tempMin = inflate.findViewById(R.id.UpdateTemp_min);
        et_humMax = inflate.findViewById(R.id.UpdateHum_max);
        et_humMin = inflate.findViewById(R.id.UpdateHum_min);
        et_co2Max = inflate.findViewById(R.id.UpdateCO2_max);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        updateBtnTerrarium = inflate.findViewById(R.id.updateTerrarium);
        updateTerrariumFragmentViewModel = new ViewModelProvider(this).get(UpdateTerrariumFragmentViewModel.class);



        et_TerrariumEui.setText(getArguments().getString("TerrariumEui"));
        et_tempMin.setText(Double.toString(getArguments().getDouble("TerrariumMinTemp")));
        et_tempMax.setText(Double.toString(getArguments().getDouble("TerrariumMaxTemp")));
        et_humMin.setText(Double.toString(getArguments().getDouble("TerrariumMinHum")));
        et_humMax.setText(Double.toString(getArguments().getDouble("TerrariumMaxHum")));
        et_co2Max.setText(Integer.toString(getArguments().getInt("TerrariumMaxCo2")));


        updateBtnTerrarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTerrariumFragmentViewModel.updateTerrarium(getArguments().getString("TerrariumEui"),
                        new Terrarium(et_TerrariumEui.getText().toString(),
                                firebaseUser.getUid(),
                                Double.parseDouble(et_tempMin.getText().toString()),
                                Double.parseDouble(et_tempMax.getText().toString()),
                                Double.parseDouble(et_humMin.getText().toString()),
                                Double.parseDouble(et_humMax.getText().toString()), Integer.parseInt(et_co2Max.getText().toString())));
                Navigation.findNavController(inflate).navigate(R.id.action_updateTerrariumFragment_to_homeFragment);
            }
        });


        return inflate;
    }
}