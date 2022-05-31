package com.abdu.and_sep4.Dao;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abdu.and_sep4.Shared.Animal;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;

@Database(entities = {Terrarium.class,TemperatureMeasurement.class,HumidityMeasurement.class,Co2Measurement.class,Animal.class}, version = 2)
public abstract class TerrariumDatabase extends RoomDatabase {

    private static TerrariumDatabase instance;

    public abstract TerrariumDao terrariumDao();
    public abstract MeasurementDao measurementDao();
    public abstract AnimalDao animalDao();



    public static synchronized TerrariumDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                            TerrariumDatabase.class, "terrarium_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
