package com.ism.services;

import com.ism.entities.Commande;

public interface CommandeServiceI {
    Commande creerCommande(Commande commande);
    double calculerTotalCommande(Commande commande);
}
