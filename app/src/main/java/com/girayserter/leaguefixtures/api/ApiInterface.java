package com.girayserter.leaguefixtures.api;

import com.girayserter.leaguefixtures.models.Teams;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    /*@GET("833d3697-317e-4db7-a364-8472b00ef2b2")
//URL extension for teams object
    Call<Teams> getTeams();*/


    @GET("2932703f-188f-4349-9404-17f4bc10be1f")
    //URL extension for teams object
    Call<Teams> getTeams();
}
