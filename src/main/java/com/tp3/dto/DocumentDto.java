package com.tp3.dto;

import com.tp3.model.Cd;
import com.tp3.model.Dvd;
import com.tp3.model.Livre;
import lombok.Data;

@Data
public class DocumentDto {
    private int id;
    private String titre;
    private int anneePublication;
    private String auteur;
    private int nbrExemplaire;
    private String duration;
    private String type;
    private String editeur;
    private String maisonDePublication;
    private String typeDocument;

    public DocumentDto(String titre, int anneePublication, String auteur, int nbrExemplaire, String duration, String type, String editeur, String maisonDePublication) {
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.auteur = auteur;
        this.nbrExemplaire = nbrExemplaire;
        this.duration = duration;
        this.type = type;
        this.editeur = editeur;
        this.maisonDePublication = maisonDePublication;
    }

    public DocumentDto() {
    }

    public DocumentDto(int id, String titre, int anneePublication, String auteur, int nbrExemplaire) {
        this.id = id;
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.auteur = auteur;
        this.nbrExemplaire = nbrExemplaire;
    }

    public Livre toLivre(String titre, int anneePublication, String auteur, int nbrExemplaire) {
        Livre livre = new Livre (titre, anneePublication, auteur, nbrExemplaire, editeur , maisonDePublication , type);
        return livre;
    }

    public Dvd toDvd(String titre, int anneePublication, String auteur, int nbrExemplaire) {
        Dvd dvd = new Dvd (titre, anneePublication, auteur, nbrExemplaire, duration, type);
        return dvd;
    }

    public Cd toCD(String titre, int anneePublication, String auteur, int nbrExemplaire) {
        Cd cd = new Cd(titre, anneePublication, auteur, nbrExemplaire, duration, type);
        return cd;
    }





}
