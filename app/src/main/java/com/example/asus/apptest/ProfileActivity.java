package com.example.asus.apptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserMail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewUserMail=(TextView)findViewById(R.id.textViewUserEmail);
        buttonLogout=(Button)findViewById(R.id.buttonLogOut);
        firebaseAuth=FirebaseAuth.getInstance();
        buttonLogout.setOnClickListener(this);

        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();
        textViewUserMail.setText("Welcome "+user.getEmail()+"\n\t\t\t\t\t\t\t\t\t\t\tHi There!");


    }

    @Override
    public void onClick(View view) {
        if(view==buttonLogout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class ));
        }


    }
}