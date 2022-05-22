package com.abdu.and_sep4.API;

import com.abdu.and_sep4.Shared.Measurements;
import com.abdu.and_sep4.Shared.Pet;
import com.abdu.and_sep4.Shared.Temperatur;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.Shared.User;
import com.abdu.and_sep4.Shared.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TerrariumApi {

   @GET("user/validate/{username}/{password}")
    Call<User> validateUser(@Path("username") String username, @Path("password") String password);


   @GET("terrarium/user/{id}")
    Call<List<Terrarium>>getTerrariumByUserId(@Path("id") int id);

   @POST("terrarium")
   Call<Terrarium> addTerrarium(@Body Terrarium terrarium);

    @DELETE("terrarium/{id}")
    Call<Terrarium> deleteTerrarium(@Path("id") long id);

    @PATCH("terrarium/{id}")
    Call<Terrarium> updateTerrarium(@Path("id") long id,@Body Terrarium terrarium);

   @GET("measurements/{id}")
    Call<List<Measurements>>getMeasurementsByTerrariumId(@Path("id") int id);

    //addPet
    @POST("pet")
    Call<Pet> addPet(@Body Pet pet);


    //GetPetList
    @GET("pet/{id}")
    Call<List<Pet>>getPets(@Path("id") long id);

    @DELETE("pet/{id}")
    Call<Pet> deletePet(@Path("id") long id);


    @PATCH("pet/{id}")
    Call<Pet> updatePet(@Path("id") long id,@Body Pet pet);






}
