package com.abdu.and_sep4.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.abdu.and_sep4.Shared.Animal;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

@Dao
public interface AnimalDao {

    @Query("select * from animal_table where eui = :eui")
    LiveData<List<Animal>> getAnimalsByEui(String eui);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAnimal(Animal animal);





}
