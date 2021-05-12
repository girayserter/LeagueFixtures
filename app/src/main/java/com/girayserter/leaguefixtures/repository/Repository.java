package com.girayserter.leaguefixtures.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.girayserter.leaguefixtures.api.ApiInterface;
import com.girayserter.leaguefixtures.api.ApiUrl;
import com.girayserter.leaguefixtures.database.Match;
import com.girayserter.leaguefixtures.database.MatchesDao;
import com.girayserter.leaguefixtures.database.MatchesDatabase;
import com.girayserter.leaguefixtures.models.Team;
import com.girayserter.leaguefixtures.models.Teams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    public Repository(Application application){
        MatchesDatabase database= MatchesDatabase.getInstance(application);
        matchesDao =database.matchesDao();
    }

    public List<Team> teamList;
    private MatchesDao matchesDao;

    /**
     * Creates LiveData of teams from API
     */
    public MutableLiveData<List<Team>> getTeams(){
        final MutableLiveData<List<Team>> mutableLiveData = new MutableLiveData<>();
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Teams> call = api.getTeams();
        call.enqueue(new Callback<Teams>() {
            /**
             *On success, create LiveData of teamList
             */
            @Override
            public void onResponse(Call<Teams> call, Response<Teams> response) {
                teamList = response.body().getTeams();
                mutableLiveData.setValue(teamList);
            }

            @Override
            public void onFailure(Call<Teams> call, Throwable t) {
                Log.d("Failure: ", t.getMessage().toString());
            }
        });
        return mutableLiveData;
    }

    public void insertMatch(Match match){
        matchesDao.insertMatch(match);
    }

    public void insertMatches(ArrayList<Match> matches){
        matchesDao.insertMatches(matches);
    }

    public void deleteAllMatches(){
        matchesDao.deleteAllMatches();
    }

    public LiveData<List<Match>> getWeekMatches(int week){
        return matchesDao.getWeekMatches(week);
    }
}
