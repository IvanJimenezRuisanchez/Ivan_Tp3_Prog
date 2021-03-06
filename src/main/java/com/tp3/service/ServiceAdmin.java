package com.tp3.service;

import com.tp3.dto.ClientDto;
import com.tp3.dto.DocumentDto;
import com.tp3.model.*;
import com.tp3.repository.DocumentRepository;
import com.tp3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ServiceAdmin {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private DocumentRepository documentRepository;

        public Utilisateur addUserToBliblio(String firstName, String lastName, String address, String phoneNumber, String email, LocalDate dateEmbauche, String role) {
            Utilisateur user = null;
            switch (role.toUpperCase()){
                case "GESTIONNAIRE":
                    user = new Gestionnaire(firstName,lastName,address,phoneNumber,email,dateEmbauche);
                    break;
                case "PREPOSE":
                    user = new Prepose(firstName,lastName,address,phoneNumber,email,dateEmbauche);
                    break;
            }
            return userRepository.save(user);
        }

        public ClientDto addClient(String firstName, String lastName, String address, String phoneNumber, String email){
            Client client = new Client(firstName,lastName,address,phoneNumber,email);
            userRepository.save(client);
            return new ClientDto(firstName,lastName,address,phoneNumber,email);
        }

    public DocumentDto addDocumentToBiblio(String titre, int anneePub, String auteur , String editeur, String maisonDePublication, String type, String duration, int nbrExemplaires, String typeDocument) {
        Document document = null;
        switch (typeDocument.toUpperCase()) {
            case "LIVRE":
                document = new Livre(titre,anneePub,auteur,nbrExemplaires,editeur,maisonDePublication,type);
                break;
            case "CD":
                document = new Cd(titre, anneePub, auteur, nbrExemplaires,duration, type);
                break;
            case "DVD":
                document = new Dvd(titre, anneePub, auteur,nbrExemplaires, duration, type);
                break;
        }
        documentRepository.save(document);
        return new DocumentDto(titre,anneePub,auteur,nbrExemplaires,duration,type,editeur,maisonDePublication);
    }
}
