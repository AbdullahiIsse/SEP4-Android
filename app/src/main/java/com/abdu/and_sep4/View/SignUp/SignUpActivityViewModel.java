package com.abdu.and_sep4.View.SignUp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.Web.UserRepo.UserRepository;
import com.abdu.and_sep4.Repository.Web.UserRepo.UserRepositoryImpl;
import com.abdu.and_sep4.Shared.User;

public class SignUpActivityViewModel extends ViewModel implements ISignUpActivity {

    private UserRepository userRepository;




    public SignUpActivityViewModel() {

        userRepository = UserRepositoryImpl.getInstance();



    }



    public LiveData<User> addUser(User user){
        return userRepository.addUser(user);
    }














}
