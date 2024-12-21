package com.ism.services.impl;

import com.ism.entities.Commande;
import com.ism.services.CommandeServiceI;

public class CommandeServiceImpl implements CommandeServiceI {

    @Override
    public Commande creerCommande(Commande commande) {
        return commande; 
    }

    @Override
    public double calculerTotalCommande(Commande commande) {
        return commande.getArticles().stream()
                .mapToDouble(article -> article.getPrix() * article.getQuantite())
                .sum();
    }
}
