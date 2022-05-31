package com.abdu.and_sep4.View.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.Web.UserRepo.UserRepository;
import com.abdu.and_sep4.Repository.Web.UserRepo.UserRepositoryImpl;
import com.abdu.and_sep4.Shared.User;

public class LoginActivityViewModel extends ViewModel implements ILoginActivity {

    private UserRepository userRepository;



    public LoginActivityViewModel() {

        userRepository = UserRepositoryImpl.getInstance();


    }


    public LiveData<User> addUser(User user){
        return userRepository.addUser(user);
    }







}
