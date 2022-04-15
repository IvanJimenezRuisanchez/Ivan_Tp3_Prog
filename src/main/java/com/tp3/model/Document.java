package com.tp3.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int idDocument;

    private String titre;
    private int anneePublication;
    private String auteur;
    private int nbrExemplaire;
    private String categorie;

    public Document(String titre, int anneePub, String auteur, int nbrExemplaire) {
        this.titre = titre;
        this.anneePublication = anneePub;
        this.auteur = auteur;
        this.nbrExemplaire = nbrExemplaire;
    }

}
