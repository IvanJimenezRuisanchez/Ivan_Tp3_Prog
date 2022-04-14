package com.tp3.dto;

import com.tp3.model.Client;
import lombok.Data;

@Data
public class ClientDto {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;

    public ClientDto(String firstName, String lastName, String address, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public ClientDto(){}

    public Client toClient(String firstName, String lastName, String address, String phoneNumber, String email){
        return new Client(firstName,lastName,address,phoneNumber,email);
    }
}
