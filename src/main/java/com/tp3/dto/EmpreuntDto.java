package com.tp3.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpreuntDto {
    private int idEmpreunt;
    private String dateDebut;
    private String dateFin;
    private String status;
    private int idUser;
    private int idDocument;
    public EmpreuntDto(int idEmpreunt,int idDocument,  String dateDebut, String dateFin, String status, int idUser) {
        this.idEmpreunt = idEmpreunt;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.status = status;
        this.idUser = idUser;
        this.idDocument = idDocument;
    }

    public EmpreuntDto() {
    }
}
