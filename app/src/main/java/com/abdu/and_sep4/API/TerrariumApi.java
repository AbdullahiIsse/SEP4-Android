package com.abdu.and_sep4.API;

import com.abdu.and_sep4.Shared.Temperatur;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.Shared.User;
import com.abdu.and_sep4.Shared.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TerrariumApi {

   @GET("user/validate/{username}/{password}")
    Call<User> validateUser(@Path("username") String username, @Path("password") String password);


   @GET("terrarium/user/{id}")
    Call<List<Terrarium>>getTerrariumByUserId(@Path("id") int id);


   @GET("temperature/{id}")
    Call<List<Temperatur>>getTemperatureByTerrariumId(@Path("id") int id);














}
