package com.abdu.and_sep4.API;

import com.abdu.and_sep4.Shared.Animal;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.Shared.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TerrariumApi {

    //User

    @POST("user")
    Call<User> addUser(@Body User user);

    //Terrarium
    @GET("terrarium/{userId}")
    Call<List<Terrarium>>getTerrariumByUserId(@Path("userId") String userId);

    @POST("terrarium")
    Call<Terrarium> addTerrarium(@Body Terrarium terrarium);


    @PATCH("terrarium/{eui}")
    Call<Terrarium> updateTerrarium(@Path("eui") String eui, @Body Terrarium terrarium);


    ///Measurement

    //Temp
    @GET("temperatures/{userId}/{eui}")
    Call<List<TemperatureMeasurement>>getTemperatureMeasurementByUserId(@Path("userId") String UserId,@Path("eui") String eui);

     //Humidity
    @GET("humidity/{userId}/{eui}")
    Call<List<HumidityMeasurement>>getHumidityMeasurementByUserId(@Path("userId") String UserId,@Path("eui") String eui);

     // CO2
    @GET("carbondioxides/{userId}/{eui}")
    Call<List<Co2Measurement>>getCo2MeasurementByUserId(@Path("userId") String UserId,@Path("eui") String eui);



    //Animals
    @POST("animals")
    Call<Animal> addAnimal(@Body Animal animal);

    @GET("animals/{userId}/{eui}")
    Call<List<Animal>> getAnimalByUserId(@Path("userId") String userId,@Path("eui") String eui);



    @PATCH("animals/{id}")
    Call<Animal> updateAnimal(@Path("id") int id,@Body Animal animal);





}
