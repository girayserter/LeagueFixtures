package com.girayserter.leaguefixtures.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {//Every Team has 2 attributes, TeamId and TeamName
    @SerializedName("teamId")
    @Expose
    private String teamId;
    @SerializedName("teamName")
    @Expose
    private String teamName;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
