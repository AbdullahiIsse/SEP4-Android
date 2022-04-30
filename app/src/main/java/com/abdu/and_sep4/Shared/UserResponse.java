package com.abdu.and_sep4.Shared;

public class UserResponse {


    private String username;
    private String password;



    public User getUser(String username,String password){
        return new User(username,password);
    }









}
