package com.abdu.and_sep4.View.AddTerrarium;

import android.content.Context;
import android.content.SharedPreferences;
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


public class AddTerrariumFragment extends Fragment {

    private View inflate;
    private AppCompatEditText et_TerrariumEui;
    private AppCompatEditText et_tempMax;
    private AppCompatEditText et_tempMin;
    private AppCompatEditText et_humMax;
    private AppCompatEditText et_humMin;
    private AppCompatEditText et_co2Max;
    private Button addTerrariumBtn;
    private SharedPreferences sharedPreferences;
    private AddTerrariumFragmentViewModel addTerrariumFragmentViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_add_terrarium, container, false);

        et_TerrariumEui = inflate.findViewById(R.id.Terrarium_Eui);
        et_tempMax = inflate.findViewById(R.id.Temp_max);
        et_tempMin = inflate.findViewById(R.id.Temp_min);
        et_humMax = inflate.findViewById(R.id.Hum_max);
        et_humMin = inflate.findViewById(R.id.Hum_min);
        et_co2Max = inflate.findViewById(R.id.CO2_max);
        addTerrariumBtn = inflate.findViewById(R.id.addTerrarium);
        //sharedPreferences = getActivity().getSharedPreferences("CriticalValues", Context.MODE_PRIVATE);
        addTerrariumFragmentViewModel = new ViewModelProvider(this).get(AddTerrariumFragmentViewModel.class);

        addTerrariumBtn.setOnClickListener(this::addTerrarium);

        return inflate;
    }

    private void addTerrarium(View view) {

        String eui = et_TerrariumEui.getText().toString();
        String tempMin = et_tempMin.getText().toString();
        String tempMax = et_tempMax.getText().toString();
        String humMin = et_humMin.getText().toString();
        String humMax = et_humMax.getText().toString();
        String co2Max = et_co2Max.getText().toString();


        if (validations(eui, tempMin, tempMax, humMin, humMax, co2Max)) {

            addTerrariumToDb(eui, Double.parseDouble(tempMin), Double.parseDouble(tempMax), Double.parseDouble(humMin), Double.parseDouble(humMax), Integer.parseInt(co2Max));


//            SharedPreferences.Editor editor = sharedPreferences.edit();
//
//            editor.putString("tempMax",tempMax);
//            editor.putString("tempMin",tempMin);
//            editor.putString("humMax",humMax);
//            editor.putString("humMin",humMin);
//            editor.putString("co2Max",co2Max);
//
//            editor.apply();


            et_TerrariumEui.setText("");
            et_tempMax.setText("");
            et_tempMin.setText("");
            et_humMax.setText("");
            et_humMin.setText("");
            et_co2Max.setText("");

        }


    }

    private void addTerrariumToDb(String eui, double tempMin, double tempMax, double humMin, double humMax, int co2Max) {
        Terrarium terrarium = new Terrarium(eui, "jack",
                tempMin, tempMax
                , humMin, humMax, co2Max);


        addTerrariumFragmentViewModel.addTerrariumToDb(terrarium);
        Navigation.findNavController(inflate).navigate(R.id.action_addTerrariumFragment_to_homeFragment);


    }


    private boolean validations(String eui, String tempMin, String tempMax, String humMin, String humMax, String co2Max) {
        if (eui.isEmpty()) {
            et_TerrariumEui.setError("Eui can not be empty");
            et_TerrariumEui.requestFocus();
            return false;
        }

        if (tempMin.isEmpty()) {
            et_tempMin.setError("Temp Min can not be Empty");
            et_tempMin.requestFocus();
            return false;
        }

        if (Double.parseDouble(tempMin) > 99.9) {
            et_tempMin.setError("Temp Min can not be more than 99");
            et_tempMin.requestFocus();
            return false;
        }

        if (Double.parseDouble(tempMin) <= 0.0) {
            et_tempMin.setError("Temp Min can not be more than 99");
            et_tempMin.requestFocus();
            return false;
        }

        if (tempMax.isEmpty()) {
            et_tempMax.setError("Temp max can not be empty");
            et_tempMax.requestFocus();
            return false;
        }

        if (Double.parseDouble(tempMax) > 99.9) {
            et_tempMin.setError("Temp Max can not be more than 99");
            et_tempMin.requestFocus();
            return false;
        }

        if (Double.parseDouble(tempMax) <= 0.0) {
            et_tempMin.setError("Temp Max can not be less than 0");
            et_tempMin.requestFocus();
            return false;
        }


        if (humMin.isEmpty()) {
            et_humMin.setError("Min Humidity can not be empty");
            et_humMin.requestFocus();
            return false;
        }

        if (Double.parseDouble(humMin) > 99.9) {
            et_tempMin.setError("Hum Min can not be more than 99");
            et_tempMin.requestFocus();
            return false;
        }

        if (Double.parseDouble(humMin) <= 0.0) {
            et_tempMin.setError("Temp Min can not be less than 0");
            et_tempMin.requestFocus();
            return false;
        }

        if (humMax.isEmpty()) {
            et_humMax.setError("Max Humidity can not be empty");
            et_humMax.requestFocus();
            return false;
        }

        if (Double.parseDouble(humMax) > 99.9) {
            et_tempMin.setError("Hum Max can not be more than 99");
            et_tempMin.requestFocus();
            return false;
        }

        if (Double.parseDouble(humMax) <= 0.0) {
            et_tempMin.setError("Hum Max can not be less than 0");
            et_tempMin.requestFocus();
            return false;
        }

        if (co2Max.isEmpty()) {
            et_co2Max.setError("Max CO2 can not be empty");
            et_co2Max.requestFocus();
            return false;
        }
        if (Integer.parseInt(co2Max) <= 0) {
            et_tempMin.setError("CO2 Max can not be less than 0");
            et_tempMin.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}