package com.abdu.and_sep4.View.CriticalValue;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.abdu.and_sep4.R;


public class CriticalValueFragment extends Fragment {

    private AppCompatEditText et_tempMax;
    private AppCompatEditText et_tempMin;
    private AppCompatEditText et_humMax;
    private AppCompatEditText et_humMin;
    private AppCompatEditText et_co2Max;
    private AppCompatEditText et_co2Min;
    private Button button;
    private SharedPreferences sharedPreferences;
    private View inflate;
    private String tempMin, tempMax, humMax, humMin, co2Max, co2Min;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_critical_value, container, false);

        et_tempMax = inflate.findViewById(R.id.Temp_max);
        et_tempMin = inflate.findViewById(R.id.Temp_min);
        et_humMax = inflate.findViewById(R.id.Hum_max);
        et_humMin = inflate.findViewById(R.id.Hum_min);
        et_co2Max = inflate.findViewById(R.id.CO2_max);
        et_co2Min = inflate.findViewById(R.id.CO2_min);
        button = inflate.findViewById(R.id.addCriticalValues);

        sharedPreferences = getActivity().getSharedPreferences("CriticalValues", Context.MODE_PRIVATE);

        button.setOnClickListener(this::addCriticalValues);


        return inflate;
    }

    private void addCriticalValues(View view) {
        tempMax = et_tempMax.getText().toString();
        tempMin = et_tempMin.getText().toString();
        humMax = et_humMax.getText().toString();
        humMin = et_humMin.getText().toString();
        co2Max = et_co2Max.getText().toString();
        co2Min = et_co2Min.getText().toString();

        if (tempMax.isEmpty()) {
            et_tempMax.setError("Max temperature cannot be empty");
            et_tempMax.requestFocus();
            return;
        }

        if (tempMin.isEmpty()) {
            et_tempMin.setError("Min temperature cannot be empty");
            et_tempMin.requestFocus();
            return;
        }
        if (humMax.isEmpty()) {
            et_humMax.setError("Max Humidity cannot be empty");
            et_humMax.requestFocus();
            return;
        }

        if (humMin.isEmpty()) {
            et_humMin.setError("Min Humidity cannot be empty");
            et_humMin.requestFocus();
            return;
        }


        if (co2Max.isEmpty()) {
            et_co2Max.setError("Max CO2 cannot be empty");
            et_co2Max.requestFocus();
            return;
        }

        if (co2Min.isEmpty()) {
            et_co2Min.setError("Min CO2 cannot be empty");
            et_co2Min.requestFocus();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("tempMax",tempMax);
        editor.putString("tempMin",tempMin);
        editor.putString("humMax",humMax);
        editor.putString("humMin",humMin);
        editor.putString("co2Max",co2Max);
        editor.putString("co2Min",co2Min);

        editor.apply();

        Toast.makeText(getContext(),"Saved critical values",Toast.LENGTH_LONG).show();

        clear();


    }


    private void clear(){
        et_tempMax.setText("");
        et_tempMin.setText("");
        et_humMax.setText("");
        et_humMin.setText("");
        et_co2Max.setText("");
        et_co2Min.setText("");
    }
}