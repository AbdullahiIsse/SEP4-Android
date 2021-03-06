package com.abdu.and_sep4.View.TerrariumDetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.View.Adapter.TemperatureSparkAdapter;


import com.abdu.and_sep4.View.WorkManager.CriticalValueWorker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.robinhood.spark.SparkView;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class TerrariumDetailsFragment extends Fragment {


    private TextView terrariumName;
    private TextView terrariumCurrentTemp;
    private TextView textViewDate;
    private SparkView sparkView;
    private TextView terrariumTemp;
    private ArrayList<TemperatureMeasurement> temperatureMeasurementArrayList = new ArrayList<>();
    private TemperatureSparkAdapter temperatureSparkAdapter;
    private Button animalBtn;
    private View inflate;
    private SharedPreferences sharedPreferences;
    private Button humidityGraphBtn;
    private Button co2GraphBtn;
    private ProgressBar progressBar;
    private TextView temp_error;



    private Button foodBtn;

    private Button notifyMe;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    private TerrariumDetailsFragmentViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_terrarium_details, container, false);
        terrariumName = inflate.findViewById(R.id.tv_terrarium_name);
        terrariumCurrentTemp = inflate.findViewById(R.id.tv_current_temp);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        textViewDate = inflate.findViewById(R.id.tv_date);
        sparkView = inflate.findViewById(R.id.sparkview);
        terrariumTemp = inflate.findViewById(R.id.tv_temp);
        progressBar = inflate.findViewById(R.id.progressBar);
        animalBtn = inflate.findViewById(R.id.terrarium_animals);
        humidityGraphBtn = inflate.findViewById(R.id.Humidity);
        co2GraphBtn = inflate.findViewById(R.id.CO2);
        foodBtn = inflate.findViewById(R.id.addFood);
        notifyMe = inflate.findViewById(R.id.notifyMe);

        sharedPreferences = getActivity().getSharedPreferences("terrariumId", Context.MODE_PRIVATE);
        Terrarium terrarium = SaveInfo.getInstance().getTerrarium();
        terrariumName.setText(terrarium.getEui());
       // terrariumCurrentTemp.setText(Double.toString(20.1));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(terrarium.getEui());


        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id", terrarium.getEui());

        editor.apply();


        viewModel = new ViewModelProvider(this).get(TerrariumDetailsFragmentViewModel.class);


        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ifNetworkIsAvailable()) {
                    viewModel.feedAnimal(SaveInfo.getInstance().getTerrarium().getEui());
                } else {
                    Toast.makeText(inflate.getContext(), "Please connect to the internet", Toast.LENGTH_LONG).show();
                }


            }
        });


        viewModel.getTemperatureByUserIdAndEuiLiveData(firebaseUser.getUid(),
                SaveInfo.getInstance().getTerrarium().getEui()).observe(getViewLifecycleOwner(), new Observer<List<TemperatureMeasurement>>() {
            @Override
            public void onChanged(List<TemperatureMeasurement> temperatureMeasurements) {
                progressBar.setVisibility(View.GONE);

                List<TemperatureMeasurement> body = temperatureMeasurements;

                sparkView.setScrubEnabled(true);
                sparkView.setScrubListener(t -> {

                    if (t instanceof TemperatureMeasurement) {
                        updateInfoForDate((TemperatureMeasurement) t);
                    }
                });
                temperatureMeasurementArrayList = (ArrayList<TemperatureMeasurement>) body;
                Log.e("test", "det virker");
                updateDisplayWithData(temperatureMeasurementArrayList);
                Log.e("Viewmodel-temp", temperatureMeasurements.toString());
            }
        });

        notifyMe.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (ifNetworkIsAvailable()) {
                    Constraints constraints = new Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build();


                    @SuppressLint("InvalidPeriodicWorkRequestInterval")
                    PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(CriticalValueWorker.class, Duration.ofMinutes(1))
                            .setConstraints(constraints).build();


                    WorkManager.getInstance(getContext()).enqueue(periodicWorkRequest);
                } else {
                    Toast.makeText(inflate.getContext(), "Please connect to the internet", Toast.LENGTH_LONG).show();
                }

            }
        });

        animalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(inflate).navigate(R.id.action_terrariumDetailsFragment_to_animalListFragment);


            }
        });

        humidityGraphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(inflate).navigate(R.id.action_terrariumDetailsFragment_to_humidityFragment);
            }
        });

        co2GraphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(inflate).navigate(R.id.action_terrariumDetailsFragment_to_co2Fragment);
            }
        });


        return inflate;
    }


    public boolean ifNetworkIsAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) inflate.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null) {
            if (info.isConnected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }


    private void updateDisplayWithData(List<TemperatureMeasurement> dailyData) {


        temperatureSparkAdapter = new TemperatureSparkAdapter(dailyData);

        sparkView.setAdapter(temperatureSparkAdapter);


        if (!dailyData.isEmpty()) {
            updateInfoForDate(dailyData.get(dailyData.size() - 1));
        } else {
            Handler handler = new Handler(Looper.getMainLooper());

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (dailyData.isEmpty()){
                        textViewDate.setText("temp not available");
                        terrariumTemp.setText("");
                        terrariumCurrentTemp.setText("");
                    }


                }
            },500);

        }

    }


    private void updateInfoForDate(TemperatureMeasurement temperatureMeasurement) {


        textViewDate.setText(temperatureMeasurement.getTimestamp());
        terrariumTemp.setText(Double.toString(temperatureMeasurement.getMeasurement()));
        terrariumCurrentTemp.setText(Double.toString(temperatureMeasurement.getMeasurement()));


    }


}