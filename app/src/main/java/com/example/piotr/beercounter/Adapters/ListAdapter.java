package com.example.piotr.beercounter.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.piotr.beercounter.Beer;
import com.example.piotr.beercounter.Fragments.ArchiveFragment;
import com.example.piotr.beercounter.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<Beer> listBeer;
    private ArchiveFragment archiveFragment;
    private DatabaseReference mDatabase;

    public ListAdapter(List<Beer> beers, ArchiveFragment fragment) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        this.listBeer = beers;
        this.archiveFragment = fragment;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("BeerList");
    }
    @Override
    public int getItemCount() {
        return listBeer.size();
    }
    @Override
    public void onBindViewHolder(final ListViewHolder listViewHolder,int i) {
        final int actualIndex = i;
        listViewHolder.beer_item_tv.setText(listBeer.get(i).toString());
        listViewHolder.main_item_lay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              //  MainActivity.showDialog(archiveFragment, listBeer, actualIndex );
                archiveFragment.deleteBeer(listBeer.get(actualIndex).getKey());

            }
        });

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
        private LinearLayout main_item_lay;
        public ListViewHolder(final View v) {
            super(v);
            beer_item_tv = (TextView) v.findViewById(R.id.beer_item_tv);
            main_item_lay = (LinearLayout) v.findViewById(R.id.main_item_lay);
        }
    }


}
