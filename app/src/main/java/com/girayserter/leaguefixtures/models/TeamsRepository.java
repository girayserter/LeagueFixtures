package com.girayserter.leaguefixtures.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.girayserter.leaguefixtures.api.ApiInterface;
import com.girayserter.leaguefixtures.api.ApiUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamsRepository {
    public List<Team> teamList;

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
}
