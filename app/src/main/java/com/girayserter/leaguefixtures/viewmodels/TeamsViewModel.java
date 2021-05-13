package com.girayserter.leaguefixtures.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.girayserter.leaguefixtures.models.Match;
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

    public void insertMatches(){
        deleteAllMatches();
        repository.insertMatches(getRecord());
    }

    public void deleteAllMatches(){
        repository.deleteAllMatches();
    }

    /**
     * This algorithm calculates league matches.
     * First element of array stands fixed,
     * last element comes to second place,
     * rest of the elements swipe 1 index up.
     */
    public ArrayList<Match> getRecord() {
        List<Team> teams=teamList.getValue();//Get teams from teamList
        ArrayList<Match> matches =new ArrayList<>();//ArrayList that will keep all league fixture

        int week=1;
        int half=1;
        int[] teamNumbers=new int[teams.size()];//Array to keep teamId
        int[] ids;
        //Add a number for each team
        for (int i=1;i<=teams.size();i++){
            teamNumbers[i-1]=i;
        }

        //If there is odd number of teams add one more which had id "0"
        if (teamNumbers.length % 2 != 0)
        {
            ids = new int[teamNumbers.length+1];
            for(int i=0;i<teamNumbers.length ; i++)
            {
                ids[i] = teamNumbers[i];
            }
            ids[ids.length - 1] = 0;
        }
        else
        {
            ids = new int[teamNumbers.length];
            for (int i = 0; i < teamNumbers.length; i++)
            {
                ids[i] = teamNumbers[i];
            }
        }

        int[][] placement = new int[ids.length/2][2];//Represents a week of league, holds teams as 2D array
        //Changes Home and Away for second half of league
        for(;half<3;half++) {
            //There should be (teamAmount-1) week to all teams match with each other
            for (int k = 0; k < ids.length - 1; k++) {
                //Places teams as "U" shape to placement array
                for (int i = 0; i < 2; i++) {
                    if (i % 2 == 0) {
                        for (int j = 0; j < ids.length / 2; j++) {
                            placement[j][i] = ids[i * ids.length / 2 + j];
                        }
                    } else {
                        for (int j = ids.length / 2 - 1; j >= 0; j--) {
                            placement[j][i] = ids[i * ids.length / 2 + ids.length / 2 - 1 - j];
                        }
                    }
                }
                //Creates Match object for each row of array
                for (int i = 0; i < ids.length / 2; i++) {
                    Match match = new Match();
                    for (int j = 0; j < 2; j++) {
                        if (half == 1) {
                            if (j == 0) {
                                match.setHome(teams.get(placement[i][j] - 1).getTeamName());
                            } else {
                                match.setAway(teams.get(placement[i][j] - 1).getTeamName());
                            }
                        } else if (half == 2) {
                            if (j == 0) {
                                match.setAway(teams.get(placement[i][j] - 1).getTeamName());
                            } else {
                                match.setHome(teams.get(placement[i][j] - 1).getTeamName());
                            }
                        }
                    }
                    match.setWeek(week);
                    match.setHalf(half);
                    matches.add(match);
                }
                week++;
                //Keep item at index 0 still, bring last item in ids array to index 1 then swipe rest to 1 index up
                int last = ids[ids.length - 1];
                for (int i = ids.length - 2; i > 0; i--) {
                    ids[i + 1] = ids[i];
                }
                ids[1] = last;
            }
        }
        return matches;
    }
}
