package com.ism.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeArticle {
    private Long id;
    private Commande commande;
    private Article article;
    private int quantite;
    private double prix;
    private double montant;
    public int getPrixUnitaire() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrixUnitaire'");
    }
}
