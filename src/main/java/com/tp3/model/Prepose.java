package com.tp3.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Prepose extends Utilisateur {

    private LocalDate dateEmbauche;

    public Prepose(String firstName, String lastName, String address, String phoneNumber, String email, LocalDate dateEmbauche) {
        super(firstName, lastName, address, phoneNumber, email);
        this.dateEmbauche = dateEmbauche;
    }
}
