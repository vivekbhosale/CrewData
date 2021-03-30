package com.example.juststream.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Crew.class}, version = 1 )
public abstract class CrewDatabase extends RoomDatabase {
    private static CrewDatabase INSTANCE;
    public abstract CrewDao crewDao();

    public static CrewDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CrewDatabase.class, "Database")
                    .allowMainThreadQueries()
                    .build();

            return INSTANCE;
        }

        return INSTANCE;
    }

}
