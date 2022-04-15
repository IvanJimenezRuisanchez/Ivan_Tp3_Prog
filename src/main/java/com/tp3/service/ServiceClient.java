package com.tp3.service;
import com.tp3.dto.CdDto;
import com.tp3.dto.DocumentDto;
import com.tp3.dto.DvdDto;
import com.tp3.dto.LivreDto;
import com.tp3.model.*;
import com.tp3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<LivreDto> findLivres(String critereRecherche, String data){
        List<Livre> livre = new ArrayList<>();
        data = data.toUpperCase();
        switch (critereRecherche.toUpperCase()){
            case "TITRE":
                livre =  livreRepository.getLivreByTitre(data);
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
}
