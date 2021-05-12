package com.girayserter.leaguefixtures.api;

import com.girayserter.leaguefixtures.models.Teams;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("833d3697-317e-4db7-a364-8472b00ef2b2")//URL extension for teams object
    Call<Teams> getTeams();
}
