package com.abdu.and_sep4.View.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.UserRepository;
import com.abdu.and_sep4.Shared.User;
import com.abdu.and_sep4.Shared.UserResponse;

public class LoginActivityViewModel extends ViewModel {

    private UserRepository userRepository;



    public LoginActivityViewModel() {

        userRepository = UserRepository.getInstance();

    }


    public LiveData<User>validateUser(String username, String password){


        return userRepository.validateUser(username,password);


    }






}
