package com.tp3.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;

    public Utilisateur(String firstName, String lastName, String address, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
