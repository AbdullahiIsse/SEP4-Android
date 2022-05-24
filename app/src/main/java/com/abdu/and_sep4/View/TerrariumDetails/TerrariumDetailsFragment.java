package com.abdu.and_sep4.View.TerrariumDetails;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.FoodDispenser;
import com.abdu.and_sep4.Shared.Measurements;
import com.abdu.and_sep4.Shared.MeasurementsType;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Temperatur;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.View.Adapter.StockSparkAdapter;


import com.abdu.and_sep4.View.AddTerrarium.AddTerrariumFragmentViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.robinhood.spark.SparkView;


import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Single;


public class TerrariumDetailsFragment extends Fragment {


    private TextView terrariumName;
    private TextView terrariumCurrentTemp;
    private TextView textViewDate;
    private SparkView sparkView;
    private TextView terrariumTemp;
    private ArrayList<Measurements> temperaturArrayList = new ArrayList<>();

    private List<Temperatur> temperatureMeasurements = new ArrayList<>();
    private HubConnection hubConnection;
    private StockSparkAdapter stockSparkAdapter;
    private RadioGroup measurementRadioGroup;
    private RadioButton measurementTemp;
    private RadioButton measurementAir;
    private RadioButton measurementCo2;
    private Button animalBtn;
    private View inflate;

    private Button foodBtn;
    private Integer food = 1;

    private TerrariumDetailsFragmentViewModel viewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_terrarium_details, container, false);
        terrariumName = inflate.findViewById(R.id.tv_terrarium_name);
        terrariumCurrentTemp = inflate.findViewById(R.id.tv_current_temp);
        textViewDate = inflate.findViewById(R.id.tv_date);
        sparkView = inflate.findViewById(R.id.sparkview);
        terrariumTemp = inflate.findViewById(R.id.tv_temp);
        measurementRadioGroup = inflate.findViewById(R.id.measurements_radio);
        measurementTemp = inflate.findViewById(R.id.measurements_radio_temp);
        measurementAir = inflate.findViewById(R.id.measurements_radio_air);
        measurementCo2 = inflate.findViewById(R.id.measurements_radio_co2);
        animalBtn = inflate.findViewById(R.id.terrarium_animals);
        foodBtn = inflate.findViewById(R.id.addFood);

        Terrarium terrarium = SaveInfo.getInstance().getTerrarium();
        terrariumName.setText(terrarium.getTerrariumName());
        terrariumCurrentTemp.setText(Double.toString(20.1));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(terrarium.getTerrariumName());


        //  hubConnection = HubConnectionBuilder.create("https://terraeyes.azurewebsites.net/AppHub").build();



  /*          hubConnection.start().blockingGet();

            Single<List> temperatureDataFromDataToAndroid = hubConnection.invoke(List.class, "TemperatureDataFromDataToAndroid", "1");



            Log.e("signalr",temperatureDataFromDataToAndroid.blockingGet().toString());

            Gson gson = new Gson();

            Type temp = new TypeToken<ArrayList<TemperatureMeasurement>>(){}.getType();


            temperatureMeasurements = gson.fromJson(temperatureDataFromDataToAndroid.blockingGet().toString(), temp);
*/


        viewModel = new ViewModelProvider(this).get(TerrariumDetailsFragmentViewModel.class);


        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FoodDispenser addFood = new FoodDispenser(food,terrarium.getId());

                viewModel.addFood(addFood);


            }
        });




        viewModel.getTemperatureByTerrariumIdLiveData(terrarium.getId()).observe(getViewLifecycleOwner(), new Observer<List<Measurements>>() {
            @Override
            public void onChanged(List<Measurements> measurements) {
                List<Measurements> body = measurements;
                sparkView.setScrubEnabled(true);
                sparkView.setScrubListener(t -> {

                    if (t instanceof Measurements) {
                        updateInfoForDate((Measurements) t);
                    }
                });

                measurementRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (measurements!= null &&!measurements.isEmpty()){



                        if (i == R.id.measurements_radio_temp){
                            stockSparkAdapter.measurementsType = MeasurementsType.TEMPERATURE;
                            textViewDate.setText(measurements.get(measurements.size()-1).getMeasurements_date());
                            terrariumTemp.setText(Double.toString(measurements.get(measurements.size()-1).getMeasurement_temp()));
                            terrariumCurrentTemp.setText(Double.toString(measurements.get(measurements.size()-1).getMeasurement_temp()));

                        } else if (i == R.id.measurements_radio_air){
                            stockSparkAdapter.measurementsType = MeasurementsType.AIRHUMIDITY;
                            textViewDate.setText(measurements.get(measurements.size()-1).getMeasurements_date());
                            terrariumTemp.setText(Double.toString(measurements.get(measurements.size()-1).getMeasurement_air()));
                            terrariumCurrentTemp.setText(Double.toString(measurements.get(measurements.size()-1).getMeasurement_air()));

                        }
                        else {
                            stockSparkAdapter.measurementsType = MeasurementsType.CO2;
                            textViewDate.setText(measurements.get(measurements.size()-1).getMeasurements_date());
                            terrariumTemp.setText(Double.toString(measurements.get(measurements.size()-1).getMeasurement_co2()));
                            terrariumCurrentTemp.setText(Double.toString(measurements.get(measurements.size()-1).getMeasurement_co2()));

                        }
                        stockSparkAdapter.notifyDataSetChanged();
                    }
                    }
                });

                temperaturArrayList = (ArrayList<Measurements>) body;
                Log.e("test", "det virker");
                updateDisplayWithData(temperaturArrayList);
            }
        });



        animalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(inflate).navigate(R.id.action_terrariumDetailsFragment_to_animalListFragment);
            }
        });





        return inflate;
    }


    private void updateDisplayWithData(List<Measurements> dailyData) {


        stockSparkAdapter = new StockSparkAdapter(dailyData);

        sparkView.setAdapter(stockSparkAdapter);
        measurementTemp.setChecked(true);

        if (!dailyData.isEmpty()) {
            updateInfoForDate(dailyData.get(dailyData.size() - 1));
        } else {

            terrariumCurrentTemp.setText("temperature is not available for terrarium");
            textViewDate.setText("");
            terrariumTemp.setText("");
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        hubConnection.close();
    }

    private void updateInfoForDate(Measurements measurements) {

        if (stockSparkAdapter.measurementsType == MeasurementsType.CO2) {

            textViewDate.setText(measurements.getMeasurements_date());
            terrariumTemp.setText(Double.toString(measurements.getMeasurement_co2()));
            terrariumCurrentTemp.setText(Double.toString(measurements.getMeasurement_co2()));


        } else if (stockSparkAdapter.measurementsType == MeasurementsType.TEMPERATURE) {
            textViewDate.setText(measurements.getMeasurements_date());
            terrariumTemp.setText(Double.toString(measurements.getMeasurement_temp()));
            terrariumCurrentTemp.setText(Double.toString(measurements.getMeasurement_temp()));

        } else  {
            textViewDate.setText(measurements.getMeasurements_date());
            terrariumTemp.setText(Double.toString(measurements.getMeasurement_air()));
            terrariumCurrentTemp.setText(Double.toString(measurements.getMeasurement_air()));
        }


    }


}