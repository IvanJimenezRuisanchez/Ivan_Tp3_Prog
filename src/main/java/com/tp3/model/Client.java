package com.tp3.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Client extends Utilisateur {

    public Client(String firstName, String lastName, String address, String phoneNumber, String email) {
        super(firstName, lastName, address, phoneNumber, email);
    }

    @OneToMany
    private List<Empreunt> empreunts = new ArrayList<>();

    public void addEmpreuntToClient(Empreunt empreunt){
        empreunts.add(empreunt);
    }
}
