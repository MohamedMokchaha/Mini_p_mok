package com.example.mini_p_mok;

public class Add_command {
    String username, commande, phone;

    public Add_command(String username, String commande, String phone) {
        this.username = username;
        this.commande = commande;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getCommande() {
        return commande;
    }

    public String getPhone() {
        return phone;
    }
}
