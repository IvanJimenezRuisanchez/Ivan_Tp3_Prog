package com.tp3.dto;

import com.tp3.model.Dvd;
import lombok.Data;

@Data
public class DvdDto {
    private int id;
    private String titre;
    private int anneePublication;
    private String auteur;
    private int nbrExemplaire;
    private String duration;
    private String type;

    public DvdDto(int id, String titre, int anneePublication, String auteur, int nbrExemplaire, String type, String duration) {
        this.id = id;
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.auteur = auteur;
        this.nbrExemplaire = nbrExemplaire;
        this.duration = duration;
        this.type = type;
    }

    public DvdDto() {
    }
}