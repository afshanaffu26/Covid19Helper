package com.example.covid19helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editEmail,editPassword,editConfirmPassword;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign up");

//        Spinner spinner= findViewById(R.id.spinner);
//        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.security_questions,R.layout.color_spinner_layout);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);

        editEmail=(EditText) findViewById(R.id.editEmail);
        editPassword=(EditText) findViewById(R.id.editPassword);
        editConfirmPassword=(EditText) findViewById(R.id.editConfirmPassword);

        progressBar=(ProgressBar) findViewById(R.id.progressbar);
    }

    public void navigateToLogin(View view) {
        String email=editEmail.getText().toString().trim();
        String password=editPassword.getText().toString().trim();
        String confirmPassword=editConfirmPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please enter valid email");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editPassword.setError("Minimum length of password should be 6");
            editPassword.requestFocus();
            return;
        }
        if(confirmPassword.isEmpty())
        {
            editConfirmPassword.setError("Password is required");
            editConfirmPassword.requestFocus();
            return;
        }
        if(!password.equals(confirmPassword))
        {
            editPassword.setError("Passwords do not match");
            //editConfirmPassword.setError("Passwords do not match");
            editPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"Verification Email Sent.Verify your Email to Login.",Toast.LENGTH_SHORT).show();
                            }
                        });
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    }
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"Email is already registered",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).equals("Select"))
        {
            //do nothing
        }
        else
        {
            String item= parent.getItemAtPosition(position).toString();
            //show selected spinner
           // Toast.makeText(this,parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public void navToLogin(View view) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }
}
