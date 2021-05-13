package com.girayserter.leaguefixtures;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.girayserter.leaguefixtures.adapters.ViewPagerAdapter;
import com.girayserter.leaguefixtures.models.Match;
import com.girayserter.leaguefixtures.databinding.ActivityFixturesBinding;
import com.girayserter.leaguefixtures.viewmodels.FixturesViewModel;

import java.util.List;

public class FixturesActivity extends AppCompatActivity {

    ActivityFixturesBinding binding;
    ViewPagerAdapter adapter;
    List<Match> matches;
    FixturesViewModel fixturesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fixtures);

        adapter = new ViewPagerAdapter(this);
        binding.viewPager2.setAdapter(adapter);
        fixturesViewModel = new ViewModelProvider(this).get(FixturesViewModel.class);
        fixturesViewModel.getWeekAmount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer amount) {
                adapter.setPageAmount(amount);
                adapter.notifyDataSetChanged();
            }
        });

        /**
         * Change matches of week by view page change
         */
        adapter.pageNumber.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer page) {
                fixturesViewModel.getWeekMatches(page + 1).observe(FixturesActivity.this, new Observer<List<Match>>() {
                    @Override
                    public void onChanged(List<Match> matchList) {
                        adapter.addMatchList(matchList);
                        adapter.notifyDataSetChanged();
                        matches = matchList;
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_change_theme:
                switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
                    case Configuration.UI_MODE_NIGHT_YES:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case Configuration.UI_MODE_NIGHT_NO:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}