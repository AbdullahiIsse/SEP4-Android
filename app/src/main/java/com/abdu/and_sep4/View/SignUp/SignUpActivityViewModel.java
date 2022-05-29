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
    private TerrariumRepository terrariumRepository;
    private final MutableLiveData<Boolean> loading;



    public SignUpActivityViewModel() {

        userRepository = UserRepository.getInstance();
        terrariumRepository = TerrariumRepository.getInstance();
        loading = new MutableLiveData<>(true);


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
