package com.girayserter.leaguefixtures.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.girayserter.leaguefixtures.R;
import com.girayserter.leaguefixtures.models.Match;
import com.girayserter.leaguefixtures.databinding.ItemViewpagerBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private List<Match> matchList;
    private int pageAmount;
    private ViewPager2 viewPager2;
    private MatchesAdapter matchesAdapter;
    public MutableLiveData<Integer> pageNumber=new MutableLiveData<>();
    Context context;


    public ViewPagerAdapter(Context context) {
        this.matchList = new ArrayList<>();
        this.viewPager2 = viewPager2;
        this.context=context;
        matchesAdapter=new MatchesAdapter();
        pageNumber.setValue(5);
    }

    public void addMatchList(List<Match> matchList){
        this.matchList=matchList;
    }

    public void setPageAmount(int pageAmount){
        this.pageAmount=pageAmount;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewpagerBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_viewpager,
                parent,
                false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setMatchModel(matchList.get(0));

        if(pageNumber.getValue()!=position){
            pageNumber.setValue(position);
        }

        holder.binding.rvMatches.setAdapter(matchesAdapter);
        holder.binding.rvMatches.setHasFixedSize(true);
        holder.binding.rvMatches.setLayoutManager(new LinearLayoutManager(context));
        matchesAdapter.addMatchList(matchList);
        matchesAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        //Keeps how many pages there will be
        return pageAmount;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemViewpagerBinding binding;

        ViewHolder(ItemViewpagerBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
    }

}
