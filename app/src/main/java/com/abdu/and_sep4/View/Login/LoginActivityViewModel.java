package com.abdu.and_sep4.View.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Repository.UserRepository;
import com.abdu.and_sep4.Shared.User;

public class LoginActivityViewModel extends ViewModel {

    private UserRepository userRepository;
    private TerrariumRepository terrariumRepository;
    private final MutableLiveData<Boolean> loading;



    public LoginActivityViewModel() {

        userRepository = UserRepository.getInstance();
        terrariumRepository = TerrariumRepository.getInstance();
        loading = new MutableLiveData<>(true);

    }


    public LiveData<User>validateUser(String username, String password){


        return userRepository.validateUser(username,password);


    }

//    public LiveData<User> addUser(User user){
//        return userRepository.addUser(user);
//    }

    public MutableLiveData<User> addUser(User user){
        return   terrariumRepository.addUser(() -> loading.setValue(false),user);
    }

    public LiveData<Boolean> loading() {
        return loading;
    }





}
