package com.tp3.dto;

import lombok.Data;

@Data
public class CdDto {
    private int id;
    private String titre;
    private int anneePublication;
    private String auteur;
    private int nbrExemplaire;
    private String duration;
    private String type;

    public CdDto(int id, String titre, int anneePublication, String auteur, int nbrExemplaire, String type, String duration) {
        this.id = id;
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.auteur = auteur;
        this.nbrExemplaire = nbrExemplaire;
        this.duration = duration;
        this.type = type;
    }

    public CdDto() {
    }
}