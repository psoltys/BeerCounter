package com.example.piotr.beercounter.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.piotr.beercounter.Adapters.ListAdapter;
import com.example.piotr.beercounter.Beer;
import com.example.piotr.beercounter.BeerCursorAdapter;
import com.example.piotr.beercounter.R;

import java.util.List;

public class ArchiveFragment extends Fragment {

    public static ArchiveFragment newInstance(Beer beer) {
        ArchiveFragment archiveFragment = new ArchiveFragment();
        if (beer != null) {
            Bundle args = new Bundle();
            args.putSerializable("beer", beer);
            archiveFragment.setArguments(args);
        }
        return archiveFragment;
    }


    public ArchiveFragment(){}
    private BeerCursorAdapter beerDB;
    private RecyclerView beerRec;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.archive_fragment, container, false);
        final Activity activity = getActivity();

        beerRec = (RecyclerView) view.findViewById(R.id.beerRec);
        LinearLayoutManager llm = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
        beerRec.setLayoutManager(llm);

        if (getArguments() != null) {
            Beer beers = (Beer) getArguments().getSerializable("beer");

            beerDB = new BeerCursorAdapter(activity);
            beerDB.open();
            beerDB.addBeers(beers);

            beerRec.setAdapter(new ListAdapter(beerDB.getAllBeers(),ArchiveFragment.this));
//            List<Beer> values = beerDB.getAllBeers();
//
//            ArrayAdapter<Beer> adapter = new ArrayAdapter<Beer>(this, android.R.layout.simple_list_item_1, values);
//            setListAdapter(adapter);
//
//            adapter.add(beers);
//            adapter.notifyDataSetChanged();
//
        }
        else{
            beerDB = new BeerCursorAdapter(activity);
            beerDB.open();
            beerRec.setAdapter(new ListAdapter(beerDB.getAllBeers(), ArchiveFragment.this));
        }
        return view;
    }
    public void deleteBeer(long id){
        beerDB.deleteBeerById(Long.toString(id));
        beerRec.setAdapter(new ListAdapter(beerDB.getAllBeers(), ArchiveFragment.this));
    }
    @Override
    public void onResume() {
        beerDB.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        beerDB.close();
        super.onPause();
    }

}