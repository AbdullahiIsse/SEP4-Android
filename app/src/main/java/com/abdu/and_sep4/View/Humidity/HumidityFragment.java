package com.abdu.and_sep4.View.Humidity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.View.Adapter.HumiditySparkAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.robinhood.spark.SparkView;

import java.util.ArrayList;
import java.util.List;


public class HumidityFragment extends Fragment {

    private View inflate;

    private TextView terrariumName;
    private TextView terrariumCurrentTemp;
    private TextView textViewDate;
    private SparkView sparkView;
    private TextView terrariumTemp;
    private ArrayList<HumidityMeasurement> humidityMeasurementArrayList = new ArrayList<>();
    private HumiditySparkAdapter humiditySparkAdapter;
    private HumidityFragmentViewModel viewModel;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_humidity, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        terrariumName = inflate.findViewById(R.id.tv_terrarium_name);
        terrariumCurrentTemp = inflate.findViewById(R.id.tv_current_temp);
        textViewDate = inflate.findViewById(R.id.tv_date);
        sparkView = inflate.findViewById(R.id.sparkview);
        terrariumTemp = inflate.findViewById(R.id.tv_temp);
        progressBar = inflate.findViewById(R.id.progressBar);
        Terrarium terrarium = SaveInfo.getInstance().getTerrarium();
        terrariumName.setText(terrarium.getEui());

        viewModel = new ViewModelProvider(this).get(HumidityFragmentViewModel.class);


        viewModel.getHum(firebaseUser.getUid(),SaveInfo.getInstance().getTerrarium().getEui()).observe(getViewLifecycleOwner(), new Observer<List<HumidityMeasurement>>() {
            @Override
            public void onChanged(List<HumidityMeasurement> humidityMeasurements) {
                progressBar.setVisibility(View.GONE);
                List<HumidityMeasurement> body = humidityMeasurements;

                sparkView.setScrubEnabled(true);
                sparkView.setScrubListener(t -> {

                    if (t instanceof HumidityMeasurement) {
                        updateInfoForDate((HumidityMeasurement) t);
                    }
                });
                humidityMeasurementArrayList = (ArrayList<HumidityMeasurement>) body;
                Log.e("test","det virker");
                updateDisplayWithData(humidityMeasurementArrayList);;
                Log.e("Viewmodel-hum", humidityMeasurements.toString());
            }
        });



        return inflate;
    }



    private void updateDisplayWithData(List<HumidityMeasurement> dailyData) {


        humiditySparkAdapter = new HumiditySparkAdapter(dailyData);

        sparkView.setAdapter(humiditySparkAdapter);


        if (!dailyData.isEmpty()) {
            updateInfoForDate(dailyData.get(dailyData.size() - 1));
        } else {
            Handler handler = new Handler(Looper.getMainLooper());

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (dailyData.isEmpty()){
                        textViewDate.setText("humidity not available");
                        terrariumTemp.setText("");
                        terrariumCurrentTemp.setText("");
                    }


                }
            },500);


        }

    }


    private void updateInfoForDate(HumidityMeasurement humidityMeasurement) {


        textViewDate.setText(humidityMeasurement.getTimestamp());
        terrariumTemp.setText(Double.toString(humidityMeasurement.getMeasurement()));
        terrariumCurrentTemp.setText(Double.toString(humidityMeasurement.getMeasurement()));


    }

}