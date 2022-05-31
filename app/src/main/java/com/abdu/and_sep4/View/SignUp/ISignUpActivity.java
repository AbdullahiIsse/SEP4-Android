package com.abdu.and_sep4.View.SignUp;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.User;

public interface ISignUpActivity {

    LiveData<User> addUser(User user);
}
