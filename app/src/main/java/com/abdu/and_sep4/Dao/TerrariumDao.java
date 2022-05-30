package com.abdu.and_sep4.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

@Dao
public interface TerrariumDao {

    @Query("select * from terrarium_table where userId = :userId ")
    LiveData<List<Terrarium>> getTerrariumByUserId(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTerrarium(Terrarium terrarium);



}
