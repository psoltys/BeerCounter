package com.example.piotr.beercounter.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piotr.beercounter.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    public ListAdapter() {

    }
    @Override
    public int getItemCount() {
        return 4;
    }
    @Override
    public void onBindViewHolder(final ListViewHolder listViewHolder,int i) {

    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_item, viewGroup, false);
        return new ListViewHolder(itemView);
    }
    public static class ListViewHolder extends RecyclerView.ViewHolder {

        public ListViewHolder(final View v) {
            super(v);

        }
    }
}
