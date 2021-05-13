package com.girayserter.leaguefixtures.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.girayserter.leaguefixtures.R;
import com.girayserter.leaguefixtures.models.Match;
import com.girayserter.leaguefixtures.databinding.ListItemFixtureBinding;

import java.util.ArrayList;
import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MyViewHolder> {

    private List<Match> matchList;


    public MatchesAdapter() {
        matchList = new ArrayList<>();
    }

    public void addMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    @Override
    public int getItemCount() {
        return matchList != null ? matchList.size() : 0;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemFixtureBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_fixture, parent, false);
        return new MyViewHolder(binding);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final ListItemFixtureBinding binding;

        MyViewHolder(ListItemFixtureBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setMatchModel(matchList.get(position));
    }
}
