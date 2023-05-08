package com.example.mini_p_mok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterProd extends RecyclerView.Adapter<MyAdapterProd.MyViewHolder> {
    Context context;
    ArrayList<Produit_aff> list;

    public MyAdapterProd(Context context, ArrayList<Produit_aff> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_prod, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produit_aff produit_aff = list.get(position);
        holder.nom_pro.setText(produit_aff.getNom_p());
        holder.categorie_pro.setText(produit_aff.getCategorie_p());
        holder.prix_pro.setText(produit_aff.getPrix_p());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nom_pro, categorie_pro, prix_pro;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom_pro = itemView.findViewById(R.id.tvname_p);
            categorie_pro = itemView.findViewById(R.id.tvcategorie_p);
            prix_pro = itemView.findViewById(R.id.tvprix_p);
        }
    }
}
