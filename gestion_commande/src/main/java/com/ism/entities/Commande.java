package com.ism.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    private Long id;
    private Client client;
    private List<CommandeArticle> articles;
}
