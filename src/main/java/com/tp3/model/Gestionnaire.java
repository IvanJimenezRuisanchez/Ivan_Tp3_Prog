package com.tp3.model;

import com.tp3.repository.DocumentRepository;
import com.tp3.repository.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Gestionnaire extends Utilisateur {
    private LocalDate dateEmbauche;

    public Gestionnaire(String firstName, String lastName, String address, String phoneNumber, String email, LocalDate dateEmbauche) {
        super(firstName, lastName, address, phoneNumber, email);
        this.dateEmbauche = dateEmbauche;
    }
}
