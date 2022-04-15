package com.tp3.service;
import com.tp3.dto.*;
import com.tp3.model.*;
import com.tp3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceClient{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private EmpreuntRepository empreuntRepository;
    @Autowired
    private CdRepository cdRepository;
    @Autowired
    private DvdRepository dvdRepository;
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private ClientRepository clientRepository;


    public List<LivreDto> findLivres(String critereRecherche, String data){
        List<Livre> livre = new ArrayList<>();
        data = data.toUpperCase();
        switch (critereRecherche.toUpperCase()){
            case "TITRE":
                livre =  livreRepository.getLivreAuteur(data);
                break;
            case "AUTEUR":
                livre =  livreRepository.getLivreAuteur(data);
                break;
            case "CATEGORIE":
                livre =  livreRepository.getLivreByCategorie(data);
                break;
            case "ANNEE":
                livre =  livreRepository.getLivreByAnneePublication(Integer.parseInt(data));
                break;
        }
        return  toLivreDTOList(livre);
    }

    private List<LivreDto> toLivreDTOList(List<Livre> livres){
        List<LivreDto> livreDtos = new ArrayList<>();
        for(Livre livre : livres){
            livreDtos.add(new LivreDto(livre.getIdDocument(),livre.getTitre(),livre.getAnneePublication(),livre.getAuteur(),
                    livre.getNbrExemplaire(),livre.getEditeur(),livre.getMaisonDePublication(),livre.getType()));
        }
        return livreDtos;
    }

    public List<CdDto> findCds(String critereRecherche, String data){
        List<Cd> cd = new ArrayList<>();
        data = data.toUpperCase();
        switch (critereRecherche.toUpperCase()){
            case "TITRE":
                cd =  cdRepository.getCdByTitre(data);
                break;
            case "AUTEUR":
                cd =  cdRepository.getCdByAuteur(data);
                break;
            case "CATEGORIE":
                cd  =  cdRepository.getCdByCategorie(data);
                break;
            case "ANNEE":
                cd  =  cdRepository.getCdByAnneePublication(Integer.parseInt(data));
                break;
        }
        return  toCdDTOList(cd );
    }

    private List<CdDto> toCdDTOList(List<Cd> cds){
        List<CdDto> cdDtos = new ArrayList<>();
        for(Cd cd : cds){
            cdDtos.add(new CdDto(cd.getIdDocument(),cd.getTitre(),cd.getAnneePublication(),cd.getAuteur(),
                    cd.getNbrExemplaire(),cd.getDuration(),cd.getType()));
        }
        return cdDtos;
    }

    public List<DvdDto> findDvds(String critereRecherche, String data){
        List<Dvd> dvds = new ArrayList<>();
        data = data.toUpperCase();
        switch (critereRecherche.toUpperCase()){
            case "TITRE":
                dvds =  dvdRepository.getDvdByTitre(data);
                break;
            case "AUTEUR":
                dvds =  dvdRepository.getDvdByAuteur(data);
                break;
            case "CATEGORIE":
                dvds =  dvdRepository.getDvdByCategorie(data);
                break;
            case "ANNEE":
                dvds =  dvdRepository.getDvdByAnneePublication(Integer.parseInt(data));
                break;
        }
        return  toDvdDTOList(dvds);
    }

    private List<DvdDto> toDvdDTOList(List<Dvd> dvds){
        List<DvdDto> dvdDtos = new ArrayList<>();
        for(Dvd dvd : dvds){
            dvdDtos.add(new DvdDto(dvd.getIdDocument(),dvd.getTitre(),dvd.getAnneePublication(),dvd.getAuteur(),
                    dvd.getNbrExemplaire(),dvd.getDuration(),dvd.getType()));
        }
        return dvdDtos;
    }


    public void empreunter(int idUser, int idDocument,LocalDate debut) {
        Document document = documentRepository.findById(idDocument).get();
        Client client = clientRepository.findById(idUser).get();
        int nbrExemplaires = document.getNbrExemplaire();
        LocalDate dateFin = null;
        switch (document.getClass().getSimpleName().toUpperCase()){
            case "LIVRE":
                dateFin = debut.plusDays(21);
                break;
            case "CD":
                dateFin = debut.plusDays(14);
                break;
            case "DVD":
                dateFin = debut.plusDays(7);
                break;
        }
        document.setNbrExemplaire(nbrExemplaires-1);
        documentRepository.save(document);
        Empreunt empreunt = new Empreunt(debut, dateFin, "En cours");
        empreunt.addClientResponsable(client);
        empreunt.addDocumentToEmpreunt(document);
        empreuntRepository.save(empreunt);
    }

    public void retourner(int idUser, int idDocument) {
        Empreunt empreunt = empreuntRepository.getEmpreuntByClientAndDocument(idUser,idDocument);
        empreunt.setStatus("Retourner");
        Document document = documentRepository.findById(idDocument).get();
        int nbExemplaires = document.getNbrExemplaire();
        document.setNbrExemplaire(nbExemplaires+1);
        empreuntRepository.save(empreunt);
        documentRepository.save(document);
    }

    public List<EmpreuntDto> getAllEmpreuntByUser(int idUser){
        return  toEmpreuntDTOList(empreuntRepository.getEmpreuntsByIdUser(idUser));
    }

    private List<EmpreuntDto> toEmpreuntDTOList(List<Empreunt> empreunts){
        List<EmpreuntDto> empreuntDtos = new ArrayList<>();
        for(Empreunt empreunt : empreunts){
            empreuntDtos.add(new EmpreuntDto(empreunt.getIdEmpreunt(),empreunt.getDocument().getIdDocument(),empreunt.getDateDebut().toString(),empreunt.getDateFin().toString(),
                    empreunt.getStatus(),empreunt.getClient().getIdUser()));
        }
        return empreuntDtos;
    }


    public List<LivreDto> getAllLivres(){
        return toLivreDTOList(livreRepository.findAll());
    }

    public List<DvdDto> getAllDvds(){
        return toDvdDTOList(dvdRepository.findAll());
    }

    public List<CdDto> getAllCds(){
        return toCdDTOList(cdRepository.findAll());
    }

}
