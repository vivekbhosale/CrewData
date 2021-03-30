package com.example.juststream.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CrewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Crew crew);

    @Query("select * from crew")
    List<Crew> getdata();

    @Delete
    void delete(List<Crew> crewList);
}
