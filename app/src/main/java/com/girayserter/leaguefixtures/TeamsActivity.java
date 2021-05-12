package com.girayserter.leaguefixtures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.girayserter.leaguefixtures.adapters.TeamsAdapter;
import com.girayserter.leaguefixtures.databinding.ActivityTeamsBinding;
import com.girayserter.leaguefixtures.models.Team;
import com.girayserter.leaguefixtures.viewmodels.TeamsViewModel;;

import java.util.List;

public class TeamsActivity extends AppCompatActivity {

    ActivityTeamsBinding binding;
    TeamsAdapter adapter;
    List<Team> teams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_teams);

        binding.recyclerViewTeams.setHasFixedSize(true);
        binding.recyclerViewTeams.setLayoutManager(new LinearLayoutManager(this));

        adapter=new TeamsAdapter();
        binding.recyclerViewTeams.setAdapter(adapter);

        TeamsViewModel teamsViewModel=new TeamsViewModel();
        teamsViewModel.getTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teamList) {
                adapter.addTeamList(teamList);
                adapter.notifyDataSetChanged();
                teams=teamList;
            }
        });

        binding.buttonDrawFixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeamsActivity.this,"sdfasf",Toast.LENGTH_LONG).show();

            }
        });
    }
}