package com.example.piotr.beercounter.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.piotr.beercounter.MainActivity;
import com.example.piotr.beercounter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Piotr on 2017-06-22.
 */

public class RegisterFragment extends Fragment {
    private Button registerBtn;
    private EditText register_email;
    private EditText register_password;
    private EditText register_nick;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgDialog;
    private DatabaseReference mDatabase;
    public static RegisterFragment newInstance(String loading_msg) {
        RegisterFragment loadingFragment = new RegisterFragment();
        if (loading_msg != null) {
            Bundle args = new Bundle();
            args.putString("msg", loading_msg);
            loadingFragment.setArguments(args);
        }
        return loadingFragment;
    }


    public RegisterFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.register_fragment, container, false);
        final Activity activity = getActivity();
        registerBtn = (Button) view.findViewById(R.id.registerBtn);


        register_email = (EditText) view.findViewById(R.id.register_email);
        register_nick = (EditText) view.findViewById(R.id.register_nick);
        register_password = (EditText) view.findViewById(R.id.register_password);
        mAuth = FirebaseAuth.getInstance();
        mProgDialog = new ProgressDialog(getActivity());
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        registerBtn.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                register();
            }
        }));


        return view;
    }
    private void register(){
        final String nick = register_nick.getText().toString().trim();
        String email = register_email.getText().toString().trim();
        String password = register_password.getText().toString().trim();

        if(!TextUtils.isEmpty(email)&& !TextUtils.isEmpty(password)){
            mProgDialog.setMessage("Rejestrowanie...");
            mProgDialog.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = mDatabase.child(user_id);

                    current_user_db.child("nick").setValue(nick);
                }
            });
            mProgDialog.dismiss();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, MainFragment.newInstance("cos"),"mainFragment")
                    .addToBackStack("main")
                    .commit();
        }
    }
}
