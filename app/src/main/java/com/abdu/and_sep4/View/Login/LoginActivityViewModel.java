package com.abdu.and_sep4.View.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Repository.UserRepository;
import com.abdu.and_sep4.Shared.User;

public class LoginActivityViewModel extends ViewModel {

    private UserRepository userRepository;



    public LoginActivityViewModel() {

        userRepository = UserRepository.getInstance();


    }


    public LiveData<User> addUser(User user){
        return userRepository.addUser(user);
    }







}
