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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnreg;
    private EditText email;
    private EditText pass;
    private TextView sgnin;


    private ProgressDialog progdia;
    private FirebaseAuth fire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnreg=(Button)findViewById(R.id.btn);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        sgnin =(TextView)findViewById(R.id.sign);
        progdia=new ProgressDialog(this);
        fire=FirebaseAuth.getInstance();

        btnreg.setOnClickListener(this);
        sgnin.setOnClickListener(this);

        if(fire.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

        }
    }

    private void registerUser(){
        String mail=email.getText().toString().trim();
        String password=pass.getText().toString().trim();

        if(TextUtils.isEmpty(mail))
        {
            Toast.makeText(this,"Please enter your email id",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
   
        progdia.setMessage("Registering User..");
        progdia.show();

        fire.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                finish();
                                Toast.makeText(MainActivity.this,"Registerd Successfully",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                                progdia.dismiss();
                            }
                            else

                            {
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                progdia.dismiss();
                            }

                    }
                });
    }

    public void onClick(View view)
    {
        if(view==btnreg)
        {
            registerUser();
        }

        if(view==sgnin){

            startActivity(new Intent(this,LoginActivity.class));

        }
    }
}
