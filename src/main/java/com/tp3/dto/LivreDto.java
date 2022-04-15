package com.tp3.dto;

import lombok.Data;

@Data
public class LivreDto {
    private int id;
    private String titre;
    private int anneePublication;
    private String auteur;
    private int nbrExemplaire;
    private String editeur;
    private String maisonDePublication;
    private String type;
    public LivreDto(int id, String titre, int anneePublication, String auteur, int nbrExemplaire, String editeur,
                    String maisonDePublication, String type) {
        this.id = id;
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.auteur = auteur;
        this.nbrExemplaire = nbrExemplaire;
        this.editeur = editeur;
        this.maisonDePublication = maisonDePublication;
        this.type = type;
    }

    public LivreDto() {
    }
}
