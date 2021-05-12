package com.girayserter.leaguefixtures.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.girayserter.leaguefixtures.database.Match;
import com.girayserter.leaguefixtures.models.Team;
import com.girayserter.leaguefixtures.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class TeamsViewModel extends AndroidViewModel {
    private Repository repository;
    private MutableLiveData<List<Team>> teamList=null;
    private LiveData<List<Match>> fixture;


    public TeamsViewModel(@NonNull Application application){
        super(application);
        repository =new Repository(application);
    }

    /**
     * Returns teams data from repository
     */
    public LiveData<List<Team>> getTeams(){
        if(teamList==null){
            teamList= repository.getTeams();
        }
        return teamList;
    }

    public LiveData<List<Match>> getWeekMatches(int week){
        fixture= repository.getWeekMatches(week);
        return fixture;
    }

    public void insertMatches(ArrayList<Match> matches){
        repository.insertMatches(getRecord());
    }

    public void insertMatch(Match match){
        repository.insertMatch(match);
    }

    public void deleteAllMatches(){
        repository.deleteAllMatches();
    }

    /**
     * Will include algorithm that calculates match fixture
     */
    public ArrayList<Match> getRecord() {
        return null;
    }
}
