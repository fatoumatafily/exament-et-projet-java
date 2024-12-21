package com.ism.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String ville;
    private String quartier;
    private String numeroVilla;
}
