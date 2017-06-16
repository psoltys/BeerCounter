package com.example.piotr.beercounter.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.piotr.beercounter.Beer;
import com.example.piotr.beercounter.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<Beer> listBeer;
    public ListAdapter(List<Beer> beers) {
        this.listBeer = beers;
    }
    @Override
    public int getItemCount() {
        return listBeer.size();
    }
    @Override
    public void onBindViewHolder(final ListViewHolder listViewHolder,int i) {
        listViewHolder.beer_item_tv.setText(listBeer.get(i).toString());
    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_item, viewGroup, false);
        return new ListViewHolder(itemView);
    }
    public static class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView beer_item_tv;
        public ListViewHolder(final View v) {
            super(v);
            beer_item_tv = (TextView) v.findViewById(R.id.beer_item_tv);

        }
    }
}
