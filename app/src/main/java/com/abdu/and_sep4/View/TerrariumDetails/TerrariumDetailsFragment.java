package com.abdu.and_sep4.View.TerrariumDetails;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Temperatur;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.View.Adapter.StockSparkAdapter;
import com.robinhood.spark.SparkView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TerrariumDetailsFragment extends Fragment {


    private TextView terrariumName;
    private TextView terrariumCurrentTemp;
    private TextView textViewDate;
    private SparkView sparkView;
    private TextView terrariumTemp;
    private ArrayList<Temperatur> temperaturArrayList = new ArrayList<>();
    private TerrariumDetailsFragmentViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_terrarium_details, container, false);

        terrariumName = inflate.findViewById(R.id.tv_terrarium_name);
        terrariumCurrentTemp = inflate.findViewById(R.id.tv_current_temp);
        textViewDate = inflate.findViewById(R.id.tv_date);
        sparkView = inflate.findViewById(R.id.sparkview);
        terrariumTemp = inflate.findViewById(R.id.tv_temp);

        Terrarium terrarium = SaveInfo.getInstance().getTerrarium();
        terrariumName.setText(terrarium.getTerrariumName());
        terrariumCurrentTemp.setText(Double.toString(20.1));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(terrarium.getTerrariumName());


        viewModel = new ViewModelProvider(this).get(TerrariumDetailsFragmentViewModel.class);


        viewModel.getTemperatureByTerrariumIdLiveData(terrarium.getId()).observe(getViewLifecycleOwner(), new Observer<List<Temperatur>>() {
            @Override
            public void onChanged(List<Temperatur> temperaturs) {
                List<Temperatur> body = temperaturs;
                Collections.reverse(body);
                sparkView.setScrubEnabled(true);
                sparkView.setScrubListener(t -> {

                    if (t instanceof Temperatur) {
                        updateInfoForDate((Temperatur) t);
                    }
                });
                temperaturArrayList = (ArrayList<Temperatur>) body;
                Log.e("test","det virker");
                updateDisplayWithData(temperaturArrayList);


            }
        });

        return inflate;
    }


    private void updateDisplayWithData(List<Temperatur> dailyData) {


        StockSparkAdapter stockSparkAdapter = new StockSparkAdapter(dailyData);

        sparkView.setAdapter(stockSparkAdapter);

        if (!dailyData.isEmpty()) {
            updateInfoForDate(dailyData.get(dailyData.size() - 1));
        } else {


            terrariumCurrentTemp.setText("temperature is not available for terrarium");
            textViewDate.setText("");
            terrariumTemp.setText("");


        }


    }

    private void updateInfoForDate(Temperatur temperatur) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        textViewDate.setText(temperatur.getTemperatureDate());
        terrariumTemp.setText(Double.toString(temperatur.getTemperatureCelsius()));
        terrariumCurrentTemp.setText(Double.toString(temperatur.getTemperatureCelsius()));

    }


}