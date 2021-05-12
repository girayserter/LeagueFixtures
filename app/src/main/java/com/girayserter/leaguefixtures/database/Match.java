package com.girayserter.leaguefixtures.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "matches")
public class Match {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "half")
    private int half;

    @ColumnInfo(name = "week")
    private int week;

    @ColumnInfo(name = "home")
    private String home;

    @ColumnInfo(name = "away")
    private String away;

    public int getHalf() {
        return half;
    }

    public void setHalf(int half) {
        this.half = half;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }
}
