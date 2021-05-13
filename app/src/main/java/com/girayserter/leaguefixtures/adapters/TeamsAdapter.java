package com.girayserter.leaguefixtures.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.girayserter.leaguefixtures.R;
import com.girayserter.leaguefixtures.databinding.ListItemTeamBinding;
import com.girayserter.leaguefixtures.models.Team;
import com.girayserter.leaguefixtures.models.Teams;

import java.util.ArrayList;
import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.MyViewHolder> {

    private List<Team> teamList;


    public TeamsAdapter() {
        teamList = new ArrayList<>();
    }

    public void addTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    @Override
    public int getItemCount() {
        return teamList != null ? teamList.size() : 0;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemTeamBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_team, parent, false);
        return new MyViewHolder(binding);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final ListItemTeamBinding binding;

        MyViewHolder(ListItemTeamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setTeamModel(teamList.get(position));
    }
}