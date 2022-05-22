package com.abdu.and_sep4.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TerrariumRepository {

    private static TerrariumRepository instance;

    private final MutableLiveData<List<Terrarium>> terrariumListMutableLiveData;
    private final MutableLiveData<Terrarium> terrariumMutableLiveData;

    public TerrariumRepository() {
        terrariumListMutableLiveData = new MutableLiveData<>();
        terrariumMutableLiveData = new MutableLiveData<>();



    }

    public static synchronized TerrariumRepository getInstance() {

        if (instance == null){
            instance = new TerrariumRepository();
        }

        return instance;
    }

    public MutableLiveData<List<Terrarium>> getTerrariumListMutableLiveData() {
        return terrariumListMutableLiveData;
    }






    public LiveData<List<Terrarium>> getTerrariumByUserId(int userid){

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Terrarium>> call = terrariumApi.getTerrariumByUserId(userid);

        call.enqueue(new Callback<List<Terrarium>>() {
            @Override
            public void onResponse(Call<List<Terrarium>> call, Response<List<Terrarium>> response) {

                if (response.isSuccessful()){

                    terrariumListMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Terrarium>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Terrarium by user id :(");

            }
        });

       return terrariumListMutableLiveData;

    }

    public LiveData<Terrarium> addTerrarium(Terrarium terrarium){
        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Terrarium> call = terrariumApi.addTerrarium(terrarium);

        call.enqueue(new Callback<Terrarium>() {
            @Override
            public void onResponse(Call<Terrarium> call, Response<Terrarium> response) {
                if(response.isSuccessful()) {
                    terrariumMutableLiveData.setValue(response.body());
                    Log.e("Retrofit", "working adding terrarium :(");

                }
            }

            @Override
            public void onFailure(Call<Terrarium> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong adding Terrarium :(");
            }
        });



        return terrariumMutableLiveData;

    }








}
