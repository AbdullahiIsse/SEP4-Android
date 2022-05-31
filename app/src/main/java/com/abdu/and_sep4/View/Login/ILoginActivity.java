package com.abdu.and_sep4.View.Login;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.User;

public interface ILoginActivity {

    LiveData<User> addUser(User user);
}
