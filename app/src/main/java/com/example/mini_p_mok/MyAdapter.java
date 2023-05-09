package com.example.mini_p_mok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Client> list;

    public MyAdapter(Context context, ArrayList<Client> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Client client = list.get(position);
        holder.username.setText(client.getUsername());
        holder.phone.setText(client.getPhone());
        holder.commande.setText(client.getCommande());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyItemRemoved(position); // Avertir l'adaptateur que l'élément a été supprimé
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username, phone, commande;
        Button btnDelete;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.tvusername);
            phone = itemView.findViewById(R.id.tvphone);
            commande = itemView.findViewById(R.id.tvcommande);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

}
