package com.girayserter.leaguefixtures.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.girayserter.leaguefixtures.models.Team;
import com.girayserter.leaguefixtures.models.TeamsRepository;

import java.util.List;

public class TeamsViewModel {
    private TeamsRepository teamsRepository;
    private MutableLiveData<List<Team>> mutableLiveData=null;


    public TeamsViewModel(){
        teamsRepository=new TeamsRepository();
    }

    /**
     * Returns teams data from repository
     */
    public LiveData<List<Team>> getTeams(){
        if(mutableLiveData==null){
            mutableLiveData=teamsRepository.getTeams();
        }
        return mutableLiveData;
    }
}
