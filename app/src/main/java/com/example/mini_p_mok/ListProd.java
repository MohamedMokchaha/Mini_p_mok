package com.example.mini_p_mok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListProd extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapterProd myAdapter;
    Button retourn,add_favori;
    ImageButton button;
    ArrayList<Produit_aff> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_prod);
        retourn = findViewById(R.id.retourn2);
        recyclerView = findViewById(R.id.prod_list);
        database = FirebaseDatabase.getInstance().getReference("Produits");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        button = findViewById(R.id.logout);
        list = new ArrayList<>();
        myAdapter = new MyAdapterProd(this,list);
        recyclerView.setAdapter(myAdapter);
        add_favori=findViewById(R.id.add_favori);
        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Vider la liste pour éviter les doublons
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    try {
                        Produit_aff produit_aff = dataSnapshot.getValue(Produit_aff.class);
                        list.add(produit_aff);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        retourn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivityClient.class);
                startActivity(intent);
                finish();
            }
        });

    }
}