package com.example.piotr.beercounter.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.piotr.beercounter.Beer;
import com.example.piotr.beercounter.Borrow;
import com.example.piotr.beercounter.Food;
import com.example.piotr.beercounter.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


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
    private ArrayList<Beer> beerDB = new ArrayList<>();
    private ListView beerList;
    private RecyclerView beerRec;
    private DatabaseReference mDatabase;
    AlertDialog.Builder builder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.archive_fragment, container, false);
        final Activity activity = getActivity();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        beerList = (ListView)view.findViewById(R.id.beerList);
        final ArrayAdapter<Beer> arrayAdapter = new ArrayAdapter<Beer>(getActivity(), android.R.layout.simple_list_item_1,beerDB);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("BeerList");
        beerList.setAdapter(arrayAdapter);

        beerList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object deletedBeer = beerList.getItemAtPosition(i);
                Beer delBeer = (Beer) deletedBeer;
                deleteBeer(delBeer.getKey());
                return true;
            }

        });

        beerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object selectedBeer = beerList.getItemAtPosition(i);
                Beer selBeer = (Beer) selectedBeer;

                ArrayList<Borrow> borrowed = selBeer.getBorrowed();
                ArrayList<Food> foods = selBeer.getFood();
                ArrayList<String> boorAndfoods =new ArrayList<String>();
                for(Borrow borrow: borrowed){
                    boorAndfoods.add("Dlugi: " + borrow.toString());
                }
                for(Food food : foods){
                    boorAndfoods.add("Jedzenie: " + food.toString());
                }
                builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista dlugow i jedzenia:");
                builder.setItems(boorAndfoods.toArray(new String[borrowed.size()]), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.cancel();
                    }});
                builder.show();
            }
        });

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Beer value = dataSnapshot.getValue(Beer.class);
                value.setKey(dataSnapshot.getKey());
                beerDB.add(value);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();

                for (int i = 0; i < beerDB.size(); i++) {
                    if (beerDB.get(i).getKey().equals(key)) {
                        beerDB.remove(i);
                        break;
                    }
                }

                arrayAdapter.notifyDataSetChanged();
            }



            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
   }

    public void deleteBeer(final String key){
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Czy wykasować element?");

        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_NUMBER |  InputType.TYPE_NUMBER_FLAG_DECIMAL);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDatabase.child(key).removeValue();
            }
        });
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        builder.show();


    }
    }