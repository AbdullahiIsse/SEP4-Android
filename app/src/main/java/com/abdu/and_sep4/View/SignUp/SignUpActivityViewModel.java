package com.abdu.and_sep4.View.SignUp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Repository.UserRepository;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.User;

import java.util.List;

public class SignUpActivityViewModel extends ViewModel {

    private UserRepository userRepository;




    public SignUpActivityViewModel() {

        userRepository = UserRepository.getInstance();



    }



    public LiveData<User> addUser(User user){
        return userRepository.addUser(user);
    }














}
