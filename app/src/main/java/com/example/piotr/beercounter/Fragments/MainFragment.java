

        package com.example.piotr.beercounter.Fragments;


        import android.app.Activity;
        import android.app.Fragment;
        import android.app.FragmentTransaction;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;

        import com.example.piotr.beercounter.MainActivity;
        import com.example.piotr.beercounter.R;

public class MainFragment extends Fragment {

    private Button new_act;
    private Button Archive;
    public static MainFragment newInstance(String loading_msg) {
        MainFragment loadingFragment = new MainFragment();
        if (loading_msg != null) {
            Bundle args = new Bundle();
            args.putString("msg", loading_msg);
            loadingFragment.setArguments(args);
        }
        return loadingFragment;
    }


    public MainFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.main_fragment, container, false);
        final Activity activity = getActivity();
        new_act = (Button) view.findViewById(R.id.new_activity);
        Archive = (Button) view.findViewById(R.id.Show_Archive);
        new_act.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, BeerFragment.newInstance("cos"),"beerFragment")
                        .addToBackStack("beer")
                        .commit();
            }
        }));

        Archive.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, ArchiveFragment.newInstance(null),"archiveFragment")
                        .addToBackStack("archive")
                        .commit();
            }
        }));
        return view;
    }
}

