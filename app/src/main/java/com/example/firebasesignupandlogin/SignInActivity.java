package com.example.firebasesignupandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    TextInputEditText edSignInEmail,edSignInPassword;

    FirebaseAuth auth;
    AppCompatButton signInBtn;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        variableFinder();
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();


        signInBtn.setOnClickListener(v -> {


            String email=edSignInEmail.getText().toString().trim();
            String password=edSignInPassword.getText().toString().trim();


            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
           edSignInEmail.setError("Enter Valid Email");
            } else if (password.isEmpty()){

                edSignInPassword.setError("Enter Valid Email");
            }

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        Intent intent =new Intent(SignInActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SignInActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });


        });





    }

    private void variableFinder(){

        edSignInEmail=findViewById(R.id.edSignEmail);
        edSignInPassword=findViewById(R.id.edSignInPassword);
        signInBtn=findViewById(R.id.signInButton);

    }
}