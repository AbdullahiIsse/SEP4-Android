package com.abdu.and_sep4.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.Shared.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TerrariumRepository {

    private static TerrariumRepository instance;

    private final MutableLiveData<List<Terrarium>> terrariumMutableLiveData;

    public TerrariumRepository() {
        terrariumMutableLiveData = new MutableLiveData<>();



    }

    public static synchronized TerrariumRepository getInstance() {

        if (instance == null){
            instance = new TerrariumRepository();
        }

        return instance;
    }

    public MutableLiveData<List<Terrarium>> getTerrariumMutableLiveData() {
        return terrariumMutableLiveData;
    }




    public LiveData<List<Terrarium>> getTerrariumByUserId(int userid){

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Terrarium>> call = terrariumApi.getTerrariumByUserId(userid);

        call.enqueue(new Callback<List<Terrarium>>() {
            @Override
            public void onResponse(Call<List<Terrarium>> call, Response<List<Terrarium>> response) {

                if (response.isSuccessful()){

                    terrariumMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Terrarium>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Terrarium by user id :(");

            }
        });


       return terrariumMutableLiveData;


    }
}
