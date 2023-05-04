package com.example.mini_p_mok;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    MaterialButton bouton_login;
    EditText inp_username, inp_password;
    TextView regist_now;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        inp_username = findViewById(R.id.email);
        inp_password = findViewById(R.id.password);

        bouton_login = findViewById(R.id.loginbtn);
        progressBar = findViewById(R.id.progress_bar);

        // Setup the registration button outside of the login button's onClickListener
        regist_now = findViewById(R.id.registerNow);
        regist_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bouton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String user, pass;
                user = inp_username.getText().toString();
                pass = inp_password.getText().toString();
                if(TextUtils.isEmpty(user)){
                    Toast.makeText(MainActivity.this, "Entrer nom d'utilisateur", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(MainActivity.this, "Entrer le mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(user, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    if (user.equals("admin@gmail.com")) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Intent intent = new Intent(MainActivity.this, ListProd.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Echec de l'authentification, vérifiez vos informations de connexion", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
