package com.girayserter.leaguefixtures.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Teams {
    @SerializedName("teams")
    @Expose
    private List<Team> teams=null;//Teams has Team list

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
