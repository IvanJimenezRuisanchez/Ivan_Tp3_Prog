package com.tp3.service;

import com.tp3.model.Client;
import com.tp3.model.Document;
import com.tp3.model.Empreunt;
import com.tp3.model.Utilisateur;
import com.tp3.repository.DocumentRepository;
import com.tp3.repository.EmpreuntRepository;
import com.tp3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class ServiceClient{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private EmpreuntRepository empreuntRepository;

    public Empreunt empreunter(long idUser, long idDocument) {
        Document document = documentRepository.findById(idDocument).get();
        String typeDocument = document.getClass().getName().toUpperCase();
        int nbrExemplaires = document.getNbrExemplaire();
        LocalDate dateFin = null;

        if(nbrExemplaires > 0) {
            switch (typeDocument) {
                case "LIVRE":
                    dateFin = LocalDate.now().plusWeeks(3);
                    break;
                case "CD":
                    dateFin = LocalDate.now().plusWeeks(2);
                    break;
                case "DVD":
                    dateFin = LocalDate.now().plusWeeks(1);
                    break;
            }
            Empreunt empreunt = new Empreunt((Client) userRepository.findById(idUser).get(), documentRepository.findById(idDocument).get(),
                    dateFin, "En cours");
            document.setNbrExemplaire(nbrExemplaires-1);
            documentRepository.save(document);
            return empreuntRepository.save(empreunt);
        }
        return null;
    }
}
