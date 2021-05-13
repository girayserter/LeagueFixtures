package com.girayserter.leaguefixtures.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.girayserter.leaguefixtures.models.Match;
import com.girayserter.leaguefixtures.repository.Repository;

import java.util.List;

public class FixturesViewModel extends AndroidViewModel {
    private final Repository repo;
    private LiveData<List<Match>> fixture;
    private LiveData<Integer> weekAmount;

    public FixturesViewModel(@NonNull Application application) {
        super(application);
        repo = new Repository(application);
    }

    public LiveData<List<Match>> getWeekMatches(int week) {
        fixture = repo.getWeekMatches(week);
        return fixture;
    }

    public LiveData<Integer> getWeekAmount() {
        weekAmount = repo.getWeekAmount();
        return weekAmount;
    }
}
