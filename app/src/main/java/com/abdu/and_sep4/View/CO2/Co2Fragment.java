package com.abdu.and_sep4.View.CO2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.View.Adapter.Co2SparkAdapter;
import com.robinhood.spark.SparkView;

import java.util.ArrayList;
import java.util.List;


public class Co2Fragment extends Fragment {

    private View inflate;
    private TextView terrariumName;
    private TextView terrariumCurrentTemp;
    private TextView textViewDate;
    private SparkView sparkView;
    private TextView terrariumTemp;
    private ArrayList<Co2Measurement> co2MeasurementArrayList = new ArrayList<>();
    private Co2SparkAdapter co2SparkAdapter;
    private Co2FragmentViewModel viewModel;
    private ProgressBar progressBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_co2, container, false);
        terrariumName = inflate.findViewById(R.id.tv_terrarium_name);
        terrariumCurrentTemp = inflate.findViewById(R.id.tv_current_temp);
        textViewDate = inflate.findViewById(R.id.tv_date);
        sparkView = inflate.findViewById(R.id.sparkview);
        terrariumTemp = inflate.findViewById(R.id.tv_temp);
        progressBar = inflate.findViewById(R.id.progressBar);
        Terrarium terrarium = SaveInfo.getInstance().getTerrarium();
        terrariumName.setText(terrarium.getEui());

        viewModel = new ViewModelProvider(this).get(Co2FragmentViewModel.class);


        viewModel.getCo2("jack",SaveInfo.getInstance().getTerrarium().getEui()).observe(getViewLifecycleOwner(), new Observer<List<Co2Measurement>>() {
            @Override
            public void onChanged(List<Co2Measurement> co2Measurements) {
                progressBar.setVisibility(View.GONE);
                List<Co2Measurement> body = co2Measurements;

                sparkView.setScrubEnabled(true);
                sparkView.setScrubListener(t -> {

                    if (t instanceof Co2Measurement) {
                        updateInfoForDate((Co2Measurement) t);
                    }
                });
                co2MeasurementArrayList = (ArrayList<Co2Measurement>) body;
                Log.e("test", "det virker");
                updateDisplayWithData(co2MeasurementArrayList);

                Log.e("Viewmodel-co2", co2Measurements.toString());
            }
        });



        return inflate;
    }




    private void updateDisplayWithData(List<Co2Measurement> dailyData) {


        co2SparkAdapter = new Co2SparkAdapter(dailyData);

        sparkView.setAdapter(co2SparkAdapter);


        if (!dailyData.isEmpty()) {
            updateInfoForDate(dailyData.get(dailyData.size() - 1));
        } else {

            terrariumCurrentTemp.setText("temperature is not available for terrarium");
            textViewDate.setText("");
            terrariumTemp.setText("");
        }

    }


    private void updateInfoForDate(Co2Measurement co2Measurement) {


        textViewDate.setText(co2Measurement.getTimestamp());
        terrariumTemp.setText(Double.toString(co2Measurement.getMeasurement()));
        terrariumCurrentTemp.setText(Double.toString(co2Measurement.getMeasurement()));


    }


}