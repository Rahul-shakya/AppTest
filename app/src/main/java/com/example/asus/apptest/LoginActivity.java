package com.example.asus.apptest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button signinbtn;
    private EditText signinmail;
    private EditText signinpass;
    private TextView signuptxt;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signinmail=(EditText)findViewById(R.id.email1);
        signinpass=(EditText)findViewById(R.id.pass1);
        signinbtn=(Button)findViewById(R.id.btn1);
        signuptxt=(TextView)findViewById(R.id.sign1);

        progressDialog=new ProgressDialog(this);

        signinbtn.setOnClickListener(this);
        signuptxt.setOnClickListener(this);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

        }

      }

      private void userLogin(){
        String email=signinmail.getText().toString().trim();
        String password=signinpass.getText().toString().trim();

          if(TextUtils.isEmpty(email))
          {
              Toast.makeText(this,"Please enter your email id",Toast.LENGTH_SHORT).show();
              return;
          }

          if(TextUtils.isEmpty(password))
          {
              Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
              return;
          }
          progressDialog.setMessage("Loading..Please Wait");
          progressDialog.show();

          firebaseAuth.signInWithEmailAndPassword(email,password)
                  .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          progressDialog.dismiss();
                          if(task.isSuccessful()){
                              //start profile activity
                              finish();
                              startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

                          }

                      }
                  }) ;

      }
    @Override
    public void onClick(View view) {
        if(view==signinbtn){
            userLogin();
        }
        if(view==signuptxt){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }


    }
}
