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

public class SecondActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText addUsername, addCommande, addPhone;
    ImageButton button;
    Button add_product,btn_addClient;;
    TextView textView;
    FirebaseUser user;
    DatabaseReference Prod;
    EditText nom, description_p,price_p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        add_product = findViewById(R.id.add);
        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        btn_addClient = findViewById(R.id.btn_addClient);
        addCommande = findViewById(R.id.add_Commande);
        addUsername = findViewById(R.id.add_Username);
        addPhone = findViewById(R.id.add_Phone);
        textView = findViewById(R.id.user_details);
        Prod = FirebaseDatabase.getInstance().getReference().child("Produits");
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
        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListClient.class);
                startActivity(intent);
                finish();
            }
        });


        btn_addClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_p = addUsername.getText().toString();
                String categorie_p = addCommande.getText().toString();
                String prix_p = addPhone.getText().toString();
                Produit produit = new Produit(name_p,categorie_p,prix_p); // Fix here
                Prod.push().setValue(produit);
                Toast.makeText(SecondActivity.this, "Insertion avec succées", Toast.LENGTH_SHORT).show();
            }
        });


    }}





