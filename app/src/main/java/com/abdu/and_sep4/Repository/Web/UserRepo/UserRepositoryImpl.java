package com.abdu.and_sep4.Repository.Web.UserRepo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository  {


    private static UserRepositoryImpl instance;

    private final MutableLiveData<User> userMutableLiveData;

    private final MutableLiveData<User> addUserMutableLiveData;


    private UserRepositoryImpl() {
        userMutableLiveData = new MutableLiveData<>();
        addUserMutableLiveData = new MutableLiveData<>();


    }

    public static synchronized UserRepositoryImpl getInstance() {

        if (instance == null){
            instance = new UserRepositoryImpl();
        }

        return instance;
    }


    public LiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }



    public LiveData<User> addUser(User user){
        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<User> call = terrariumApi.addUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    addUserMutableLiveData.setValue(response.body());
                    Log.e("Retrofit", "working adding a user :(");

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong adding a user :(");
            }
        });


        return addUserMutableLiveData;

    }








}
