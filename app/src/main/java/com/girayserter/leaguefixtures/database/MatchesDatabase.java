package com.girayserter.leaguefixtures.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Match.class},version = 1)
public abstract class MatchesDatabase extends RoomDatabase {

    public abstract MatchesDao matchesDao();

    private static final String DB_NAME = "Fixtures.db";
    private static volatile MatchesDatabase instance;

    public static synchronized MatchesDatabase getInstance(Context context){
        if(instance == null){
            instance = create(context);
        }
        return instance;
    }

    private static MatchesDatabase create(final Context context){
        return Room.databaseBuilder(
                context,
                MatchesDatabase.class,
                DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

}
