package com.example.mini_p_mok;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivityClient extends AppCompatActivity {
    FirebaseAuth auth;
    Button retourn;
    EditText comm_Username,commPhone,comm_Commande;
    ImageButton button;
    Button aff_produit,btn_addComm;;
    TextView textView;
    FirebaseUser user;
    DatabaseReference comm;
    EditText nom, description_p,price_p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_client);
        aff_produit = findViewById(R.id.aff_produit);
        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        btn_addComm = findViewById(R.id.btn_addComm);
        commPhone = findViewById(R.id.comm_Phone);
        comm_Username = findViewById(R.id.comm_Username);
        comm_Commande = findViewById(R.id.comm_Commande);

        textView = findViewById(R.id.user_details);
        retourn = findViewById(R.id.retourn);
        comm = FirebaseDatabase.getInstance().getReference().child("Users");
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        aff_produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListProd.class);
                startActivity(intent);
                finish();
            }
        });


        btn_addComm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = comm_Username.getText().toString();
                String phone = comm_Commande.getText().toString();
                String commande = commPhone.getText().toString();
                Add_command add_command = new Add_command(username, phone, commande);
                // Fix here
                comm.push().setValue(add_command);
                Toast.makeText(SecondActivityClient.this, "Commande inserer avec succées", Toast.LENGTH_SHORT).show();
            }
        });




    }}





