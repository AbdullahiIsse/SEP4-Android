package com.abdu.and_sep4.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.User;
import com.abdu.and_sep4.Shared.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {


    private static UserRepository instance;

    private final MutableLiveData<User> userMutableLiveData;


    public UserRepository() {
        userMutableLiveData = new MutableLiveData<>();



    }

    public static synchronized UserRepository getInstance() {

        if (instance == null){
            instance = new UserRepository();
        }

        return instance;
    }


    public LiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }


    public LiveData<User>validateUser(String username, String password){

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<User> call = terrariumApi.validateUser(username,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.code() == 404){
                    userMutableLiveData.setValue(null);
                }

                if (response.isSuccessful()){
                    Log.e("test","det virker");
                    userMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Retrofit", t.getMessage());
            }
        });


        return userMutableLiveData;

    }









}
