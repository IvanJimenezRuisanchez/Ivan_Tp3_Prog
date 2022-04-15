package com.tp3.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RetourDto {
    private int idUser;
    private int idDocument;
    final LocalDate LOCAL_DATE = LocalDate.now();

    public RetourDto(int idUser, int idDocument) {
        this.idUser = idUser;
        this.idDocument = idDocument;
    }

    public RetourDto() {
    }
}
