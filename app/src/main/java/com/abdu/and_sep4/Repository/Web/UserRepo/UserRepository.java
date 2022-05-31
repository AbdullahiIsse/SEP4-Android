package com.abdu.and_sep4.Repository.Web.UserRepo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.User;

public interface UserRepository {

    LiveData<User> getUserMutableLiveData();

    LiveData<User> addUser(User user);

}
