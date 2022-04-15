package com.tp3.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Cd extends Document {
    private String duration;
    private String type;

    public Cd(String titre, int anneePub, String auteur, int nbrExemplaire, String duration, String type) {
        super(titre, anneePub, auteur, nbrExemplaire);
        this.duration = duration;
        this.type = type;
    }

    public Integer getDureeEmpreunt(){
        return 2;
    }
}
