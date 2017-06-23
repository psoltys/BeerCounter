

        package com.example.piotr.beercounter.Fragments;


        import android.app.Activity;
        import android.app.Fragment;
        import android.app.FragmentTransaction;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.text.TextUtils;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.example.piotr.beercounter.MainActivity;
        import com.example.piotr.beercounter.R;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

        public class MainFragment extends Fragment {

    private Button new_act;
    private Button registerBtn;
    private EditText emailText;
    private EditText passwordText;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
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
        registerBtn = (Button) view.findViewById(R.id.registerBtn);

        emailText = (EditText) view.findViewById(R.id.emailText);
        passwordText = (EditText) view.findViewById(R.id.passwordText);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                if(firebaseAuth.getCurrentUser() != null){
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, BeerFragment.newInstance("cos"),"beerFragment")
                            .addToBackStack("beer")
                            .commit();
                }
            }
        };

        new_act.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
//
                signIn();
            }
        }));

        registerBtn.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, RegisterFragment.newInstance(null),"registerFragment")
                        .addToBackStack("register")
                        .commit();
            }
        }));
        return view;
    }
    private void signIn(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
            Toast.makeText(getActivity(), "Pola sa puste", Toast.LENGTH_LONG).show();
        else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_frame, BeerFragment.newInstance("cos"),"beerFragment")
                                .addToBackStack("beer")
                                .commit();
                    }
                    else
                        Toast.makeText(getActivity(), "Problem z logowaniem", Toast.LENGTH_LONG).show();

                }
            });
        }

    }

}

