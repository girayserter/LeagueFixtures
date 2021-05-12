package com.girayserter.leaguefixtures.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public interface MatchesDao {
    // Returns the number of matches inserted.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<List<Long>> insertMatches(List<Match> matches);

    // Returns the number of matches inserted.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Long> insertMatch(Match fixtures);

    //Deletes All Matches from table
    @Query("DELETE FROM matches")
    void deleteAllMatches();

    //Gets matches at given week number
    @Query("SELECT * FROM matches WHERE week=:week ORDER BY id ASC")
    LiveData<List<Match>> getWeekMatches(int week);
}
