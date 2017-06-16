package com.example.piotr.beercounter.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piotr.beercounter.R;

public class ArchiveFragment extends Fragment {
    public static MainFragment newInstance(String loading_msg) {
        MainFragment loadingFragment = new MainFragment();
        if (loading_msg != null) {
            Bundle args = new Bundle();
            args.putString("msg", loading_msg);
            loadingFragment.setArguments(args);
        }
        return loadingFragment;
    }


    public ArchiveFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.archive_fragment, container, false);
        final Activity activity = getActivity();

        return view;
    }
}