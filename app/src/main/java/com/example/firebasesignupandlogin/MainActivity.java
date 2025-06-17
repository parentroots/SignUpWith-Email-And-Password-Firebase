package com.example.firebasesignupandlogin;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private AppCompatButton signUpButton;
    private TextInputEditText edUserName,edEmail,edPassword;

    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        signUpButton=findViewById(R.id.signupButton);
        edUserName=findViewById(R.id.edUserName);
        edEmail=findViewById(R.id.edEmail);
        edPassword=findViewById(R.id.edPassword);

        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();



firebaseSignUpWithEmailAndPassword();


    }


    private void firebaseSignUpWithEmailAndPassword(){

        signUpButton.setOnClickListener(v -> {

            String userName=edUserName.getText().toString().trim();
            String email=edEmail.getText().toString().trim();
            String password=edPassword.getText().toString().trim();

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please enter credentials", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (task.isSuccessful()){

                        Toast.makeText(MainActivity.this, "User Create SuccessFull", Toast.LENGTH_SHORT).show();
                    finish();
                    }else {

                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });



        });

    }


}