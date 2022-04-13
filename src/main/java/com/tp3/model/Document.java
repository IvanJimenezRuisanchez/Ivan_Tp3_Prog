package com.tp3.model;

import com.tp3.dto.document.DocumentDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long idDocument;

    private String titre;
    private int anneePublication;
    private String auteur;
    private int nbrExemplaire;

    public Document(String titre, int anneePub, String auteur, int nbrExemplaire) {
        this.titre = titre;
        this.anneePublication = anneePub;
        this.auteur = auteur;
        this.nbrExemplaire = nbrExemplaire;
    }

}
